FROM java:8
ADD commutify-webapp.jar app.jar
ADD init.sh init.sh
RUN chmod +x /init.sh
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["/init.sh"]