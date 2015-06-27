package com.codeborne.xlstest;

import org.junit.Test;

import static com.codeborne.xlstest.XLSMatchers.containsText;
import static org.junit.Assert.assertThat;

public class RealWorldExamplesTest {
  @Test
  public void bankStatement() throws Exception {
    XLS XLS = new XLS(getClass().getClassLoader().getResource("statement.xls"));
    assertThat(XLS, containsText("Выписка"));
    assertThat(XLS, containsText("Период"));
    assertThat(XLS, containsText("18.06.2015 - 18.06.2015"));
  }
}
