# XLS Test
Excel testing library

Be sure that your code generates correct Excel!

## How to use

```java
import com.codeborne.xlstest.XLS;
import static com.codeborne.xlstest.XLS.*;
import static org.junit.Assert.assertThat;

public class ExcelContainsTextTest {
  @Test
  public void canAssertThatXlsContainsText() {
    XLS spreadsheet = new XLS(getClass().getClassLoader().getResource("statement.xls"));
    assertThat(spreadsheet, containsText("Statement"));
  }
}
```

## How to start

If you use **Maven**, add the following dependency to pom.xml:

```xml
  <dependency>
    <groupId>com.codeborne</groupId>
    <artifactId>xls-test</artifactId>
    <version>1.1</version>
  </dependency>
```

If you use **Gradle**, add the following dependency to build.gradle:

```groovy
  testCompile 'com.codeborne:xls-test:1.1'
```

## How to contribute

You are welcome to suggest your features and fixes!

Just fork the [xls-test](https://github.com/codeborne/xls-test) and create pull request. 
Any contribution is important!

Become part of open-source community!
