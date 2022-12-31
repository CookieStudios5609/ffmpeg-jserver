package com.dev.cs5609.ffmpegserver.Service;

import java.io.IOException;
import org.springframework.stereotype.Component;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

/** 
 * The video compressor, but bad, and a test run
*/
@Component
public class DiscordPreset {
    private final FFmpeg ffm;
    private FFmpegBuilder ffb;
    public DiscordPreset() throws IOException {
        this.ffm = new FFmpeg("ffmpeg");
    }
    /** 
    * A constructor that handles passing the correct flags and parameters to ffmpeg
    @param filename The absolute path to the file, including the file and its extension
    */
    public DiscordPreset(String filename) throws IOException {
        this.ffm = new FFmpeg("ffmpeg");
        this.ffb = new FFmpegBuilder()
        .setInput(filename)
        .addOutput(parseFilePath(filename) + "_final.mp4")
        .setAudioSampleRate(44100)
        .done();
    }

    
    /**
     * Executes the built ffmpeg command
     * @throws IOException because FFmpegExecutor demands it
     */
    public void executeJob() throws IOException {
        FFmpegExecutor ffe = new FFmpegExecutor(this.ffm);
        ffe.createJob(ffb).run();
    }
    public String parseFilePath(String filename) {
        return filename.substring(0, filename.length()-4);
    }
    
}
