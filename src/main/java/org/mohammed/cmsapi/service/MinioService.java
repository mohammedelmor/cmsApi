package org.mohammed.cmsapi.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.*;
import io.minio.errors.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.mohammed.cmsapi.dto.MinioUploadedFileGetDto;
import org.mohammed.cmsapi.exception.BucketAlreadyExistsException;
import org.mohammed.cmsapi.exception.BucketNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void createBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean exists = client.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName).build());
        if (exists) throw new BucketAlreadyExistsException(bucketName + " bucket already exists");
        client.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build());
    }

    public void createBucketIfNotExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean exists = client.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!exists) {
            client.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
            client.setBucketPolicy(SetBucketPolicyArgs.builder()
                            .bucket(bucketName)
                            .config(generateConfigString(bucketName))
                    .build());
        }
    }

    public MinioUploadedFileGetDto uploadFile(MultipartFile file, String bucketName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        checkIfBucketExists(bucketName);
        ObjectWriteResponse response = client.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(UUID.randomUUID().toString())
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());

        return new MinioUploadedFileGetDto(response.object());
    }

    public void checkIfBucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean exists = client.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName).build());
        if (!exists) throw new BucketNotFoundException(bucketName + " bucket does not exist");
    }

    public void deleteFile(String bucket, String object) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        client.removeObject(RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(object)
                .build());
    }

    private String generateConfigString(String bucket) throws JsonProcessingException {
        Principal principal = Principal.builder()
                .aws(List.of("*"))
                .build();
        Statement statement = Statement.builder()
                .action(List.of("s3:GetObject"))
                .effect("Allow")
                .principal(principal)
                .resource(List.of("arn:aws:s3:::" + bucket + "/*"))
                .sId("")
                .build();
        BucketPolicy bucketPolicy = BucketPolicy.builder()
                .version("2012-10-17")
                .statement(List.of(statement))
                .build();
        return objectMapper.writeValueAsString(bucketPolicy);
    }


    @Getter
    @Setter
    @Builder
    static class Principal {
        @JsonProperty("AWS")
        private List<String> aws;
    }

    @Getter
    @Setter
    @Builder
    static class Statement {
        @JsonProperty("Sid")
        private String sId;
        @JsonProperty("Effect")
        private String effect;
        @JsonProperty("Principal")
        private Principal principal;
        @JsonProperty("Action")
        private List<String> action;
        @JsonProperty("Resource")
        private List<String> resource;
    }

    @Getter
    @Setter
    @Builder
    static class BucketPolicy {
        @JsonProperty("Version")
        private String version;
        @JsonProperty("Statement")
        private List<Statement> statement;
    }
}
