package com.ajira.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FriendList extends Type implements Transform {

    @Override
    public void read() {
        
        try  (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))){
            friendsList = new ArrayList<Friend>();
            String line;
            String[] dataLine;
            headerLine = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                dataLine = line.split(Pattern.quote(PIPE_DELIMITER));
                convert(dataLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("FileNotFoundException Friend read");
        }catch (IOException  | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException  | NullPointerException Friend read");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception Friend read");
        }
    }

    @Override
    public void convert(String[] dataLine) {
        try {
            int age = Integer.parseInt(dataLine[1]);
            if (AGE_LIMIT >= age) {
                Friend friend = new Friend();
                friend.setName(dataLine[0]);
                friend.setAge(dataLine[1]);
                friendsList.add(friend);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
            throw new RuntimeException("NullPointerException Friend convert");
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Exception Friend convert");
        }
    }

    @Override
    public void write() {
        try  (FileWriter fileWriter = new FileWriter(outputFile)){
            fileWriter.append(headerLine);
            fileWriter.append(NEW_LINE);

            for (Friend friend : friendsList) {

                fileWriter.append(friend.getName());
                fileWriter.append(PIPE_DELIMITER);
                fileWriter.append(friend.getAge());
                fileWriter.append(NEW_LINE);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException Friend write");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception Friend write");
        }
    }

    public FriendList() {
        super();
    }

    public FriendList(String inputFile, String outputFile) {
        super(inputFile, outputFile);
    }

    class Friend {

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

        private String name;
        private String age;
    }

    private String headerLine;

    private ArrayList<Friend> friendsList;

    private final static int AGE_LIMIT = 50;
}
