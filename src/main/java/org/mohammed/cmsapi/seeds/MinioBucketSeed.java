package org.mohammed.cmsapi.seeds;

import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.constants.BodyTypeConstants;
import org.mohammed.cmsapi.constants.MuscleBalanceConstants;
import org.mohammed.cmsapi.constants.TraineeConstants;
import org.mohammed.cmsapi.service.MinioService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MinioBucketSeed {

    private final MinioService service;

    @EventListener(ApplicationReadyEvent.class)
    public void seedBuckets() {
        List<String> buckets = List.of(TraineeConstants.BUCKET_NAME, BodyTypeConstants.BUCKET_NAME, MuscleBalanceConstants.BUCKET_NAME);
        buckets.forEach(bucket -> {
            try {
                service.createBucketIfNotExists(bucket);
            } catch (Exception e) {
                throw new RuntimeException("Error occurred while verifying or creating bucket: " + bucket, e);
            }
        });
    }
}
