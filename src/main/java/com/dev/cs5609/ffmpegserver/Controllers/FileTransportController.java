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
    // TODO: rewrite and account for filename path traversals
    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile video, RedirectAttributes attributes, @RequestParam(defaultValue = "200000") int outputkb, @RequestParam(defaultValue = "libx264") String codec) {
        System.out.printf("Received file: %s, containing %s bytes of %s\n", video.getOriginalFilename(), video.getSize(), video.getContentType());
        File vidFile = new File("videos/" + video.getOriginalFilename());
        vidFile.getParentFile().mkdirs();
        try {
            vidFile.createNewFile();
            System.out.println("file created");
            //FIXME: Spring writes to user's temp folder on windows
            //use relative paths
            video.transferTo(new File(vidFile.getAbsolutePath())); 
            Compress(vidFile.getAbsolutePath(), outputkb, codec);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // @GetMapping("/")
    // public String HelloWorld() {
    //     return "Hello, World!";
    // }

    @GetMapping("/download")
    
    public void Compress(String name, int size, String codec) {
        try {
            VideoCompressorService s = new VideoCompressorService(name, size, codec);
            s.executeJob();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}