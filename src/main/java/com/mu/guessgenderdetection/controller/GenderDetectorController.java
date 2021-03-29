package com.mu.guessgenderdetection.controller;

import com.mu.guessgenderdetection.enumeration.GenderEnum;
import com.mu.guessgenderdetection.service.GenderDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/gender", produces = "application/json")
public class GenderDetectorController {

    private final GenderDetector genderDetector;

    @Autowired
    public GenderDetectorController(GenderDetector genderDetector){
        this.genderDetector = genderDetector;
    }

    @RequestMapping(value = {"/guess/{names}/{algo}", "/guess/{names}"}, method = RequestMethod.GET)
    public GenderEnum guessGenderByProvidedNamesAndAlgoVariant(@PathVariable(required = false) Long algo, @PathVariable String names){
        if(algo != null){
            return genderDetector.firstTokenToCheck(names);
        }else{
            return genderDetector.allTokensToCheck(names);
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<String> allNames(){
        return genderDetector.allNames();
    }

}
