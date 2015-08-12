package com.codeborne.xlstest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

class IO {
  static byte[] readBytes(URL url) {
    try (InputStream inputStream = url.openStream()) {
      return readBytes(inputStream);
    }
    catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  static byte[] readBytes(InputStream inputStream) {
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

  static URL toURL(URI uri) {
    try {
      return uri.toURL();
    }
    catch (MalformedURLException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
