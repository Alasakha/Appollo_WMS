package com.mgkj.controller.WMS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class FilePreviewController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/preview/{filename:.+}")
    public ResponseEntity<Resource> previewFile(
            @PathVariable String filename,
            @RequestHeader(value = "User-Agent", required = false) String userAgent) {
        
        try {
            // Windows路径处理
            String winPath = uploadDir.replace("/", "\\");
            Path filePath = Paths.get(winPath).resolve(filename).normalize();
            
            // 安全检查
            if (!filePath.startsWith(Paths.get(winPath).normalize())) {
                return ResponseEntity.badRequest().build();
            }
            
            Resource resource = new UrlResource(filePath.toUri());
            
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // 根据文件类型设置响应头
            String contentType = determineContentType(filename);
            
            // 对于浏览器直接支持的类型（PDF/图片等），使用inline显示
            String contentDisposition = shouldInlinePreview(contentType) ? 
                "inline" : "attachment";
            
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                            contentDisposition + "; filename=\"" + encodeFilename(filename, userAgent) + "\"")
                    .body(resource);
            
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private String determineContentType(String filename) throws IOException {
        Path path = Paths.get(uploadDir).resolve(filename);
        String contentType = Files.probeContentType(path);
        
        // 默认类型处理
        if (contentType == null) {
            if (filename.toLowerCase().endsWith(".pdf")) {
                return "application/pdf";
            } else if (filename.toLowerCase().endsWith(".docx")) {
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            }
            // 其他类型...
            return "application/octet-stream";
        }
        return contentType;
    }

    private boolean shouldInlinePreview(String contentType) {
        // 定义可以直接在浏览器中预览的类型
        return contentType.startsWith("image/") || 
               contentType.equals("application/pdf") ||
               contentType.equals("text/plain") ||
               contentType.startsWith("video/");
    }

    private String encodeFilename(String filename, String userAgent) {
        try {
            if (userAgent != null && userAgent.contains("Trident")) {
                return java.net.URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
            } else if (userAgent != null && userAgent.contains("Edge")) {
                return java.net.URLEncoder.encode(filename, "UTF-8");
            } else {
                return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (Exception e) {
            return filename;
        }
    }
}