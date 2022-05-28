FROM adoptopenjdk/openjdk16:x86_64-alpine-jdk16u-2021-09-16-01-18-beta-nightly-slim
WORKDIR /app
ARG SERVICE
RUN #addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY ../app/${SERVICE}/build/libs/${SERVICE}-*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]