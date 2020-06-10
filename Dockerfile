FROM openjdk:11

WORKDIR /usr/app/

RUN apt-get update && \
  apt-get upgrade -y && \
  apt-get install -y build-essential

ENV DB_USERNAME=sample
ENV DB_PASSWORD=sample
ENV DB_URL=jdbc:postgresql://db/browser_calls

COPY . .

EXPOSE 8080

CMD ["sh","-c","./gradlew flywayMigrate && make serve"]
