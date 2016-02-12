package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.junit.Test;

import static com.codeborne.xlstest.XLS.containsRow;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ContainsRowTest {
  @Test
  public void rowContainsCells_all() {
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
  public void rowContainsCells_noMatch() {
    XLS xls = new XLS(getClass().getClassLoader().getResource("acquiring.xls"));
    assertThat(xls, not(containsRow("foobar")));
    assertThat(xls, not(containsRow("foobar", "PP028000", "281814930")));
    assertThat(xls, not(containsRow("PP028000", "281814930", "foobar")));
    assertThat(xls, not(containsRow("PP028000", "foobar", "281814930")));
  }
}