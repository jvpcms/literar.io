# already sets the OS to a OS based on Debian Linux
FROM openjdk:22-jdk-slim 

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew

CMD ["./gradlew", "clean", "build", "bootRun"]