package org.mohammed.cmsapi.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.config.properties.MinioProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MinioConfig {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient client() {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }


}