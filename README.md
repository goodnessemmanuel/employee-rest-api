# rest-api

Project for REST-API for skills-directory

## Getting started

Build app image and run as container

Using Podman
<br>Note: You can build and run app directly with the dockerfile in project directory
<br>```podman build . -t springboot-kotlin-rest-api```
<br>```docker build . -t springboot-kotlin-rest-api```
<br>```podman run --name <any-name> -p 8080:8080 -d springboot-kotlin-rest-api```
<br> or use the compose with podman or docker as describe below

install podman if you don't have it already
Install podman compose [here] if you don't have it already
cd to this project directory and
run: podman-compose -f docker-compose.yml up -d ::starts app

podman ps ::to confirm container is running
Goto http://localhost:8080/ to check app

podman-compose down ::removes and destroy container



Using docker

install docker desktop if you don't have it already
cd to this project directory and run: docker-compose up -d ::starts app with all dependencies

docker ps ::to confirm container is running
Goto http://localhost:8080/ to check app
docker-compose down