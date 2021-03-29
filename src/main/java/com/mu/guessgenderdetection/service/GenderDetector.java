package com.mu.guessgenderdetection.service;

import com.mu.guessgenderdetection.enumeration.GenderEnum;

public interface GenderDetector {

    GenderEnum firstTokenToCheck(String providedName);
    GenderEnum allTokensToCheck(String providedNames);

}
