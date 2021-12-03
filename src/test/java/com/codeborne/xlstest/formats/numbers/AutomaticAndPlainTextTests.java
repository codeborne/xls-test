package com.codeborne.xlstest.formats.numbers;

import com.codeborne.xlstest.XLS;
import org.junit.Test;

import java.io.File;

import static com.codeborne.xlstest.XLS.containsText;
import static org.hamcrest.MatcherAssert.assertThat;

public class AutomaticAndPlainTextTests {
  @Test
  public void automaticStyle_valueInCell1_displayedValue1_parsedAs1() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("formats.xlsx").toURI());
    assertThat(new XLS(file), containsText("1"));
  }

  @Test
  public void automaticStyle_valueInCell1comma2_displayedValue1comma2_parsedAs1point2() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("formats.xlsx").toURI());
    assertThat(new XLS(file), containsText("1.2"));
  }

  @Test
  public void plainText_valueInCell2_displayedValue2_parsedAs2() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("formats.xlsx").toURI());
    assertThat(new XLS(file), containsText("2"));
  }

  @Test
  public void plainText_valueInCell2point1_displayedValue2point1_parsedAs2point1() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("formats.xlsx").toURI());
    assertThat(new XLS(file), containsText("2.1"));
  }

  @Test
  public void plainText_valueInCell2comma2_displayedValue2comma2_parsedAs2comma2() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("formats.xlsx").toURI());
    assertThat(new XLS(file), containsText("2,2"));
  }
}
