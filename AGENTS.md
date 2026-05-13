# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

`xls-test` is a small Java library that exposes Hamcrest matchers for asserting on the contents of Excel files (`.xls` and `.xlsx`). It is published to Maven Central as `com.codeborne:xls-test`.

## Build & test commands

The build uses the Gradle wrapper. The default task chain is `test publishToMavenLocal`.

- `./gradlew test` — run the JUnit 4 test suite (matches `com/codeborne/xlstest/**`).
- `./gradlew test --tests com.codeborne.xlstest.matchers.ContainsTextTest` — run a single test class.
- `./gradlew test --tests '*ContainsTextTest.canAssertThatXlsContainsText'` — run a single test method.
- `./gradlew jacocoTestReport` — generate coverage reports in `build/reports/jacoco/`.
- `./gradlew publishToMavenLocal` — install the artifact to `~/.m2/repository`.
- `./gradlew` (no args) — runs `test` + `publishToMavenLocal` (default tasks).

Java toolchain is JDK 17, but bytecode targets Java 1.8 (`sourceCompatibility` / `targetCompatibility` in `gradle/compilation.gradle`). Don't raise these without discussion — the library is meant to remain usable on Java 8 consumers.

## Architecture

The public API is intentionally tiny:

- `com.codeborne.xlstest.XLS` (`src/main/java/com/codeborne/xlstest/XLS.java`) — wraps an Apache POI `Workbook`. Constructors accept `File`, `URL`, `URI`, `byte[]`, or `InputStream`; they all funnel through a private constructor that reads bytes into memory and calls `WorkbookFactory.create(...)`, so both `.xls` (HSSF) and `.xlsx` (XSSF) are handled transparently. `XLS` also exposes the static matcher factories `containsText`, `containsRow`, `doesNotContainText`.
- `com.codeborne.xlstest.matchers.XLSMatcher` — package-private abstract base extending Hamcrest's `TypeSafeMatcher<XLS>`. Subclasses (`ContainsText`, `DoesNotContainText`, `ContainsRow`) iterate every sheet/row/cell and rely on two shared helpers:
  - `reduceSpaces(...)` collapses whitespace (including NBSP ` `) so text matching is tolerant of Excel formatting.
  - `getFormattedCellValue(Cell)` formats numeric cells using their `DataFormatString` when it matches one of a small whitelist of POI built-in formats (indexes 1, 2, 3, 4, 9, 10); otherwise it falls back to the default `DecimalFormat`. This is the source of truth for "what string does a cell render as" and is shared by every matcher — change it carefully.
  - `describeMismatchSafely` dumps the whole workbook as tab-separated text on assertion failure, so test output shows the actual sheet contents.

When adding a new matcher, extend `XLSMatcher` so it inherits the dump-on-failure behaviour and the numeric/whitespace normalization. Expose it via a static factory on `XLS` to keep the user-facing import surface (`import static com.codeborne.xlstest.XLS.*`) consistent.

## Releases & CI

- GitHub Actions (`.github/workflows/test.yml`) runs `./gradlew clean test --info --rerun-tasks` on JDK 17 for every push to `main` and every PR, and uploads `build/reports/` on failure.
- Dependabot PRs are auto-merged by the same workflow (uses `DEPENDABOT_PAT`, rebase strategy, minor-version target).
- Coveralls upload runs only in CI (`tasks.coveralls.onlyIf { System.env.'CI' }`).
- Publishing to Maven Central is configured in `gradle/publish.gradle` via the `com.vanniktech.maven.publish` plugin and is gated on the `signing.keyId` Gradle property being present.
