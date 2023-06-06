# build app for production
FROM node:14.17.3-buster-slim as build

WORKDIR /frontend

COPY package.json package.json

COPY package-lock.json package-lock.json

RUN npm i --omit=dev

COPY  . .

RUN npm run build

# install and run application in NGINX Web Server
FROM nginx:1.23-alpine-slim

COPY --from=build /frontend/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]