FROM node:20-alpine

WORKDIR /app

# Sadece package.json'u kopyalayıp paketleri kuruyoruz (Docker cache)
COPY package*.json ./
RUN npm install

# Geri kalan tüm proje dosyalarını kopyalıyoruz
COPY . .

# Angular'ın varsayılan portu
EXPOSE 4200

# Docker dışından erişilebilmesi için host 0.0.0.0 olarak ayarlandı
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0", "--disable-host-check"]
