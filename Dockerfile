# Build stage
FROM gradle:7.6.1-jdk17-alpine as builder
COPY --chown=gradle:gradle . /build-dir
WORKDIR /build-dir
RUN gradle --no-daemon build -x test

# Set up stage
FROM bitnami/wildfly:29.0.1
COPY --from=builder /build-dir/build/libs/lab3-web.war /opt/bitnami/wildfly/standalone/deployments/