package com.abarcan.restclientdemo;

import com.abarcan.restclientdemo.client.JsonPlaceHolderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    JsonPlaceHolderService jsonPlaceHolderService() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return factory.createClient(JsonPlaceHolderService.class);
    }
}
