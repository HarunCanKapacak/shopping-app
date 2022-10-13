package com.example.shoppingapp.filestrore.startup;

import com.example.shoppingapp.filestrore.config.S3ConfigurationProperties;
import com.example.shoppingapp.filestrore.service.FileStoreService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStoreStartup {


    private final S3ConfigurationProperties s3ConfigurationProperties;
    private final MinioClient minioClient;

    @EventListener(ApplicationStartedEvent.class)
    public  void init()throws  Exception{
     boolean bucketExist =   minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(s3ConfigurationProperties.getBucket())
                .build());
     if(!bucketExist){
         minioClient.makeBucket(MakeBucketArgs.builder()
                 .bucket(s3ConfigurationProperties.getBucket())
                 .build());
     }
    }
}
