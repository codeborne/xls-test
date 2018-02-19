package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.hamcrest.Description;

public class ContainsText extends XLSMatcher {
  private final String substring;

  public ContainsText(String substring) {
    this.substring = reduceSpaces(substring);
  }

  @Override
  protected boolean matchesSafely(XLS item) {
    for (int i  = 0; i < item.excel.getNumberOfSheets(); i++) {
      Sheet sheet = item.excel.getSheetAt(i);
      for (Row row : sheet) {
        for (Cell cell : row) {
          String cellValue = reduceSpaces(getFormattedCellValue(cell));
          if (cellValue.contains(substring))
            return true;
        }
      }
    }
    return false;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a XLS containing text ").appendValue(reduceSpaces(substring));
  }
}
