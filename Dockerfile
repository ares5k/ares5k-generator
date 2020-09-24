FROM openjdk:8
MAINTAINER ares5k <16891544@qq.com>
LABEL description="ares5k-generator docker file"
EXPOSE 1994
VOLUME /var/ares5k-generator
ADD ares5k-generator-1.0-SNAPSHOT.jar /app.jar
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JVM_OPTS="-Xint"
CMD ["/bin/bash", "-c","java ${JVM_OPTS} -jar /app.jar"]