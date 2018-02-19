package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

import java.text.DecimalFormat;

abstract class XLSMatcher extends TypeSafeMatcher<XLS> implements SelfDescribing {

  private static final int TYPE_NUMBER = 0;
  private static final int TYPE_TEXT = 1;
  private static final int TYPE_EMPTY = 3;

  protected String reduceSpaces(String text) {
    return text.replaceAll("[\\s\\n\\r\u00a0]+", " ").trim();
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
      Sheet sheet = item.excel.getSheetAt(i);
      for (Row row : sheet) {
        for (Cell cell : row) {
          mismatchDescription.appendText(getFormattedCellValue(cell)).appendText("\t");
        }
        mismatchDescription.appendText("\n");
      }
    }
  }
}
