package com.mu.guessgenderdetection.service;

import com.mu.guessgenderdetection.enumeration.GenderEnum;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Michal
 * @version 1.4
 * GenderDetector: Class
 * */

@Service
public class GenderDetectorImpl implements GenderDetector{

    private FileInputStream inputStream = null;
    private Scanner sc = null;

    private final String MALE_PATH = ResourceBundle.getBundle("application").getString("file.male.source");
    private final String FEMALE_PATH = ResourceBundle.getBundle("application").getString("file.female.source");

    public GenderEnum firstTokenToCheck(String providedName){
        String getFirstName = splitStringToTokens(providedName).get(0);
        return getGenderType(getFirstName);
    }

    private GenderEnum getGenderType(String firstName){
        return ifGenderExist(firstName, MALE_PATH) ? GenderEnum.MALE : ifGenderExist(firstName, FEMALE_PATH) ? GenderEnum.FEMALE : GenderEnum.INCONCLUSIVE;
    }

    public GenderEnum allTokensToCheck(String providedNames){

        int countMale = 0;
        int countFemale = 0;

        List<String> list = splitStringToTokens(providedNames);

        for (String n : list){
            if(ifGenderExist(n, MALE_PATH)){
                countMale++;
                continue;
            }
            if(ifGenderExist(n, FEMALE_PATH)){
                countFemale++;
            }
        }
        return getGenderTypeForAllTokens(countMale, countFemale);
    }

    private GenderEnum getGenderTypeForAllTokens(int countMale, int countFemale){
        return (countMale > countFemale) ? GenderEnum.MALE : (countMale < countFemale) ? GenderEnum.FEMALE : GenderEnum.INCONCLUSIVE;
    }

    private List<String> splitStringToTokens(String name){
        List<String> names = new ArrayList<>();
        StringTokenizer myToken = new StringTokenizer(name, " ");
        while (myToken.hasMoreTokens()){
            names.add(myToken.nextToken());
        }
        return names;
    }

    private boolean ifGenderExist(String name, String path){
        boolean ifExist = false;

        try(Scanner sc = new Scanner(new FileInputStream(path), "UTF-8")){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.equals(name)){
                    ifExist = true;
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return ifExist;
    }
}
