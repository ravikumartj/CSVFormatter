package com.ajira.config;

import com.ajira.type.Transform;
import com.ajira.type.EmployeeList;
import com.ajira.type.FriendList;
import com.ajira.type.PeopleList;
import com.ajira.type.Type;
import com.ajira.type.StudentList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Config {

    public void processConfig() {

        File f=new File(configFile);
        System.out.println(f.exists());
        try  (BufferedReader br = new BufferedReader(new FileReader(configFile))){
            String line;
            String[] dataLine;
            headerLine = br.readLine();
            if ((line = br.readLine()) != null) {
                dataLine = line.split(Transform.COMMA_DELIMITER);
                invokeType(Integer.parseInt(dataLine[0]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("FileNotFoundException process config");
        }catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException | NullPointerException process config");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception process config");
        }
    }

    public void invokeType(int t) {
        switch (t) {

            case Transform.TRANSFORM_CHANGE: {
                Type type = new StudentList(inputFile, outputFile);
                type.transform();
                break;
            }
            case Transform.TRANSFORM_REMOVE: {
                Type type = new FriendList(inputFile, outputFile);
                type.transform();
                break;
            }
            case Transform.TRANSFORM_MERGE: {
                Type type = new EmployeeList(inputFile, outputFile);
                type.transform();
                break;
            }
            case Transform.TRANSFORM_CREATE: {
                Type type = new PeopleList(inputFile, outputFile);
                type.transform();
                break;
            }
            default: {
                throw new RuntimeException("Type Not Defined");
            }
        }
    }

    public Config(String inputFile, String configFile, String outputFile) {
        if(null == inputFile || null == configFile || null == outputFile){
            throw new RuntimeException("Please pass inputFile, configFile, outputFile");
        }
        this.inputFile = inputFile;
        this.configFile = configFile;
        this.outputFile = outputFile;
    }

    public Config() {
        throw new RuntimeException("Please invoke Parametrized Constructor " +
                "Config String inputFile, String configFile, String outputFile");
    }

    public static void main(String[] args) {
        if(null == args || NO_OF_FILES != args.length){
            throw new RuntimeException("Please pass args inputFile, configFile, outputFile");
        }
        Config config = new Config(args[0], args[1], args[2]);
        config.processConfig();
    }

    private static final int NO_OF_FILES = 3;
    private String inputFile;
    private String configFile;
    private String outputFile;

    private String headerLine;
}
