FROM openjdk:11
WORKDIR /usr/chia
EXPOSE 8086
COPY Chia_Server-1.0.0.war .
ENV IN=${IN}
ENV IP=${IP}
ENV IE=${IE}
CMD java -jar Chia_Server-1.0.0.war --spring.config.location=/usr/chia/config/application.yml --in=${IN} --ip=${IP} --ie=${IE}
