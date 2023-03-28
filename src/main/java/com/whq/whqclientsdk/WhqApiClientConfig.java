package com.whq.whqclientsdk;

import com.whq.whqclientsdk.client.WhqApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: whq
 * @description:
 * @time: 2023/3/25 14:04
 */
@Configuration
@ConfigurationProperties("whq-api.client")
@Data
@ComponentScan
public class WhqApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public WhqApiClient whqApiClient() {
        return new WhqApiClient(accessKey, secretKey);
    }

}
