package com.sk.test;

import com.sk.com.sk.model.Author;
import com.sk.com.sk.model.AuthorService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
public class AuthorTest {

    @Autowired
    AuthorService authorService;

    private Author readAuthor(int id) {
        //log.info(String.format("query author #%d", id));
        Optional<Author> authorOptional = authorService.getAuthorById(id);
        assertTrue(authorOptional.isPresent());
        log.info("Author found");
        return authorOptional.get();
    }

    @Test
    @Transactional
    void testReadAuthor() {
        Author author = readAuthor(1);
        //assert that the author is Jack
        assertEquals("Jack", author.getFirstName());
    }

    @Test
    @Transactional
    void testReadPlanetThreeTimes() {
        for (int i=1; i <= 4; i++){
            log.info(" ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ Calling testReadAuthor " + i + " time ");
            testReadAuthor();
        }
    }
}