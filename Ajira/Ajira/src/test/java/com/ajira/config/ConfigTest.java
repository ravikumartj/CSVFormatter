package com.ajira.config;

import com.ajira.config.Config;
import com.ajira.type.StudentList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigTest {

    @Test
    @DisplayName("Test method to check Exception thrown for default constructor()")
    void testDefaultConstructor_whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Config();
        });

        String expectedMessage = "Please invoke Parametrized Constructor Config String inputFile, String configFile, String outputFile";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for constructor() null check")
    void testConstructor_invalidParams_whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Config(null, null, null);
        });

        String expectedMessage = "Please pass inputFile, configFile, outputFile";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for invokeType invalid type")
    void testInvokeType_invalidType_whenExceptionThrown_thenAssertionSucceeds() {
        config = new Config(STUDENTS_FILE_PATH, CONFIG_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            config.invokeType(5);
        });

        String expectedMessage = "Type Not Defined";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }
    @Test
    @DisplayName("Test method to check Exception thrown for processConfig invalid file content")
    void testProcessConfig_invalidFileContent_whenExceptionThrown_thenAssertionSucceeds() {
        config =  new Config(STUDENTS_FILE_PATH, INVALID_CONFIG_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            config.processConfig();
        });

        String expectedMessage = "Exception process config";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown main() when args is null")
    void testMain_nullParams_whenExceptionThrown_thenAssertionSucceeds() {
        config = new Config(STUDENTS_FILE_PATH, CONFIG_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            config.main(null);
        });

        String expectedMessage = "Please pass args inputFile, configFile, outputFile";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown main() when args is invalid length")
    void testMain_invalidLength_Params_whenExceptionThrown_thenAssertionSucceeds() {
        config = new Config(STUDENTS_FILE_PATH, CONFIG_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            config.main(new String[]{""});
        });

        String expectedMessage = "Please pass args inputFile, configFile, outputFile";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    @DisplayName("Test method to check Exception thrown for processConfig invalid config")
    void testProcessConfig_invalidConfig_whenExceptionThrown_thenAssertionSucceeds() {
        config = new Config(STUDENTS_FILE_PATH, INVALID_FILE_PATH, OUTPUT_FILE_PATH);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            config.processConfig();
        });

        String expectedMessage = "FileNotFoundException process config";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.matches(expectedMessage));
    }

    @Test
    public void testSuccess(){
        config = new Config(STUDENTS_FILE_PATH, CONFIG_FILE_PATH, OUTPUT_FILE_PATH);
        assertDoesNotThrow(() -> config.processConfig());
    }

    private Config config;
    public static final String INVALID_DIR_PATH = "C:/ajira/res/csv/Invalid";
    public static final String INVALID_FILE_PATH = "C:/ajira/res/csv/Invalid.csv";
    public static final String STUDENTS_FILE_PATH = "C:/ajira/res/csv/Students.csv";
    public static final String FRIENDS_FILE_PATH = "C:/ajira/res/csv/Friends.csv";
    public static final String EMPLOYEES_FILE_PATH = "C:/ajira/res/csv/Employees.csv";
    public static final String PEOPLE_FILE_PATH = "C:/ajira/res/csv/People.csv";
    public static final String CONFIG_FILE_PATH = "C:/ajira/res/csv/Config.csv";
    public static final String INVALID_CONFIG_FILE_PATH = "C:/ajira/res/csv/InvalidConfig.csv";
    public static final String OUTPUT_FILE_PATH = "C:/ajira/res/csv/Output.csv";
}
