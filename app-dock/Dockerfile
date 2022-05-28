FROM gradle:jdk16 as build
COPY /app /app
WORKDIR /app
RUN gradle clean build

FROM adoptopenjdk/openjdk16:jre
ARG SERVICE
COPY --from=build /app/${SERVICE}/build/libs/${SERVICE}-*.jar /app/app.jar
CMD [ "java", "-jar", "/app/app.jar" ]
