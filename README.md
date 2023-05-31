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

## Setup
- Clone the repository with `git clone https://github.com/CookieStudios5609/ffmpeg-jserver.git`
- Compile

## Compressing a video file

Currently only converts videos to h.265/HEVC or re-encodes h.264/AVC videos into slightly smaller ones

Send a POST request (Powershell example): 

    $wc = New-Object System.Net.WebClient
    $resp = $wc.UploadFile({loc-here}/upload?outputkb=1000&codec=libx265, {file path}.mp4)