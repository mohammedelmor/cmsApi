package org.mohammed.cmsapi.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.config.properties.MinioProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @EventListener(ApplicationReadyEvent.class)
    public void createBucketIfNotExists() {
        MinioClient client = client();
        List<String> buckets = List.of("trainees", "bodytypes", "musclebalances");
        buckets.forEach(bucket -> {
            try {
                boolean bucketExists = client.bucketExists(BucketExistsArgs.builder()
                        .bucket(bucket).build());
                if (!bucketExists) {
                    client.makeBucket(MakeBucketArgs.builder()
                            .bucket(bucket)
                            .build());
                }
            } catch (Exception e) {
                throw new RuntimeException("Error occurred while verifying or creating bucket: " + bucket, e);
            }
        });
    }
}