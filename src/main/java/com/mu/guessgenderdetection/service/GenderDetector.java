package com.mu.guessgenderdetection.service;

import com.mu.guessgenderdetection.enumeration.GenderEnum;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Michal
 * @version 1.1
 * GenderDetector: Class
 * */

@Service
public class GenderDetector {

    private FileInputStream inputStream = null;
    private Scanner sc = null;

    private final String MALE_PATH = "file.male.source";
    private final String FEMALE_PATH = "file.female.source";

    private final String malePath = ResourceBundle.getBundle("application").getString(MALE_PATH);
    private final String femalePath = ResourceBundle.getBundle("application").getString(FEMALE_PATH);

    public GenderEnum result_V1(String providedName){
        String name = tokens(providedName).get(0);
        return result_V2(name);
    }

    public GenderEnum result_V2(String providedName){

        int countMale = 0;
        int countFemale = 0;

        List<String> list = tokens(providedName);

        for (String n : list){
            if(ifGenderExist(n, malePath)){
                countMale++;
            }
            if(ifGenderExist(n, femalePath)){
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

    public List<String> tokens(String name){
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

    public List<String> allMale(){
        return allTokens(malePath);
    }

    public List<String> allFemale(){
        return allTokens(femalePath);
    }

    private List<String> allTokens(String path){
        List<String> arrayList = new ArrayList<>();
        try{
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                arrayList.add(line);
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
        return arrayList;
    }
}
