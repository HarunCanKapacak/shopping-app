package com.example.shoppingapp.filestrore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("hk.s3")
public class S3ConfigurationProperties {
    private String url;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
