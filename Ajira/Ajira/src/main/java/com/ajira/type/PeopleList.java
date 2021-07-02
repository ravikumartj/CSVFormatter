package com.ajira.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PeopleList extends Type implements Transform {

    @Override
    public void read() {
        try  (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))){
            peoplesList = new ArrayList<People>();
            String line;
            String[] dataLine;
            headerLine = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                dataLine = line.split(Pattern.quote(PIPE_DELIMITER));
                convert(dataLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("FileNotFoundException People read");
        }catch (IOException  | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException  | NullPointerException People read");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception People read");
        }
    }

    @Override
    public void convert(String[] dataLine) {
        try {
            People people = new People();
            people.setName(dataLine[0]);
            people.setAge(dataLine[1]);
            if (VOTING_AGE < Integer.parseInt(dataLine[1])) {
                people.setEligibleForVoting(ELIGIBLE_FOR_VOTING);
            } else {
                people.setEligibleForVoting(NOT_ELIGIBLE_FOR_VOTING);
            }
            peoplesList.add(people);
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new RuntimeException("NullPointerException People convert");
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Exception People convert");
        }
    }

    @Override
    public void write() {
        try  (FileWriter fileWriter = new FileWriter(outputFile)){
            fileWriter.append(headerLine);
            fileWriter.append(PIPE_DELIMITER);
            fileWriter.append(ADDITIONAL_HEADER);
            fileWriter.append(NEW_LINE);

            for (People people : peoplesList) {

                fileWriter.append(people.getName());
                fileWriter.append(PIPE_DELIMITER);
                fileWriter.append(people.getAge());
                fileWriter.append(PIPE_DELIMITER);
                fileWriter.append(people.getEligibleForVoting());
                fileWriter.append(NEW_LINE);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException People write");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception People write");
        }
    }

    public PeopleList() {
        super();
    }

    public PeopleList(String inputFile, String outputFile) {
        super(inputFile, outputFile);
    }

    class People {

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEligibleForVoting() {
            return eligibleForVoting;
        }

        public void setEligibleForVoting(String eligibleForVoting) {
            this.eligibleForVoting = eligibleForVoting;
        }

        private String name;
        private String age;
        private String eligibleForVoting;
    }

    private String headerLine;

    private ArrayList<People> peoplesList;
    private final static String ADDITIONAL_HEADER = "eligible_for_voting";

    private final static String ELIGIBLE_FOR_VOTING = "T";
    private final static String NOT_ELIGIBLE_FOR_VOTING = "F";

    private final static int VOTING_AGE = 18;

}
