package com.f1istanbul.service;

import com.f1istanbul.dto.TicketRequest;
import com.f1istanbul.model.Customer;
import com.f1istanbul.model.Ticket;
import com.f1istanbul.repository.CustomerRepository;
import com.f1istanbul.repository.TicketRepository;
import com.f1istanbul.util.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final PdfGenerator pdfGenerator;

    @Transactional
    public byte[] purchaseTicket(TicketRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseGet(() -> {
                    Customer newCustomer = Customer.builder()
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .build();
                    return customerRepository.save(newCustomer);
                });

        String barcode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        Ticket ticket = Ticket.builder()
                .grandstand(request.getGrandstand())
                .seatNumber("A-" + (int)(Math.random() * 100))
                .price(5000.0) // Simule fiyat
                .barcode(barcode)
                .purchaseDate(LocalDateTime.now())
                .customer(customer)
                .build();

        ticketRepository.save(ticket);

        return pdfGenerator.generateTicketPdf(ticket);
    }
}
