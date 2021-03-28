package com.mu.guessgenderdetection.services;

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
         String expectedResult = "male";
         String actual = genderDetector.result_V1(providedNames);
         Assertions.assertEquals(expectedResult, actual);
     }

     @Test
    void shouldReturnFemaleFromV2(){
         String providedNames = "Jacob Samantha Grace";
         String expectedResult = "female";
         String actual = genderDetector.result_V2(providedNames);
         Assertions.assertEquals(expectedResult, actual);
     }

     @Test
    void shouldReturnInconclusive(){
         String providedNames = "Jacob Samantha NoName";
         String expectedResult = "INCONCLUSIVE";
         String actual = genderDetector.result_V2(providedNames);
         Assertions.assertEquals(expectedResult, actual);
     }

}