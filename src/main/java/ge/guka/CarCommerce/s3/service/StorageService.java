package ge.guka.CarCommerce.s3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Autowired
    private final S3Client s3Client;

    public String uploadFile(String key, MultipartFile file){
        try {

            byte[] content = file.getBytes();

            String encodedKey = URLEncoder.encode(key, StandardCharsets.UTF_8);

            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            s3Client.putObject(putRequest, RequestBody.fromBytes(content));

            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + encodedKey;


        } catch (IOException ex){
           // System.err.println("File upload error: " + exception.awsErrorDetails().errorMessage());
            throw new RuntimeException("File upload failed: " + ex.getMessage());
        }
    }

    // დროებითი იუერელი

//    public String getFileUrl(String key){
//
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
//                .signatureDuration(Duration.ofMinutes(15))
//                .getObjectRequest(getObjectRequest)
//                .build();
//
//        PresignedGetObjectRequest presigned = s3Presigner.presignGetObject(presignRequest);
//
//        return presigned.url().toString();
//    }
}
