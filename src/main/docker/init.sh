#!/bin/bash

while ! exec 6<>/dev/tcp/${DB_PORT_3306_TCP_ADDR}/${DB_PORT_3306_TCP_PORT}; do
    echo "Trying to connect to MySQL at ${DB_PORT}..."
    sleep 10
done

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -Xms256m -Xmx512m -jar /app.jar