package com.elminster.retrieve.runnable;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elminster.common.constants.Constants.StringConstants;
import com.elminster.common.retrieve.RetrieveException;

public class CompareGameRetriever extends BaseRetriever {
  
  private static final String LANG = "ja";
  
  private static final Log logger = LogFactory.getLog(CompareGameRetriever.class);
  
  public CompareGameRetriever(String url) {
    super(url);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected void configHttpMethod(HttpClient client, HttpMethod httpMethod) throws RetrieveException {
    httpMethod.setRequestHeader("Host", "io.playstation.com");
    httpMethod.setRequestHeader("Connection", "keep-alive");
    httpMethod.setRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01");
    //httpMethod.setRequestHeader("X-Requested-With", "XMLHttpRequest");
//    httpMethod.setRequestHeader("User-Agent",
//        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
    httpMethod.setRequestHeader("Origin", "https://www.playstation.com");
    httpMethod.setRequestHeader("Referer", "https://www.playstation.com/en-us/my/compare-game-trophies/");
    httpMethod.setRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
    httpMethod.setRequestHeader("Accept-Language", LANG);
    httpMethod.setRequestHeader("AlexaToolbar-ALX_NS_PH", "AlexaToolbar/alxg-3.3");
    String cookie = StringConstants.EMPTY_STRING;
    Cookie[] cookies = client.getState().getCookies();
    for (Cookie c : cookies) {
      cookie += c.toString() + StringConstants.SEMICOLON;
    }
    if (cookie.length() > 3) {
      // remove last <code>;</code> and <code>\r\n</code>
      cookie = cookie.substring(0, cookie.length() - 3);
      if (logger.isDebugEnabled()) {
        logger.debug("Cookie to Send: " + cookie);
      }
      httpMethod.setRequestHeader("Cookie", cookie);
    }
  }
}
