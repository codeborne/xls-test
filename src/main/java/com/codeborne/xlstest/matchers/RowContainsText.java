package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hamcrest.Description;

import java.text.DecimalFormat;

public class RowContainsText extends XLSMatcher {
  private static final int TYPE_NUMBER = 0;
  private static final int TYPE_TEXT = 1;
  private static final int TYPE_EMPTY = 3;

  private final String substring;

  public RowContainsText(String... cellTexts) {
    StringBuilder rowText = new StringBuilder();
    for (String cellText : cellTexts) {
       rowText.append(cellText).append('\t');
    }
    this.substring = rowText.substring(0, rowText.length()-1);
  }

  @Override
  protected boolean matchesSafely(XLS item) {
    for (int i = 0; i < item.excel.getNumberOfSheets(); i++) {
      HSSFSheet sheet = item.excel.getSheetAt(i);
      for (Row row : sheet) {
        StringBuilder rowText = new StringBuilder();
        for (Cell cell : row) {
          rowText.append(getFormattedCellValue(cell));
          rowText.append('\t');
        }
        if (rowText.toString().contains(substring)) return true;
      }
    }
    return false;
  }

  protected String getFormattedCellValue(Cell cell) {
    switch (cell.getCellType()) {
      case TYPE_NUMBER:
        return new DecimalFormat(cell.getCellStyle().getDataFormatString()).format(cell.getNumericCellValue());
      case TYPE_TEXT:
      case TYPE_EMPTY:
      default:
        return cell.toString();
    }
  }

  @Override
  protected void describeMismatchSafely(XLS item, Description mismatchDescription) {
    mismatchDescription.appendText("was \"").appendText(item.name).appendText("\"\n");

    for (int i = 0; i < item.excel.getNumberOfSheets(); i++) {
      HSSFSheet sheet = item.excel.getSheetAt(i);
      for (Row row : sheet) {
        for (Cell cell : row) {
          mismatchDescription.appendText(getFormattedCellValue(cell)).appendText("\t");
        }
        mismatchDescription.appendText("\n");
      }
    }
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a XLS containing ").appendValue(reduceSpaces(substring));
  }
}
