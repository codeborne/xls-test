package com.codeborne.xlstest;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class InvalidXlsTest {
  @Test
  public void throws_IllegalArgumentException_ifFailedToParseXlsFromBytes() {
    try {
      new XLS("This is not Excel file".getBytes());
      fail("expected IllegalArgumentException");
    }
    catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage(), is("Invalid XLS "));
      assertThat(expected.getCause(), is(notNullValue()));
    }
  }

  @Test
  public void throws_IllegalArgumentException_ifFailedToReadXlsFile() {
    try {
      new XLS(new File("missing-file.xls"));
      fail("expected IllegalArgumentException");
    }
    catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage(), is("Failed to read file missing-file.xls"));
      assertThat(expected.getCause(), is(notNullValue()));
    }
  }
}
