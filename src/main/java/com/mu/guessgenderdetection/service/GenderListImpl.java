package com.mu.guessgenderdetection.service;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Michal
 * @version 1.2
 * GenderDetector: Class
 * */

@Service
public class GenderListImpl implements GenderList {

    private FileInputStream inputStream = null;
    private Scanner sc = null;

    private final String MALE_PATH = ResourceBundle.getBundle("application").getString("file.male.source");
    private final String FEMALE_PATH = ResourceBundle.getBundle("application").getString("file.female.source");

    public List<String> allNames(){
        return Stream.concat(allTokens(MALE_PATH).stream(), allTokens(FEMALE_PATH).stream()).collect(Collectors.toList());
    }

    private List<String> allTokens(String path){
        List<String> listOfAllAvailableTokens = new ArrayList<>();
        try{
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                listOfAllAvailableTokens.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sc != null){
                sc.close();
            }
        }
        return listOfAllAvailableTokens;
    }
}
