package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.codeborne.xlstest.XLS.containsRow;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ContainsRowTest {
  @Test
  public void rowContainsCells_all() throws IOException {
    XLS xls = new XLS(getClass().getClassLoader().getResource("acquiring.xls"));
    assertThat(xls, containsRow("PP028000", "дом 261Б, ул Семафорная, г Красноярск, 197372, РОССИЯ", "Оплата", "1,021.00", "10.21", "1,010.79", "27.05.2015 00:00:00", "415039******2364", "476956", "281814930"));
  }

  @Test
  public void rowContainsCells_withGaps() throws Exception {
    XLS xls = new XLS(getClass().getClassLoader().getResource("acquiring.xls"));
    assertThat(xls, containsRow("PP028000"));
    assertThat(xls, containsRow("PP028000", "281814930"));
    assertThat(xls, containsRow("281814930"));
    assertThat(xls, containsRow("Итого по терминалу", "48,271.00"));
  }

  @Test
  public void rowContainsCells_noMatch() throws IOException {
    XLS xls = new XLS(getClass().getClassLoader().getResource("acquiring.xls"));
    assertThat(xls, not(containsRow("foobar")));
    assertThat(xls, not(containsRow("foobar", "PP028000", "281814930")));
    assertThat(xls, not(containsRow("PP028000", "281814930", "foobar")));
    assertThat(xls, not(containsRow("PP028000", "foobar", "281814930")));
  }

  @Test
  public void errorDescription() throws IOException {
    XLS xls = new XLS(new File("src/test/resources/small.xls"));
    try {
      assertThat(xls, containsRow("wrong data"));
      fail("expected AssertionError");
    }
    catch (AssertionError expected) {
      assertThat(expected.getMessage(), is(
          "\nExpected: a XLS containing row \"[wrong data]\"" + 
              "\n     but: was \"" + System.getProperty("user.dir") + "/src/test/resources/small.xls\"" +
              "\nВыписка\t\t\nСчёт\t40820810590480000591\t\n"));
    }
  }
}