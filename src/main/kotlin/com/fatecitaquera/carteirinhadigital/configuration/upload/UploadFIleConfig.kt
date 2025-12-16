package com.fatecitaquera.carteirinhadigital.configuration.upload

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.S3Configuration
import java.net.URI

@Configuration
class UploadFIleConfig(
    @Value("\${supabase.s3.endpoint-s3}") private val endpoint: String,
    @Value("\${supabase.s3.region}") private val region: String,
    @Value("\${supabase.s3.access-key}") private val accessKey: String,
    @Value("\${supabase.s3.secret-key}") private val secretKey: String
) {

    @Bean
    fun s3Client(): S3Client =
        S3Client.builder()
            .endpointOverride(URI.create(endpoint))
            .region(Region.of(region))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(accessKey, secretKey)
                )
            )
            .serviceConfiguration(
                S3Configuration.builder()
                    .pathStyleAccessEnabled(true)
                    .build()
            )
            .build()

}