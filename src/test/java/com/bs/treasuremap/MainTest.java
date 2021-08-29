package com.bs.treasuremap;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void should_write_expected_result_when_one_adventurer() throws Exception {
        // Given
        String source = "src/test/resources/example1.txt";
        // When
        Main.main(new String[]{source});
        // Then
        List<String> actual = Files.readAllLines(Paths.get("src/test/resources/example1.txt.result"));
        List<String> expected = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 1 - 3 - 2",
                "A - Lara - 0 - 3 - S - 3"
        );
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void should_write_expected_result_when_two_adventurers() throws Exception {
        // Given
        String source = "src/test/resources/example2.txt";
        // When
        Main.main(new String[]{source});
        // Then
        List<String> actual = Files.readAllLines(Paths.get("src/test/resources/example2.txt.result"));
        List<String> expected = List.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 1 - 3 - 1",
                "A - Lara - 1 - 3 - S - 2",
                "A - Indiana - 0 - 3 - S - 2"
        );
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}