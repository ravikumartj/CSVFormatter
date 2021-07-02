package com.ajira.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EmployeeList extends Type implements Transform {

    @Override
    public void read() {
        try  (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))){
            employeesList = new ArrayList<Employee>();
            String line;
            String[] dataLine;
            headerLine = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                dataLine = line.split(Pattern.quote(PIPE_DELIMITER));
                convert(dataLine);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("FileNotFoundException Employee read");
        }catch (IOException  | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException  | NullPointerException Employee read");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception Employee read");
        }
    }

    @Override
    public void convert(String[] dataLine) {
        try {
            Employee employee = new Employee();
            employee.setName(dataLine[0] + COMMA_DELIMITER + SPACE + dataLine[1]);
            employeesList.add(employee);
        }catch(NullPointerException e){
            e.printStackTrace();
            throw new RuntimeException("NullPointerException Employee convert");
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Exception Employee convert");
        }
    }

    @Override
    public void write() {
        try  (FileWriter fileWriter = new FileWriter(outputFile)){
            fileWriter.append(DEFAULT_HEADER);
            fileWriter.append(NEW_LINE);

            for (Employee employee : employeesList) {
                fileWriter.append(employee.getName());
                fileWriter.append(NEW_LINE);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException Employee write");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception Employee write");
        }
    }

    public EmployeeList() {
        super();
    }

    public EmployeeList(String inputFile, String outputFile) {
        super(inputFile, outputFile);
    }

    class Employee {

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }

    private String headerLine;
    private final static String DEFAULT_HEADER = "name";

    private ArrayList<Employee> employeesList;
}
