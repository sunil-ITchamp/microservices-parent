package com.sk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class SpringCloudStreamTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamTestApplication.class, args);
    }

    //data Supplier
//    @Bean
//    public Supplier<String> mySupplier(){
//        //List<String> stringList = Arrays.asList("hello","world","wow","spring-streams");
//        return () -> "hello from spring streams";
//
//    }

    //data Supplier - in the form of Function
    @Bean
    public Function<String,String> functionDataSupplier(){
        return (input) -> input;
    }

    @Bean
    public Function<String, String> myProcessor(){
       return (input)-> {
           System.out.println("Inside Function " + input.toUpperCase());
           return input.toUpperCase();
       };
    }

    @Bean
    public Consumer<String> myConsumer(){
        return (input) -> System.out.println("Consumer -> " + input);
    }
}
