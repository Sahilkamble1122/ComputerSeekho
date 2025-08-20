package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ContactUs;

public interface ContactUsService {
    
    ContactUs saveContact(ContactUs contactUs);
    
    List<ContactUs> getAllContacts();
    
    ContactUs getContactById(Integer id);
    
    ContactUs updateContact(Integer id, ContactUs contactUs);
    
    void deleteContact(Integer id);
}
