package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.junit.Test;

import static com.codeborne.xlstest.XLS.containsText;
import static org.junit.Assert.assertThat;

public class ContainsTextTest {
  @Test
  public void canAssertThatXlsContainsText() {
    XLS XLS = new XLS(getClass().getClassLoader().getResource("statement.xls"));
    assertThat(XLS, containsText("Выписка"));
    assertThat(XLS, containsText("Solntsev Andrei"));
    assertThat(XLS, containsText("25.06.2015 23:09:32"));

    assertThat(XLS, containsText("Счёт"));
    assertThat(XLS, containsText("40820810590480000591"));
    assertThat(XLS, containsText("Период"));
    assertThat(XLS, containsText("18.06.2015 - 18.06.2015"));
    assertThat(XLS, containsText("Входящий остаток на  18.06.2015"));
    assertThat(XLS, containsText("6.40"));

    assertThat(XLS, containsText("Дата"));
    assertThat(XLS, containsText("Счёт\nплательщика/получателя"));
    assertThat(XLS, containsText("Плательщик / Получатель"));
    assertThat(XLS, containsText("Операция"));
    assertThat(XLS, containsText("Сумма (RUB)"));

    assertThat(XLS, containsText("18.06.2015"));
    assertThat(XLS, containsText("40820810590550000146"));
    assertThat(XLS, containsText("Solntsev Andrei"));
    assertThat(XLS, containsText("сюда. Интернет-банк"));
    assertThat(XLS, containsText("-1.00"));

    assertThat(XLS, containsText("туда. Интернет-банк"));
    assertThat(XLS, containsText("1.00"));

    assertThat(XLS, containsText("Исходящий остаток на  18.06.2015"));
    assertThat(XLS, containsText("6.40"));

    assertThat(XLS, containsText("Поступление"));
    assertThat(XLS, containsText("Списание"));
    assertThat(XLS, containsText("Зарезервировано"));
    assertThat(XLS, containsText("349,928.00"));
  }
}
