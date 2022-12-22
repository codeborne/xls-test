[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.codeborne/xls-test/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.codeborne/xls-test)
[![Coverage Status](https://coveralls.io/repos/github/codeborne/xls-test/badge.svg?branch=main)](https://coveralls.io/github/codeborne/xls-test?branch=main)

# XLS Test
Excel testing library

Be sure that your code generates correct Excel!

## How to use

```java
import com.codeborne.xlstest.XLS;
import static com.codeborne.xlstest.XLS.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
    <version>1.7.0</version>
  </dependency>
```

If you use **Gradle**, add the following dependency to build.gradle:

```groovy
  testCompile 'com.codeborne:xls-test:1.7.0'
```

## How to contribute

You are welcome to suggest your features and fixes!

Just fork the [xls-test](https://github.com/codeborne/xls-test) and create pull request.
Any contribution is important!

**Become part of open-source community!**

# Thanks

Many thanks to these incredible tools that help us to create open-source software:

![Intellij IDEA](https://cloud.google.com/tools/images/icon_IntelliJIDEA.png)

![YourKit Java profiler](http://selenide.org/images/yourkit.png)

# License
xls-test is open-source project and distributed under [MIT](http://choosealicense.com/licenses/mit/) license
