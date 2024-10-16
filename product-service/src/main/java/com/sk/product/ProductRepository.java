package com.sk.product;


import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;
import static org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE;
import static org.hibernate.jpa.QueryHints.HINT_READONLY;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("Select p from Product p where name=:pname and description= :pdescription")
    @QueryHints(value = {
            //@QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = HINT_READONLY, value = "true"),
            //@QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false")
    })
    public Stream<Product> findByNameAndDescription(String pname, String pdescription);
}
