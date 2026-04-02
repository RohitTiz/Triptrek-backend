# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first (for dependency caching)
COPY pom.xml .

# Download all dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application (skip tests as per your pom.xml)
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/backend-1.0.0.jar app.jar

# Install wget for healthcheck (alpine mein nahi hota by default)
RUN apk add --no-cache wget

# Create a non-root user to run the application (security)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Expose the port
EXPOSE 8080

# Health check - Render ke liye important
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the application with proper bind to 0.0.0.0 and PORT environment variable
ENTRYPOINT ["sh", "-c", "java -jar -Dserver.address=0.0.0.0 -Dserver.port=${PORT:-8080} app.jar"]