package com.ajira.type;

import com.ajira.config.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class StudentList extends Type implements Transform {

    @Override
    public void read() {
        try  (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))){
            studentsList = new ArrayList<Student>();
            String line;
            String[] dataLine;
            headerLine = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                dataLine = line.split(Pattern.quote(PIPE_DELIMITER));
                convert(dataLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("FileNotFoundException Student read");
        }catch (IOException  | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException  | NullPointerException Student read");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception Student read");
        }
    }

    public void convert(String[] dataLine) {
        try {
            Student student = new Student();
            student.setName(dataLine[0]);
            //Instantiating the SimpleDateFormat class
            SimpleDateFormat formatter = new SimpleDateFormat(CURRENT_DATE_PATTERN);
            //Parsing the given String to Date object
            Date date = formatter.parse(dataLine[1]);
            student.setTransformedDate(new SimpleDateFormat(DESIRED_DATE_PATTERN).format(date));
            studentsList.add(student);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("ParseException Student convert");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception Student convert");
        }
    }

    @Override
    public void write() {
        try  (FileWriter fileWriter = new FileWriter(outputFile)){
            fileWriter.append(headerLine);
            fileWriter.append(NEW_LINE);

            for (Student students : studentsList) {

                fileWriter.append(students.getName());
                fileWriter.append(PIPE_DELIMITER);
                fileWriter.append(students.getTransformedDate());
                fileWriter.append(NEW_LINE);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException Student write");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception Student write");
        }
    }

    public StudentList() {
        super();
    }

    public StudentList(String inputFile, String outputFile) {
        super(inputFile, outputFile);
    }

    class Student {

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTransformedDate() {
            return transformedDate;
        }

        public void setTransformedDate(String transformedDate) {
            this.transformedDate = transformedDate;
        }

        private String name;
        private String transformedDate;
    }

    private String headerLine;
    private static final String CURRENT_DATE_PATTERN = "yyyy-MM-dd";

    private static final String DESIRED_DATE_PATTERN = "MMM dd, YYYY";

    private ArrayList<Student> studentsList;
}
