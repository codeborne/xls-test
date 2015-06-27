package com.codeborne.xlstest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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

  private static URL toURL(URI uri) {
    try {
      return uri.toURL();
    }
    catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public XLS(byte[] content) {
    this("", content);
  }

  public XLS(InputStream inputStream) {
    this(readBytes(inputStream));
  }

  private static byte[] readBytes(URL url) {
    try (InputStream inputStream = url.openStream()) {
      return readBytes(inputStream);
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }
  
  private static byte[] readBytes(InputStream inputStream) {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    try {
      byte[] buffer = new byte[2048];

      int nRead;
      while ((nRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
        result.write(buffer, 0, nRead);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }

    return result.toByteArray();
  }
}
