FROM node:21
RUN npm install -g serve
RUN mkdir ./build
ADD ./dist ./build
EXPOSE 5173

CMD ["serve", "-s", "build", "-l", "5173"]