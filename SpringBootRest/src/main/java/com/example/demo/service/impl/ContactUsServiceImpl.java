package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.service.ContactUsService;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public ContactUs saveContact(ContactUs contactUs) {
        contactUs.setCreatedDate(LocalDateTime.now());
        contactUs.setUpdatedDate(LocalDateTime.now());

        ContactUs savedContact = contactUsRepository.save(contactUs);

        // Send confirmation email to the client
        sendConfirmationEmail(savedContact);

        return savedContact;
    }

    @Override
    public List<ContactUs> getAllContacts() {
        return contactUsRepository.findAll();
    }

    @Override
    public ContactUs getContactById(Integer id) {
        return contactUsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + id));
    }

    @Override
    public ContactUs updateContact(Integer id, ContactUs contactUs) {
        ContactUs existing = contactUsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with ID: " + id));

        existing.setName(contactUs.getName());
        existing.setEmail(contactUs.getEmail());
        existing.setNumber(contactUs.getNumber());
        existing.setMessage(contactUs.getMessage());
        existing.setUpdatedDate(LocalDateTime.now());

        return contactUsRepository.save(existing);
    }

    @Override
    public void deleteContact(Integer id) {
        if (!contactUsRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with ID: " + id);
        }
        contactUsRepository.deleteById(id);
    }

    private void sendConfirmationEmail(ContactUs contactUs) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(contactUs.getEmail()); // Send to client's email
            message.setSubject("Thank you for contacting us!");
            message.setText("Hello " + contactUs.getName() + ",\n\n" +
                    "We have received your message:\n\n" +
                    "\"" + contactUs.getMessage() + "\"\n\n" +
                    "Our team will get back to you soon.\n\n" +
                    "Best regards,\n" +
                    "ComputerSeekho Team");

            System.out.println("Attempting to send email to: " + contactUs.getEmail());
            mailSender.send(message);
            System.out.println("Email sent successfully to: " + contactUs.getEmail());
        } catch (Exception e) {
            System.err.println("Error sending email to " + contactUs.getEmail() + ": " + e.getMessage());
            e.printStackTrace();
            // Don't throw the exception to avoid breaking the contact save operation
        }
    }
}
