package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ContactUs;
import com.example.demo.service.ContactUsService;

@RestController
@RequestMapping("/api/contactus")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    // Create (POST)
    @PostMapping("/add")
    public ContactUs addContact(@RequestBody ContactUs contactUs) {
        return contactUsService.saveContact(contactUs);
    }

    // Read all (GET)
    @GetMapping("/all")
    public List<ContactUs> getAllContacts() {
        return contactUsService.getAllContacts();
    }

    // Read by ID (GET)
    @GetMapping("/{id}")
    public ContactUs getContactById(@PathVariable Integer id) {
        return contactUsService.getContactById(id);
    }

    // Update (PUT)
    @PutMapping("/update/{id}")
    public ContactUs updateContact(@PathVariable Integer id, @RequestBody ContactUs contactUs) {
        return contactUsService.updateContact(id, contactUs);
    }

    // Delete (DELETE)
    @DeleteMapping("/delete/{id}")
    public void deleteContact(@PathVariable Integer id) {
        contactUsService.deleteContact(id);
    }
}
