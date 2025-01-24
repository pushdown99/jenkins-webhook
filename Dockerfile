FROM nginx:1.19.5

USER root

RUN apt-get update -y \
    && apt-get install -y  \
       net-tools \
       procps \
       vim

WORKDIR /etc/nginx