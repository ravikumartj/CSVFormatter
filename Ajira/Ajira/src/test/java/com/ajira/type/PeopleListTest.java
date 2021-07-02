package com.ajira.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ajira.config.ConfigTest.INVALID_DIR_PATH;
import static com.ajira.config.ConfigTest.INVALID_FILE_PATH;
import static com.ajira.config.ConfigTest.OUTPUT_FILE_PATH;
import static com.ajira.config.ConfigTest.PEOPLE_FILE_PATH;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PeopleListTest {

    @Test
    @DisplayName("Test method to check Exception thrown for default constructor()")
    void testDefaultConstructor_whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new PeopleList();
        });

        String expectedMessage = "Please invoke Parametrized Constructor with params String inputFile, String outputFile";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for constructor() null check")
    void testConstructor_invalidParams_whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new PeopleList(null, null);
        });

        String expectedMessage = "Please pass inputFile, outputFile";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for read invalid file")
    void testRead_invalidFile_whenExceptionThrown_thenAssertionSucceeds() {
        peopleList = new PeopleList(INVALID_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            peopleList.read();
        });

        String expectedMessage = "FileNotFoundException People read";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for read invalid file content")
    void testRead_invalidFileContent_whenExceptionThrown_thenAssertionSucceeds() {
        peopleList = new PeopleList(INVALID_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            peopleList.read();
        });

        String expectedMessage = "FileNotFoundException People read";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for convert null check")
    void testConvert_invalidFile_whenExceptionThrown_thenAssertionSucceeds() {
        peopleList = new PeopleList(INVALID_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            peopleList.convert(null);
        });

        String expectedMessage = "NullPointerException People convert";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for write invalid file")
    void testWrite_invalidFile_whenExceptionThrown_thenAssertionSucceeds() {
        peopleList = new PeopleList(PEOPLE_FILE_PATH, INVALID_DIR_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            peopleList.write();
        });

        String expectedMessage = "IOException People write";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    public void testSuccess(){
        peopleList = new PeopleList(PEOPLE_FILE_PATH, INVALID_DIR_PATH);
        assertDoesNotThrow(() -> peopleList.read());
    }

    private PeopleList peopleList;
}
