package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hamcrest.Description;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.util.Arrays.asList;

public class ContainsRow extends XLSMatcher {
  private final String[] cellTexts;

  public ContainsRow(String... cellTexts) {
    this.cellTexts = cellTexts;
  }

  @Override
  protected boolean matchesSafely(XLS item) {
    for (int i = 0; i < item.excel.getNumberOfSheets(); i++) {
      HSSFSheet sheet = item.excel.getSheetAt(i);
      for (Row row : sheet) {
        Queue<String> expectedTexts = new LinkedList<>(asList(cellTexts));
        for (Cell cell : row) {
          String expectedText = expectedTexts.peek();
          if (getFormattedCellValue(cell).contains(expectedText)) {
            expectedTexts.remove();
          }
          if (expectedTexts.isEmpty()) return true;
        }
      }
    }
    return false;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a XLS containing row ").appendValue(Arrays.toString(cellTexts));
  }
}
