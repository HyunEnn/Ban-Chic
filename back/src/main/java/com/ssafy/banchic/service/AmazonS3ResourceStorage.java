package com.ssafy.banchic.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.banchic.exception.CustomException;
import com.ssafy.banchic.exception.ErrorCode;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3ResourceStorage {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String store(String path, MultipartFile multipartFile) {
        String uuidFilename = path + UUID.randomUUID();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        try {
            amazonS3.putObject(bucket, uuidFilename, multipartFile.getInputStream(), metadata);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.FILE_UPLOAD_FAIL);
        }

        return amazonS3.getUrl(bucket, uuidFilename).toString();
    }

    public void delete(String imageURL) {
        try {
            URL url = new URL(imageURL);
            String key = url.getPath().substring(1);

            amazonS3.deleteObject(bucket, key);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FILE_DELETE_FAIL);
        }
    }

}