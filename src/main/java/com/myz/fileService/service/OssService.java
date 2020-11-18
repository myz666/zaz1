package com.myz.fileService.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadAvatar(MultipartFile file);
    void deleteFile(String dirPath,String fileName);
}
