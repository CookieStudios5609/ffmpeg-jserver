package com.dev.cs5609.ffmpegserver.Controllers;

import java.io.File;
import java.io.IOException;

import com.dev.cs5609.ffmpegserver.Service.VideoCompressorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles upload and download for video files
 */
@RestController
public class FileTransportController {

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile video, RedirectAttributes attributes) {
        System.out.printf("Received file: %s, containing %s bytes of %s\n", video.getOriginalFilename(), video.getSize(), video.getContentType());
        File vidFile = new File("videos/" + video.getOriginalFilename());
        vidFile.getParentFile().mkdirs();
        try {
            vidFile.createNewFile();
            System.out.println("file created");
            //TODO: Figure out why Spring attempts to write to a Tomcat folder in the user's temp folder, and fix to use relative paths
            video.transferTo(new File(vidFile.getAbsolutePath())); 
            Compress(vidFile.getAbsolutePath());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String HelloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/download")
    
    public void Compress(String name) {
        try {
            VideoCompressorService s = new VideoCompressorService(name);
            s.executeJob();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}