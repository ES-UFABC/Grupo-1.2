FROM node:13.12.0-alpine
WORKDIR /agricultura-familiar
ENV PATH /agricultura-familiar/node_modules/.bin:$PATH
COPY package.json .
RUN npm install
COPY . .
CMD npm run dev
