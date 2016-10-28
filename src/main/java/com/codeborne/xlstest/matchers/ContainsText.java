package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hamcrest.Description;

public class ContainsText extends XLSMatcher {
  private final String substring;

  public ContainsText(String substring) {
    this.substring = reduceSpaces(substring);
  }

  @Override
  protected boolean matchesSafely(XLS item) {
    for (int i  = 0; i < item.excel.getNumberOfSheets(); i++) {
      HSSFSheet sheet = item.excel.getSheetAt(i);
      for (Row row : sheet) {
        for (Cell cell : row) {
          if (reduceSpaces(getFormattedCellValue(cell)).contains(substring))
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
