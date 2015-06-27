package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

abstract class XLSMatcher extends TypeSafeMatcher<XLS> implements SelfDescribing {
  protected String reduceSpaces(String text) {
    return text.replaceAll("[\\s\\n\\r\u00a0]+", " ").trim();
  }
}
