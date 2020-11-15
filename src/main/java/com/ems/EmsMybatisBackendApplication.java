package com.ems;

import com.ems.model.Employee;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(Employee.class)
@MapperScan(value = "com.ems.mapper")
@SpringBootApplication
public class EmsMybatisBackendApplication implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(EmsMybatisBackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmsMybatisBackendApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.debug("Debugging log");
        logger.info("Info log");
        logger.warn("Hey, This is a warning!");
        logger.error("Oops! We have an Error. OK");
    }
}
