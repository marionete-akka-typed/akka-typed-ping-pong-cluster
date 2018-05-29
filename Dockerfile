FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY ./src/scripts/run_jar.sh ./target/scala-2.12/akka-typed-ping-pong-cluster.jar ./
RUN ["chmod", "+x", "./run_jar.sh"]
ENTRYPOINT ["./run_jar.sh"]
