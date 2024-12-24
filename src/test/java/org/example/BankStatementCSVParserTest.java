package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

class BankStatementCSVParserTest {
    private final BankStatementCSVParser statementCSVParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "25-12-2024,200,Amazon Prime";

        final BankTransaction result = statementCSVParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(
                LocalDate.of(2024, Month.DECEMBER, 25),
                200,
                "Amazon Prime"
        );

        final double tolerance = 0.0d;

        assertEquals(expected.getDate(), result.getDate());
        assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseTwoCorrectLines() throws Exception {
        final String line1 = "25-12-2024,200,Amazon Prime";
        final String line2 = "01-01-2025,250,Netflix";

        final List<String> lines = Arrays.asList(line1, line2);

        List<BankTransaction> result = statementCSVParser.parseLinesFrom(lines);

        BankTransaction expected1 = new BankTransaction(
                LocalDate.of(2024, Month.DECEMBER, 25),
                200,
                "Amazon Prime"
        );

        BankTransaction expected2 = new BankTransaction(
                LocalDate.of(2025, Month.JANUARY, 1),
                250,
                "Netflix"
        );

        List<BankTransaction> expected = Arrays.asList(expected1, expected2);

        final double tolerance = 0.0d;

        assertEquals(expected.getFirst().getDate(), result.getFirst().getDate());
        assertEquals(expected.getFirst().getAmount(), result.getFirst().getAmount(), tolerance);
        assertEquals(expected.getFirst().getDescription(), result.getFirst().getDescription());

        assertEquals(expected.get(1).getDate(), result.get(1).getDate());
        assertEquals(expected.get(1).getAmount(), result.get(1).getAmount(), tolerance);
        assertEquals(expected.get(1).getDescription(), result.get(1).getDescription());
    }
}