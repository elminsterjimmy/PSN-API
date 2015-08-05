package com.elminster.retrieve.runnable;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.constants.FileExtensionConstants;
import com.elminster.common.retrieve.RetrieveException;
import com.elminster.common.util.DateUtil;
import com.elminster.common.util.FileUtil;
import com.elminster.retrieve.constants.PropertyKey;
import com.elminster.retrieve.exception.LoginFailedException;
import com.elminster.retrieve.util.Configuration;
import com.elminster.retrieve.util.SystemSetting;
import com.elminster.retrieve.web.CookieInjectRetriever;
import com.elminster.retrieve.web.cookie.PermitAllCookiesSpec;
import com.elminster.retrieve.web.data.Method;

/**
 * The base retriever.
 * 
 * @author jgu
 * @version 1.0
 */
public class BaseRetriever extends CookieInjectRetriever {

  /** the logger. */
  private static final Log logger = LogFactory.getLog(BaseRetriever.class);
  /** the configuration. */
  private static final Configuration configuration = Configuration.INSTANCE;
  /** the system setting. */
  private static final SystemSetting systemSetting = SystemSetting.INSTANCE;
  /** the cookie file. */
  private static final String COOKIE_FILE = "cookie/" + systemSetting.getPSNUsername() + FileExtensionConstants.TEXT_EXTENSION;

  /**
   * Constructor.
   * 
   * @param url
   *          the url
   * @param parser
   *          the parser
   */
  public BaseRetriever(String url) {
    super(url, Method.GET_METHOD);
  }

  /**
   * Check the cookie valid or not.
   * 
   * @return check wether the cookie is valid or not.
   */
  private boolean checkCookieValid() {
    File cookieFile = new File(COOKIE_FILE);
    if (cookieFile.exists()) {
      // TODO need to be checked
      long lastApiCalledTime = systemSetting.getLastApiCalledTime();
      if (System.currentTimeMillis() - lastApiCalledTime > DateUtil.DAY) {
        // expired
        return false;
      }
      return true;
    }
    
    return false;
  }

  /**
   * Login into the PSN.
   * 
   * @throws IOException
   *           on error
   * @throws URISyntaxException
   *           on error
   * @throws LoginFailedException
   *           when login failed
   */
  private void loginIntoPSN() throws IOException, URISyntaxException, LoginFailedException {
    HttpClient client = new HttpClient();
    
    CookiePolicy.registerCookieSpec("PermitAllCookiesSpec", PermitAllCookiesSpec.class);
    client.getParams().setCookiePolicy("PermitAllCookiesSpec");
    
    // get base info from public profile
    String loginUrl = configuration.getStringProperty(PropertyKey.PSN_LOGIN_URL);
    String oathUrl = configuration.getStringProperty(PropertyKey.PSN_OATH_URL);
    String magicNumber = configuration.getStringProperty(PropertyKey.PSN_LOGIN_MAGIC_NUMBER);
    String username = systemSetting.getPSNUsername();
    String userpassword = systemSetting.getPSNPassword();

    // login
    PostMethod loginMethod = new PostMethod();
    loginMethod.setURI(new URI(loginUrl, false));
    loginMethod.setRequestHeader("Host", "auth.api.sonyentertainmentnetwork.com");
    loginMethod.setRequestHeader("Connection", "keep-alive");
    loginMethod.setRequestHeader("Cache-Control", "max-age=0");
    loginMethod
        .setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    loginMethod.setRequestHeader("Origin", "https://auth.api.sonyentertainmentnetwork.com");
    loginMethod.setRequestHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
    loginMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    loginMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
    loginMethod.setRequestHeader("AlexaToolbar-ALX_NS_PH", "AlexaToolbar/alxg-3.3");
    // j_username
    // j_password
    loginMethod.addParameter(new NameValuePair("j_username", username));
    loginMethod.addParameter(new NameValuePair("j_password", userpassword));
    // the magic number
    loginMethod.addParameter(new NameValuePair("params", magicNumber));

    int status = client.executeMethod(loginMethod);
    // the login will returns 302 sometimes.
    if (200 != status && 302 != status) {
      throw new LoginFailedException("login returns " + status);
    }
    
    if (logger.isDebugEnabled()) {
      logger.debug("response from login: " + loginMethod.getResponseBodyAsString());
    }

//    // get next url
//
//    if (null != responseHeaders) {
//      for (Header h : responseHeaders) {
//        System.out.println(h);
//        // if ("Location".equals(h.getName())) {
//        // nextUrl = h.getValue();
//        // }
//      }
//    }

    logger.info("login with OATH.");

    StringBuilder cookie = new StringBuilder();
    Cookie[] cookies = client.getState().getCookies();
    for (Cookie c : cookies) {
      cookie.append(c.toString()).append(StringConstants.SEMICOLON);
    }
    if (logger.isDebugEnabled()) {
      logger.debug("cookies to set: " + cookie.toString());
    }

    GetMethod oathMethod = new GetMethod();
    oathMethod.setURI(new URI(oathUrl, false));
    oathMethod.setRequestHeader("Host", "secure.us.playstation.com");
    oathMethod.setRequestHeader("Connection", "keep-alive");
    oathMethod.setRequestHeader("Cache-Control", "max-age=0");
    oathMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    oathMethod.setRequestHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
    oathMethod.setRequestHeader("Referer",
        "https://auth.api.sonyentertainmentnetwork.com/login.jsp?service_entity=psn&request_theme=liquid");
    oathMethod.setRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
    oathMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,zh-TW;q=0.2");
    oathMethod.setRequestHeader("AlexaToolbar-ALX_NS_PH", "AlexaToolbar/alxg-3.3");
    oathMethod.setRequestHeader("Cookie", cookie.toString());

    status = client.executeMethod(oathMethod);
    if (200 != status) {
      throw new LoginFailedException("oath returns " + status);
    }
    
    // FIXME check login success
    String oathResponse = oathMethod.getResponseBodyAsString();
    if (logger.isDebugEnabled()) {
      logger.debug("response from OATH: " + oathResponse);
    }
    if (oathResponse.contains("signInPage")) {
      throw new LoginFailedException("login denied. please check username and password again.");
    }
    
    writeCookies(client);
    // release the client
    client = null;
  }

  /**
   * Write the cookies into a cache file.
   * @param client the http client
   * @throws IOException on error
   */
  private void writeCookies(HttpClient client) throws IOException {
    StringBuilder cookie = new StringBuilder();
    Cookie[] cookies = client.getState().getCookies();
    boolean first = true;
    for (Cookie c : cookies) {
      if (first) {
        first = false;
      } else {
        cookie.append(StringConstants.AND);
      }
      cookie.append(c.getName());
      cookie.append(StringConstants.EQUAL);
      cookie.append(c.getValue());
    }
    FileUtil.writeLine2file(cookie.toString(), COOKIE_FILE, false);
  }

  /**
   * Read the cookies from saved cookie file.
   * 
   * @return the cookies
   * @throws IOException
   *           on error
   */
  protected Cookie[] readCookies() throws Exception {
    if (!checkCookieValid()) {
      try {
        loginIntoPSN();
      } catch (URISyntaxException | LoginFailedException e) {
        throw e;
      }
    }
    systemSetting.updateLastApiCalledTime();
    
    Cookie[] co = null;
    File cookieFile = new File(COOKIE_FILE);
    if (cookieFile.exists()) {
      String cookieInfo = FileUtil.readFile2String(cookieFile.getAbsolutePath());
      if (null != cookieInfo) {
        String[] cookies = cookieInfo.split(StringConstants.AND);
        co = new Cookie[cookies.length];
        int i = 0;
        for (String cookie : cookies) {
          String[] split = cookie.split(StringConstants.EQUAL);
          String cookieKey = split[0];
          String cookieValue = split[1];
          if (logger.isDebugEnabled()) {
            logger.debug("cookie: " + cookieKey + "=" + cookieValue);
          }
          // update the expiration to 1 day.
          co[i++] = createCookie(".psn.com", cookieKey, cookieValue, "/", DateUtil.DAY);
        }
      }
    } else {
      throw new IllegalStateException("Cookie file doesn't exist");
    }
    return co;
  }

  @Override
  protected void configHttpMethod(HttpClient client, HttpMethod httpMethod) throws RetrieveException {
    httpMethod.setRequestHeader("Host", "my.playstation.com");
    httpMethod.setRequestHeader("Connection", "keep-alive");
    httpMethod.setRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01");
    httpMethod.setRequestHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
    httpMethod.setRequestHeader("Referer",
        "https://www.playstation.com/en-us/my/public-trophies/");
    httpMethod.setRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
    httpMethod.setRequestHeader("Origin", "https://www.playstation.com");
    httpMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,zh-TW;q=0.2");
    httpMethod.setRequestHeader("AlexaToolbar-ALX_NS_PH", "AlexaToolbar/alxg-3.3");
  }

}
