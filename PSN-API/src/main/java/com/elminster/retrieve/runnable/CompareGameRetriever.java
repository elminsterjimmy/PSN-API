package com.elminster.retrieve.runnable;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;

import com.elminster.common.retrieve.RetrieveException;

public class CompareGameRetriever extends BaseRetriever {
  
  public CompareGameRetriever(String url) {
    super(url);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected void configHttpMethod(HttpClient client, HttpMethod httpMethod) throws RetrieveException {
    httpMethod.setRequestHeader("Host", "my.playstation.com");
    httpMethod.setRequestHeader("Connection", "keep-alive");
    httpMethod.setRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01");
    httpMethod.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    httpMethod.setRequestHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36");
    httpMethod.setRequestHeader("Referer", "https://my.playstation.com/logged-in/trophies/compare-game-trophies/");
    httpMethod.setRequestHeader("Accept-Encoding", "gzip, deflate, sdch");
    httpMethod.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,zh-TW;q=0.2");
    httpMethod.setRequestHeader("AlexaToolbar-ALX_NS_PH", "AlexaToolbar/alxg-3.3");
    String cookie = "";
    Cookie[] cookies = client.getState().getCookies();
    for (Cookie c : cookies) {
      cookie += c.toString() + ";";
    }
    if (cookie.length() > 0) {
      httpMethod.setRequestHeader("Cookie", cookie);
    }
  }
}
