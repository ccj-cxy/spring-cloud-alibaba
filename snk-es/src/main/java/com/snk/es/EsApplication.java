package com.snk.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/5/23
 */
@SpringBootApplication
@EnableElasticsearchRepositories
@EnableReactiveElasticsearchRepositories
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class);
    }
}
