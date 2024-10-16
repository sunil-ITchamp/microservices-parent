package com.sk.util;

import com.sk.order.OrderLineItem;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
//@AutoConfiguration
@ConditionalOnClass({DataSource.class})
public class MyAutoConfig {

    public MyAutoConfig(){
        System.out.println("My Auto Config constructorrrrrrrrrrrrrrrr .............");
    }
}
