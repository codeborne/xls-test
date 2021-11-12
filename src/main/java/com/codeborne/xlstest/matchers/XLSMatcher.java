package com.codeborne.xlstest.matchers;

import com.codeborne.xlstest.XLS;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.hamcrest.TypeSafeMatcher;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

abstract class XLSMatcher extends TypeSafeMatcher<XLS> implements SelfDescribing {
  private List<String> legalNumericFormats = Arrays.asList(
    BuiltinFormats.getBuiltinFormat(1),
    BuiltinFormats.getBuiltinFormat(2),
    BuiltinFormats.getBuiltinFormat(3),
    BuiltinFormats.getBuiltinFormat(4),
    BuiltinFormats.getBuiltinFormat(9),
    BuiltinFormats.getBuiltinFormat(10)
  );

  protected String reduceSpaces(String text) {
    return text.replaceAll("[\\s\\n\\r\u00a0]+", " ").trim();
  }

  protected String getFormattedCellValue(Cell cell) {
    switch (cell.getCellType()) {
      case NUMERIC:
        DecimalFormat formatter;
        if (legalNumericFormats.contains(cell.getCellStyle().getDataFormatString())) {
          formatter = new DecimalFormat(cell.getCellStyle().getDataFormatString());
        } else {
          formatter = new DecimalFormat();
        }
        return formatter.format(cell.getNumericCellValue());
      case STRING:
      case BLANK:
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
