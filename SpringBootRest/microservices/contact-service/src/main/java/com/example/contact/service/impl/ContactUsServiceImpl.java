package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ContactUs;
import com.example.demo.repository.ContactUsRepository;
import com.example.demo.service.ContactUsService;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Override
    public ContactUs saveContact(ContactUs contactUs) {
        contactUs.setCreatedDate(LocalDateTime.now());
        contactUs.setUpdatedDate(LocalDateTime.now());
        return contactUsRepository.save(contactUs);
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
}
