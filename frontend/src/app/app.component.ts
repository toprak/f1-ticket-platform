import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TicketService } from './services/ticket.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './app.component.html'
})
export class AppComponent {
  ticketForm: FormGroup;
  pdfUrl: SafeResourceUrl | null = null;
  rawBlob: Blob | null = null;
  isLoading = false;

  grandstands = [
    'Ana Tribün (Main Grandstand)',
    'Viraj 8 (Turn 8)',
    'Açık Alan (General Admission)'
  ];

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketService,
    private sanitizer: DomSanitizer
  ) {
    this.ticketForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      grandstand: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.ticketForm.valid) {
      this.isLoading = true;
      
      // Easter Egg 1: Motor Sesi! (Safari'de çalışması için HTML elementinden tetikliyoruz)
      const audio = document.getElementById('engineSound') as HTMLAudioElement;
      if (audio) {
        audio.volume = 0.5;
        audio.play().catch(e => console.log('Ses çalınamadı:', e));
      }

      this.ticketService.purchaseTicket(this.ticketForm.value).subscribe({
        next: (blob) => {
          this.rawBlob = blob;
          const objectUrl = URL.createObjectURL(blob);
          this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(objectUrl);
          this.isLoading = false;
          
          // Easter Egg 2: Konfeti! (Bilet başarıyla geldiğinde)
          this.fireConfetti();
        },
        error: (err) => {
          console.error('Error purchasing ticket', err);
          alert('Bilet alınırken bir hata oluştu! Backendin ayakta olduğuna emin olun.');
          this.isLoading = false;
        }
      });
    } else {
      this.ticketForm.markAllAsTouched();
      alert('Lütfen tüm bilgileri (Ad, Soyad, Tribün) eksiksiz doldurduğunuzdan ve E-Posta adresinizi doğru formatta (örnek@gmail.com) girdiğinizden emin olun!');
    }
  }

  // Konfeti patlatma fonksiyonu (Dışarıdan kütüphane yükleyerek)
  fireConfetti() {
    const script = document.createElement('script');
    script.src = 'https://cdn.jsdelivr.net/npm/canvas-confetti@1.6.0/dist/confetti.browser.min.js';
    script.onload = () => {
      const myConfetti = (window as any).confetti;
      myConfetti({
        particleCount: 200,
        spread: 120,
        origin: { y: 0.6 },
        colors: ['#E10600', '#ffffff', '#15151E'] // F1 Kırmızısı, Beyaz ve Siyah
      });
    };
    document.body.appendChild(script);
  }

  downloadPdf() {
    if (this.rawBlob) {
      const url = window.URL.createObjectURL(this.rawBlob);
      const link = document.createElement('a');
      link.href = url;
      link.download = `f1_bilet_${this.ticketForm.value.firstName}.pdf`;
      link.click();
      window.URL.revokeObjectURL(url);
    }
  }
}
