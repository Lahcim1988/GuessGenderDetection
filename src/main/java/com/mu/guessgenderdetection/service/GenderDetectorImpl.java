package com.mu.guessgenderdetection.service;

import com.mu.guessgenderdetection.enumeration.GenderEnum;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Michal
 * @version 1.2
 * GenderDetector: Class
 * */

@Service
public class GenderDetectorImpl implements GenderDetector{

    private FileInputStream inputStream = null;
    private Scanner sc = null;

    private final String MALE_PATH = ResourceBundle.getBundle("application").getString("file.male.source");
    private final String FEMALE_PATH = ResourceBundle.getBundle("application").getString("file.female.source");

    public GenderEnum firstTokenToCheck(String providedName){
        String name = tokens(providedName).get(0);
        return allTokensToCheck(name);
    }

    public GenderEnum allTokensToCheck(String providedNames){

        int countMale = 0;
        int countFemale = 0;

        List<String> list = tokens(providedNames);

        for (String n : list){
            if(ifGenderExist(n, MALE_PATH)){
                countMale++;
            }
            if(ifGenderExist(n, FEMALE_PATH)){
                countFemale++;
            }
        }
        if(countMale > countFemale){
            return GenderEnum.MALE;
        }else if(countMale < countFemale){
            return GenderEnum.FEMALE;
        }else {
            return GenderEnum.INCONCLUSIVE;
        }
    }

    private List<String> tokens(String name){
        List<String> names = new ArrayList<>();
        StringTokenizer myToken = new StringTokenizer(name, " ");

        while (myToken.hasMoreTokens()){
            names.add(myToken.nextToken());
        }
        return names;
    }

    private boolean ifGenderExist(String name, String path){
        boolean ifExist = false;

        try{
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.equals(name)){
                    ifExist = true;
                    break;
                }
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
        return ifExist;
    }
}
