package com.codeborne.xlstest;

import com.codeborne.xlstest.matchers.ContainsRow;
import com.codeborne.xlstest.matchers.ContainsText;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hamcrest.Matcher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.xlstest.IO.readBytes;
import static com.codeborne.xlstest.IO.toURL;

public class XLS {
  public final String name;
  public final HSSFWorkbook excel;

  private XLS(String name, byte[] content) {
    this.name = name;
    try (InputStream inputStream = new ByteArrayInputStream(content)) {
      excel = new HSSFWorkbook(inputStream);
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid XLS " + name, e);
    }
  }

  public XLS(File xlsFile) {
    this(xlsFile.getAbsolutePath(), readFile(xlsFile));
  }

  private static byte[] readFile(File xlsFile) {
    try {
      return Files.readAllBytes(Paths.get(xlsFile.getAbsolutePath()));
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public XLS(URL url) {
    this(url.toString(), readBytes(url));
  }

  public XLS(URI uri) {
    this(toURL(uri));
  }
  
  public XLS(byte[] content) {
    this("", content);
  }

  public XLS(InputStream inputStream) {
    this(readBytes(inputStream));
  }
  
  public static Matcher<XLS> containsText(String text) {
    return new ContainsText(text);
  }

  public static Matcher<XLS> containsRow(String... cellTexts) {
    return new ContainsRow(cellTexts);
  }
}
