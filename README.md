# XLS Test
Excel testing library

## How to use

```java
import static com.codeborne.xlstest.XLSMatchers.containsText;
import static org.junit.Assert.assertThat;

public class ExcelContainsTextTest {
  @Test
  public void canAssertThatXlsContainsText() {
    XLS spreadsheet = new XLS (getClass().getClassLoader().getResource("statement.xls"));
    assertThat(spreadsheet, containsText("Statement"));
  }
}
```
