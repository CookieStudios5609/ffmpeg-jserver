FROM eclipse-temurin:17
COPY target/*.jar .
RUN apt -y update && apt -y install ffmpeg
CMD ["java", "-jar", "ffmpegserver-0.0.1-SNAPSHOT.jar"]
