package com.codeborne.xlstest;

import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.xlstest.XLS.containsText;
import static org.junit.Assert.assertThat;

public class CreateXLSXTest {
  @Test
  public void fromFile() throws Exception {
    File file = new File(getClass().getClassLoader().getResource("statement.xlsx").toURI());
    assertThat(new XLS(file), containsText("Выписка"));
  }
  
  @Test
  public void fromUrl() throws Exception {
    URL url = getClass().getClassLoader().getResource("statement.xlsx");
    assertThat(new XLS(url), containsText("Выписка"));
  }

  @Test
  public void fromUri() throws Exception {
    URI uri = getClass().getClassLoader().getResource("statement.xlsx").toURI();
    assertThat(new XLS(uri), containsText("Выписка"));
  }

  @Test
  public void fromInputStream() throws Exception {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("statement.xlsx");
    assertThat(new XLS(inputStream), containsText("Выписка"));
  }

  @Test
  public void fromBytes() throws Exception {
    byte[] bytes = Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("statement.xlsx").toURI()));
    assertThat(new XLS(bytes), containsText("Выписка"));
  }
}
