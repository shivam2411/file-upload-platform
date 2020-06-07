package com.upload.fileuploadplatform.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsClient {
	@Bean
    public AmazonS3 generateS3Client() {
		// TODO Read AWS credentials from config file and store credentials in encrypted form
        AWSCredentials credentials = new BasicAWSCredentials("AKIAQBLMJHTQPFFA4KAU","/fX2TqF/FkWl34DQ2nF6eivWYcJmXXU4uHXsDH5n");
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
