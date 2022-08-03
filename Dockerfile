# depends alpine java in docker, # execute jar file in docker, docker build -t <account/repo> .
FROM openjdk:18-alpine
WORKDIR /usr/src/HelloWorld
COPY /lib /usr/src/HelloWorld/lib
COPY /src /usr/src/HelloWorld/src
CMD java -cp ".:./lib/*" ./src/HelloWorld.java