package com.mu.guessgenderdetection.service;

import com.mu.guessgenderdetection.enumeration.GenderEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GenderDetectorTest {

     private GenderDetector genderDetector;

     @BeforeEach
     void setGenderDetector(){
         genderDetector = new GenderDetector();
     }

     @Test
    void shouldReturnMaleFromV1(){
         String providedNames = "Jacob Samantha David";
         GenderEnum expectedResult = GenderEnum.MALE;
         GenderEnum actual = genderDetector.firstTokenToCheck(providedNames);
         Assertions.assertEquals(expectedResult, actual);
     }

     @Test
    void shouldReturnFemaleFromV2(){
         String providedNames = "Jacob Samantha Grace";
         GenderEnum expectedResult = GenderEnum.FEMALE;
         GenderEnum actual = genderDetector.allTokensToCheck(providedNames);
         Assertions.assertEquals(expectedResult, actual);
     }

     @Test
    void shouldReturnInconclusive(){
         String providedNames = "Jacob Samantha NoName";
         GenderEnum expectedResult = GenderEnum.INCONCLUSIVE;
         GenderEnum actual = genderDetector.allTokensToCheck(providedNames);
         Assertions.assertEquals(expectedResult, actual);
     }

}