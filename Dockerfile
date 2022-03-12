FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/ms-account-*-SNAPSHOT.jar
COPY ${JAR_FILE} ms-account.jar
RUN addgroup -S bootcampgroup && adduser -S bootcampuser -G bootcampgroup
ARG LOG=/opt/logs/ms-account
RUN mkdir -p ${LOG}
RUN chown -R bootcampuser:bootcampgroup ${LOG}
USER bootcampuser:bootcampgroup
ENTRYPOINT ["java","-jar","/ms-account.jar"]
