package com.mu.guessgenderdetection.service;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Michal
 * @version 1.4
 * GenderDetector: Class
 * */

@Service
public class GenderListImpl implements GenderList {

    private final String MALE_PATH = ResourceBundle.getBundle("application").getString("file.male.source");
    private final String FEMALE_PATH = ResourceBundle.getBundle("application").getString("file.female.source");

    public List<String> allNames(){
        return Stream.concat(allTokens(MALE_PATH).stream(), allTokens(FEMALE_PATH).stream()).collect(Collectors.toList());
    }

    private List<String> allTokens(String path){
        List<String> listOfAllAvailableTokens = new ArrayList<>();
        try(Scanner sc = new Scanner(new FileInputStream(path), "UTF-8")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                listOfAllAvailableTokens.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfAllAvailableTokens;
    }
}
