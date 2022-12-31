package com.dev.cs5609.ffmpegserver.Controllers;

import java.io.File;
import java.io.IOException;

import com.dev.cs5609.ffmpegserver.Service.DiscordPreset;

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
        System.out.printf("Received file: %s, containing %s bytes\n", video.getOriginalFilename(), video.getSize(), video.getContentType());
        File vidFile = new File("videos/" + video.getOriginalFilename());
        vidFile.getParentFile().mkdirs();
        try {
            vidFile.createNewFile();
            System.out.println("file created");
            //TODO: Figure out why paths were freaking out before
            video.transferTo(new File(vidFile.getAbsolutePath()));
            doThing(vidFile.getAbsolutePath());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally {System.out.println("sample Spring logging text");}
    }

    @GetMapping("/")
    public String HelloWorld() {
        return "Hello, World!";
    }
    
    public void doThing(String name) {
        try {
            DiscordPreset dp = new DiscordPreset(name);
            dp.executeJob();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}