package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/add")
    public String Add(@RequestParam(value="first")double first, @RequestParam(value="second")double second){
        return String.valueOf((first+second));
    }

    @GetMapping("/sub")
    public String Sub(@RequestParam(value="first")double first, @RequestParam(value="second")double second){
        return String.valueOf((first-second));
    }

    @GetMapping("/multi")
    public String Multi(@RequestParam(value="first")double first, @RequestParam(value="second")double second){
        return String.valueOf((first*second));
    }

    @GetMapping("/div")
    public String Division(@RequestParam(value="first")double first, @RequestParam(value="second")double second){
        if(second==0){
            return "E";
        }else{
            return String.valueOf((first/second));
        }
    }
    @GetMapping("/square")
    public String Squ(@RequestParam(value="num")double num){
        return String.valueOf(Math.pow(num,2));
    }


    @GetMapping("/over")
    public String Over(@RequestParam(value="num")double num){
        if(num==0){
            return "E";
        }
        return String.valueOf(1/num);
    }

    @GetMapping("/root")
    public String Root(@RequestParam(value="num")double num){
        if(num<0){
            return "E";
        }else{
            return String.valueOf(Math.sqrt(num));
        }
    }
}
