package com.codeborne.xlstest;

import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertThat;

public class CreateXlsTest {
  @Test
  public void fromFile() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("statement.xls").toURI());
    assertThat(new XLS(file), XLSMatchers.containsText("Выписка"));
  }
  
  @Test
  public void fromUrl() throws Exception {
    URL url = getClass().getClassLoader().getResource("statement.xls");
    assertThat(new XLS(url), XLSMatchers.containsText("Выписка"));
  }

  @Test
  public void fromUri() throws Exception {
    URI uri = getClass().getClassLoader().getResource("statement.xls").toURI();
    assertThat(new XLS(uri), XLSMatchers.containsText("Выписка"));
  }

  @Test
  public void fromInputStream() throws Exception {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("statement.xls");
    assertThat(new XLS(inputStream), XLSMatchers.containsText("Выписка"));
  }

  @Test
  public void fromBytes() throws Exception {
    byte[] bytes = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("statement.xls").toURI()));
    assertThat(new XLS(bytes), XLSMatchers.containsText("Выписка"));
  }
}
