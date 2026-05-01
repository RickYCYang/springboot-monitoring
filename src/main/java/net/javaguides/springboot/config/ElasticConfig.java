package net.javaguides.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ElasticConfig {

    @Value("${elasticsearch.url}")
    private String elasticUrl;

    @Bean
    public RestClient elasticRestClient() {
        return RestClient.builder().baseUrl(elasticUrl).build();
    }
}
