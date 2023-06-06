# Kotlin Springboot Rest API Server frontend App

### In the project directory, you can dockerize app with the following commands

Install docker or podman if you don't have it already.

Run commands similar to the below commands to build and run image

### `podman build -f Dockerfile-dev -t springboot-kotlin-app:1.0-dev .`
Builds a dev image 

### `podman build -t springboot-kotlin-app:1.0-prod .`
Builds a production ready image

<br> Run `podman images` to confirm.

Run image for development

### `podman run -it --name=springboot-kotlin-app -p 3000:3000 springboot-kotlin-app:1.0-dev`

<br>For real time changes to sync with the running container during development, run this command instead
### `podman run -it --name=springboot-kotlin-app -p 3000:3000 -v $(pwd):/frontend springboot-kotlin-app:1.0-dev`

<br>To remove container during development as soon as container exits, you can add '--rm' flag as shown:
### `podman run --rm -it --name=springboot-kotlin-app -p 3000:3000 springboot-kotlin-app:1.0-dev`

<br> Run image for production

### `podman run --name=springboot-kotlin-app-prod -p 3000:80 springboot-kotlin-app:1.0-prod`
or
### `podman run -it --name=springboot-kotlin-app-prod -p 3000:80 springboot-kotlin-app:1.0-prod` for interactive terminal

<br> Visit [http://localhost:3000](http://localhost:3000) to view it in your browser.

## Useful Scripts To Run App without docker

Within the project directory, you can run:

### `npm start`
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.
                                  
### `npm test`
Launches the test runner in the interactive watch mode.\

### `npm run build`
Builds the app for production to the `build` folder.
