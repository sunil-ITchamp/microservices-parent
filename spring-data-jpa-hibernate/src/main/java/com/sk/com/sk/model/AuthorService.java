package com.sk.com.sk.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Cacheable(cacheNames = "authorCache", cacheManager = "caffeineCacheManager")
    public Optional<Author> getAuthorById(Integer id) {
        //System.out.println(" ---- getAuthorById ----- " + id);
        return authorRepository.findById(id);
    }
}
