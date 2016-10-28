package com.codeborne.xlstest;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class InvalidXlsTest {
  @Test
  public void throwsX() {
    try {
      new XLS("This is not Excel file".getBytes());
      fail("expected IllegalArgumentException");
    }
    catch (IllegalArgumentException e) {
      assertThat(e.getMessage(), is("Invalid XLS "));
      assertThat(e.getCause(), is(notNullValue()));
    }
  }

  @Test
  public void throwsX2() {
    try {
      new XLS(new File("missing-file.xls"));
      fail("expected IllegalArgumentException");
    }
    catch (IllegalArgumentException e) {
      assertThat(e.getMessage(), is("Failed to read file missing-file.xls"));
      assertThat(e.getCause(), is(notNullValue()));
    }
  }
}
