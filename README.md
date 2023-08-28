# Getting Started

### FFMPEG-JServer
I wanted a service to share ffmpeg's audio and video compression features to other projects

### Project Structure:

    . README.md                                             # You are here
    . videos                                                # input and output video folder
    . src               
    ├── main
    |   ├── java\com\dev\cs5609\ffmpegserver\Controllers    # Contains web-related logic
    |   └── java\com\dev\cs5609\ffmpegserver\Service        # Contains files controlling ffmpeg and it's related tools
    └── test\java\com\dev\cs5609\ffmpegserver               # Contains all tests for both code and the program

# Usage

## Compile
- Clone the repository with `git clone https://github.com/CookieStudios5609/ffmpeg-jserver.git`
- `cd` into the new directory
- Compile using `mvn package`
- Run with `java -jar ffmpegserver-0.0.1-SNAPSHOT.jar`

## Or, run in Podman
- Build the image using `podman build -t yourImageName .`
- Run a container using `podman run -d --name yourContainerName -p yourHostPort:8080 -v yourOutputFolder:/videos localhost/yourImageName`

## Compressing a video file

Currently only converts videos to h.265/HEVC or re-encodes h.264/AVC videos into slightly smaller ones

Send a POST request (Powershell example): 

    $wc = New-Object System.Net.WebClient
    $resp = $wc.UploadFile(http://yourHostname:yourHostPort/upload?outputkb=1000&codec=libx265, {file path}.mp4)

The result is exported to /videos in the current folder or container mount.