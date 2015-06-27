package com.codeborne.xlstest;

import com.codeborne.xlstest.matchers.ContainsText;
import org.hamcrest.Matcher;

public class XLSMatchers {
  public static Matcher<XLS> containsText(String text) {
    return new ContainsText(text);
  }
}
