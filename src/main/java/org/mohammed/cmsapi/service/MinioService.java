package org.mohammed.cmsapi.service;

import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.mohammed.cmsapi.dto.MinioUploadedFileGetDto;
import org.mohammed.cmsapi.exception.BucketAlreadyExistsException;
import org.mohammed.cmsapi.exception.BucketNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient client;

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
                .bucket(bucketName).build());
        if (!exists)
            client.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
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

}
