package com.ssafy.banchic.service;

import com.amazonaws.SdkBaseException;
import com.ssafy.banchic.exception.CustomException;
import com.ssafy.banchic.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadService {

    private final AmazonS3ResourceStorage amazonS3ResourceStorage;

    public String save(String path, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_FILE);
        }
        verfyExenstion(multipartFile);
        return amazonS3ResourceStorage.store(path, multipartFile);
    }

    public void delete(String fileUrl) throws SdkBaseException {
        amazonS3ResourceStorage.delete(fileUrl);
    }

    public void verfyExenstion(MultipartFile multipartFile)  {
        String contentType = multipartFile.getContentType();

        if (ObjectUtils.isEmpty(contentType) | (!contentType.contains("image/jpeg") & !contentType.contains("image/png")))
            throw new CustomException(ErrorCode.NOT_VALID_EXTENSION);
    }

}