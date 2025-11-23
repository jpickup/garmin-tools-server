# pre-fetch dependencies
FROM --platform=linux/amd64 maven:3.9.9-eclipse-temurin-23 AS dependencies

WORKDIR /opt/app
COPY common/pom.xml common/pom.xml
COPY parser/pom.xml parser/pom.xml
COPY gpx/pom.xml gpx/pom.xml
COPY workout/pom.xml workout/pom.xml
COPY garmin/pom.xml garmin/pom.xml
COPY calendar/pom.xml calendar/pom.xml
COPY server/pom.xml server/pom.xml
COPY pom.xml .

RUN mvn -B -e org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=common,parser,gpx,workout,garmin,calendar

# build the jar
FROM --platform=linux/amd64 maven:3.9.9-eclipse-temurin-23 AS builder

WORKDIR /opt/app
COPY --from=dependencies /root/.m2 /root/.m2
COPY --from=dependencies /opt/app/ /opt/app
COPY common/src /opt/app/common/src
COPY parser/src /opt/app/parser/src
COPY gpx/src /opt/app/gpx/src
COPY workout/src /opt/app/workout/src
COPY garmin/src /opt/app/garmin/src
COPY calendar/src /opt/app/calendar/src
COPY server/src /opt/app/server/src

RUN mvn -B -e clean install -DskipTests

# prepeare runtime env
FROM --platform=linux/amd64 eclipse-temurin:23

WORKDIR /opt/app
COPY --from=builder /opt/app/server/target/*.jar /server.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/server.jar"]
