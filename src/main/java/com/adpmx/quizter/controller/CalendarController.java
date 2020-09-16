package com.adpmx.quizter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class CalendarController {
    @RequestMapping(value = {"/calendar"})
    public String calendar(){
        return "calendar/calendar";
    }
}
