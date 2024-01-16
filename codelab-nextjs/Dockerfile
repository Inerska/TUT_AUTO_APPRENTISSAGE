FROM node:18-alpine

WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install --no-optional && npm cache clean --force
RUN npm install -g pnpm@latest

COPY app ./app
COPY public ./public
COPY next.config.js .
COPY tsconfig.json .

CMD npm run dev