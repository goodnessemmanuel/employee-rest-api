# rest-api

Project for REST-API for skills-directory

## Getting started

Build app image and run as container

Using Podman
- install podman if you don't have it already
<br>Note: You can build and run app directly with the dockerfile in project directory
- ```podman build . -t springboot-kotlin-rest-api```
- ```docker build . -t springboot-kotlin-rest-api```
- ```podman run --name <any-name> -p 8080:8080 -d springboot-kotlin-rest-api```

<br>or use the compose with podman or docker as describe below
- Install podman compose [[here](https://phoenixnap.com/kb/podman-compose#ftoc-heading-2)] if you don't have it already
<br>cd to this project directory and run: 
- ```podman-compose -f docker-compose.yml up -d``` ::starts app
- ```podman ps``` ::to confirm container is running
- Goto http://localhost:8080/api/server/v1/health to check app
- podman-compose down ::removes and destroy container

Using docker
- install docker desktop if you don't have it already
<br>cd to this project directory and run: 
- ```docker-compose up -d ::starts app with all dependencies```
- ```docker ps``` ::to confirm container is running
- Goto http://localhost:8080/api/server/v1/health to check app
- docker-compose down ::removes and destroy container