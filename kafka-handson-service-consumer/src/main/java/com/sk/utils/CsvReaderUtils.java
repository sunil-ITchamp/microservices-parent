package com.sk.utils;
import com.sk.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvReaderUtils {

    public static List<User> getUsersFromCsv() throws IOException{
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ClassPathResource("users.csv").getInputStream()));
    CsvToBean<User> userCsvToBean = new CsvToBeanBuilder<User>(bufferedReader)
            .withType(User.class)
            .build();
    return userCsvToBean.parse();
    }
}
