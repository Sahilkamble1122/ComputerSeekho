package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {
}
