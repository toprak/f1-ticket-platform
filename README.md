# 🏎️ F1 Istanbul Ticket Platform

A modern, containerized Full-Stack ticket sales platform simulating Formula 1's potential return to Turkey. Built entirely with Docker.

## 🛠️ Tech Stack
* **Frontend:** Angular 17, Tailwind CSS
* **Backend:** Java 17, Spring Boot, Spring Data JPA, OpenPDF (QR Code generation via ZXing)
* **Database:** PostgreSQL
* **Infrastructure:** Docker & Docker Compose

## 🚀 How to Run (Single Command)
To run the project on your local machine, you only need **Docker Desktop** installed. No need to manually install Node.js, Java, or PostgreSQL!

1. Open your terminal and navigate to the project root directory.
2. Run the following command:
```bash
docker compose up --build
```
3. Visit **`http://localhost:4200`** in your browser to experience the platform.

## 🌟 Features
* **Modern UI:** Dynamic and elegant design reflecting the spirit of F1.
* **Easter Eggs:** Hear the roar of a V10 F1 engine when you click the purchase button, followed by a full-screen confetti celebration upon success! 🎉
* **Instant PDF Generation:** Based on the submitted form, a personalized souvenir PDF ticket containing a working **QR Code**, name, date, and grandstand info is generated instantly by the backend.
* **Full Isolation:** Every service (Database, Backend, Frontend) runs in its own isolated container within a dedicated Docker network.

## 📸 Screenshots
*(Add a screenshot of your running project here as `screenshot.png` to showcase your work)*
