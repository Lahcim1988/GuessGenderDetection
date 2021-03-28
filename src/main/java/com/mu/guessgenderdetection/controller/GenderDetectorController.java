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

    @RequestMapping(value = "/single/{name}", method = RequestMethod.GET)
    public GenderEnum genderByFirstName(@PathVariable String name){
        return genderDetector.result_V1(name);
    }

    @RequestMapping(value = "/multi/{names}", method = RequestMethod.GET)
    public GenderEnum genderByMultiNames(@PathVariable String names){
        return genderDetector.result_V2(names);
    }

    @RequestMapping(value = "/all/male", method = RequestMethod.GET)
    public List<String> allMaleNames(){
        return genderDetector.allMale();
    }

    @RequestMapping(value = "/all/female", method = RequestMethod.GET)
    public List<String> allFemaleNames(){
        return genderDetector.allFemale();
    }

}
