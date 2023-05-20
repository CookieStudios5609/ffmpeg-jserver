# Getting Started

### FFMPEG-JServer
I wanted a service to share ffmpeg's audio and video compression features to other projects, so here goes

### Project Structure:
- 

# Usage
Currently only converts videos to h.265/HEVC or re-encodes h.264/AVC videos into slightly smaller ones
Send a POST request (Powershell example): 
`$wc = New-Object System.Net.WebClient;
$resp = $wc.UploadFile({conatiner-link-here}/upload?outputkb=1000&codec=libx265, {file path}.mp4)`
### Setup

### Compressing a video file