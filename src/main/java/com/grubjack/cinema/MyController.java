package com.example.demo;

import com.example.demo.entities.Schedule;
import com.example.demo.services.TimeTrackingService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
//@Data
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MyController {

//    @Autowired
//    UserRepository userRepository;
    private final TimeTrackingService trackingService;

    @Autowired
    public MyController(TimeTrackingService trackingService) {
        this.trackingService = trackingService;
    }


    @GetMapping(value = "/schedule")
    public ResponseEntity<?> getAllSchedule(){
        return ResponseEntity.ok(trackingService.getSchedule());
    }


    @RequestMapping("/hello")
    @ResponseBody
    ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
        //return "index.html";

    }




}
