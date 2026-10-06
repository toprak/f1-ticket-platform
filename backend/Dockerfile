FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Sadece pom.xml'i kopyalayıp bağımlılıkları indiriyoruz (Docker cache'ini verimli kullanmak için)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Proje kaynak kodlarını kopyalayıp jar oluşturuyoruz
COPY src ./src
RUN mvn package -DskipTests

# Çalışma zamanı için standart JRE imajı (Apple Silicon M serisi uyumlu)
FROM eclipse-temurin:17-jre
WORKDIR /app

# Build aşamasında oluşan jar dosyasını çalışma imajına alıyoruz
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=Europe/Istanbul", "-jar", "app.jar"]
