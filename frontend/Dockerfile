FROM node:latest
WORKDIR /home/ubuntu/frontend 
COPY package.json .

COPY . .
RUN npm install
RUN npm i axios
RUN npm run build
EXPOSE 3000

CMD ["npm", "run", "serve"]