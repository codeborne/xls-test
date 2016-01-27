package com.codeborne.xlstest;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class XLSInformationTest {
  @Test
  public void canGetInformationFromXls() throws Exception {
    XLS XLS = new XLS(getClass().getClassLoader().getResource("statement.xls"));
    assertThat(XLS.name, endsWith("statement.xls"));
    assertThat(XLS.excel.getNumberOfSheets(), equalTo(3));
    assertThat(XLS.excel.getActiveSheetIndex(), equalTo(0));
    assertThat(XLS.excel.getSheetName(0), equalTo("Sheet1"));
  }
}
