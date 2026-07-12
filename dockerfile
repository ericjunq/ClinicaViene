# syntax=docker/dockerfile:1

# ── Stage 1: build ──────────────────────────────────────────
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copia apenas arquivos de dependência primeiro (cache de layers)
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copia o código-fonte e compila
COPY src src
RUN ./mvnw package -DskipTests -B

# Extrai camadas do JAR (otimização de cache Docker)
RUN java -Djarmode=layertools -jar target/VieneClinica-*.jar extract --destination extracted

# ── Stage 2: runtime ─────────────────────────────────────────
FROM eclipse-temurin:17-jre-jammy AS runtime

# Usuário não-root (segurança)
RUN groupadd --system app && useradd --system --gid app app
WORKDIR /app

# Copia camadas na ordem correta (menos mudanças = melhor cache)
COPY --from=builder --chown=app:app /app/extracted/dependencies/ ./
COPY --from=builder --chown=app:app /app/extracted/spring-boot-loader/ ./
COPY --from=builder --chown=app:app /app/extracted/snapshot-dependencies/ ./
COPY --from=builder --chown=app:app /app/extracted/application/ ./

USER app

EXPOSE 8080

# JVM otimizada para containers
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:+ExitOnOutOfMemoryError \
               -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]