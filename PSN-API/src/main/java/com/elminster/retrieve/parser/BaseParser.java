package com.elminster.retrieve.parser;

import static com.elminster.retrieve.constants.CommonConstants.HTTP;

public class BaseParser {

  protected String parseUrl(final String url) {
    String parsed = url;
    if (null != url && !url.toLowerCase().startsWith(HTTP)) {
      parsed = HTTP + url;
    }
    return parsed;
  }
}
