package com.codeborne.xlstest;

import org.junit.Test;

import static com.codeborne.xlstest.XLS.containsText;
import static org.junit.Assert.assertThat;

public class RealWorldExamplesTest {
  @Test
  public void bankStatement() throws Exception {
    XLS xls = new XLS(getClass().getClassLoader().getResource("statement.xls"));
    assertThat(xls, containsText("Выписка"));
    assertThat(xls, containsText("Период"));
    assertThat(xls, containsText("18.06.2015 - 18.06.2015"));
  }

  @Test
  public void acquiringReport() throws Exception {
    XLS xls = new XLS(getClass().getClassLoader().getResource("acquiring.xls"));
    assertThat(xls, containsText("Отчёт об операциях по пластиковым картам - 27.06.2015"));
    assertThat(xls, containsText("с 27.05.2015 по 27.06.2015"));
    assertThat(xls, containsText("ООО \"Джапан Мотор Сервис\", ИНН 7830002292, Номер договора 08-1-1-04-2437"));
  }
}
