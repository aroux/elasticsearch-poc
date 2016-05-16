package org.github.aroux.elasticsearchpoc;

import org.github.aroux.elasticsearchpoc.engine.MyEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration(exclude = ElasticsearchAutoConfiguration.class)
@ComponentScan(basePackages = "org.github.aroux.elasticsearchpoc")
public class Application {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        MyEngine myEngine = ctx.getBean(MyEngine.class);
        myEngine.goDomain();
    }

}