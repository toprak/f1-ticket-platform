# 🏎️ F1 Istanbul Bilet Satış Platformu

Formula 1'in Türkiye'ye olası dönüşünü kutlamak amacıyla tasarlanmış, **Docker** üzerinde çalışan, modern ve sembolik bir Full-Stack bilet satış platformudur. 

## 🛠️ Kullanılan Teknolojiler
* **Frontend:** Angular 17, Tailwind CSS
* **Backend:** Java 17, Spring Boot, Spring Data JPA, OpenPDF (ZXing ile QR Kod üretimi)
* **Veritabanı:** PostgreSQL
* **Altyapı:** Docker & Docker Compose

## 🚀 Projeyi Çalıştırma (Tek Komut)
Projeyi bilgisayarınızda ayağa kaldırmak için sisteminizde **Docker Desktop** kurulu olması yeterlidir. Bilgisayarınıza Node.js veya Java kurmanıza bile gerek yoktur!

1. Terminali açın ve projenin ana dizinine gidin.
2. Aşağıdaki komutu çalıştırın:
```bash
docker compose up --build
```
3. Tarayıcınızdan **`http://localhost:4200`** adresine giderek bilet platformunu deneyimleyebilirsiniz.

## 🌟 Özellikler
* **Modern Arayüz:** F1'in ruhunu yansıtan dinamik ve şık tasarım.
* **Easter Egg'ler:** Bilet alma butonuna basıldığında V10 F1 motor sesi ve işlem başarılı olduğunda konfeti kutlaması! 🎉
* **Anlık PDF Üretimi:** Backend'e giden bilgiler doğrultusunda saniyeler içinde size özel, isim, tarih ve çalışan bir **QR Kod** içeren PDF hatıra bileti oluşturulur.
* **Tam İzolasyon:** Her servis (DB, Backend, Frontend) Docker ağında kendi izole konteynerinde çalışır.

## 📸 Ekran Görüntüleri
*(Proje çalışırken aldığınız bir ekran görüntüsünü projenize `screenshot.png` adıyla ekleyip burada sergileyebilirsiniz)*
