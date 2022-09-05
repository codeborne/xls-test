package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.xlstest.XLS.containsText;
import static com.codeborne.xlstest.XLS.doesNotContainsText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class ContainsTextTest {
  @Test
  public void canAssertThatXlsContainsText() throws IOException {
    XLS XLS = new XLS(Objects.requireNonNull(getClass().getClassLoader().getResource("statement.xls")));
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

  @Test
  public void canAssertXlsDoesNotContainText() throws IOException {
    XLS xls = new XLS(Objects.requireNonNull(getClass().getClassLoader().getResource("statement.xls")));
    assertThat(xls, doesNotContainsText("null"));
  }

  @Test
  public void errorDescriptionForDoesNotContainTextMatcher() {
    XLS xls = new XLS(new File("src/test/resources/small.xls"));
    try {
      assertThat(xls, doesNotContainsText("Выписка"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(), is(
        "\nExpected: a XLS not containing text \"Выписка\"" +
          "\n     but: was \"" + System.getProperty("user.dir") + "/src/test/resources/small.xls\"" +
          "\nВыписка\t\t\nСчёт\t40820810590480000591\t\n"));
    }
  }

  @Test
  public void errorDescriptionForContainsTextMatcher() {
    XLS xls = new XLS(new File("src/test/resources/small.xls"));
    try {
      assertThat(xls, containsText("wrong text"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(), is(
          "\nExpected: a XLS containing text \"wrong text\"" +
              "\n     but: was \"" + System.getProperty("user.dir") + "/src/test/resources/small.xls\"" +
              "\nВыписка\t\t\nСчёт\t40820810590480000591\t\n"));
    }
  }
}
