package com.sk;

import com.sk.com.sk.model.Author;
import com.sk.com.sk.model.AuthorRepository;
import com.sk.com.sk.model.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application
{
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AuthorService authorService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(AuthorRepository repository) {
        return (args) -> {
            // save a few customers
//            repository.save(new Author("Jack", "Bauer", "jack@gmail.com"));
//            repository.save(new Author("Chloe", "O'Brian", "chloe@gmail.com"));
//
//            repository.findAll().forEach(record-> System.out.println(record.getFirstName()));
//            System.out.println(" ---- 2nd call -----");
//            repository.findAll().forEach(record-> System.out.println(record.getFirstName()));
//            System.out.println(" ---- 3rd call ---- ");
//            repository.findAll().forEach(record-> System.out.println(record.getFirstName()));
//
//            this.businessMethod();

        };
    }

    @Transactional
    public void businessMethod(){
        System.out.println(" ---- 1st call -----");
        authorService.getAuthorById(1);
        System.out.println(" ---- 2nd call -----");
        authorService.getAuthorById(1);
        System.out.println(" ---- 3rd call -----");
        authorService.getAuthorById(1);
    }
}
