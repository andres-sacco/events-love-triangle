FROM localstack/localstack:0.14.5

ENV SERVICES=sns,sqs DEBUG=1 DEFAULT_REGION=us-east-1 HOSTNAME_EXTERNAL=localhost DOCKER_HOST=unix:///var/run/docker.sock

VOLUME /docker-entrypoint-initaws.d/
VOLUME /var/run/docker.sock

EXPOSE 4566
