package com.example.demo.security;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.StaffMaster;
import com.example.demo.model.StudentMaster;
import com.example.demo.repository.StaffMasterRepository;
import com.example.demo.repository.StudentMasterRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentMasterRepository studentMasterRepository;

    @Autowired
    private StaffMasterRepository staffMasterRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First try to find as student
        Optional<StudentMaster> student = studentMasterRepository.findByStudentUsername(username);
        if (student.isPresent()) {
            return new User(student.get().getStudentUsername(), 
                          student.get().getStudentPassword(), 
                          Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT")));
        }

        // Then try to find as staff
        Optional<StaffMaster> staff = staffMasterRepository.findByStaffUsername(username);
        if (staff.isPresent()) {
            return new User(staff.get().getStaffUsername(), 
                          staff.get().getStaffPassword(), 
                          Collections.singletonList(new SimpleGrantedAuthority("ROLE_STAFF")));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
} 