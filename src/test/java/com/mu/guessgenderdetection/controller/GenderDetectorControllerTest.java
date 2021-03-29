package com.mu.guessgenderdetection.controller;

import com.mu.guessgenderdetection.enumeration.GenderEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GenderDetectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnMaleGenderForV1() throws Exception {

        this.mockMvc.perform(get("/gender/guess/Jacob/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(GenderEnum.MALE))));
    }

    @Test
    public void shouldReturnFemaleGenderForV2() throws Exception {

        this.mockMvc.perform(get("/gender/guess/Jacob Samantha Grace"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(GenderEnum.FEMALE))));
    }

}