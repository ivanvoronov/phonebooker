package com.phonebooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.phonebooking.exceptions.PhoneNotFoundException;
import com.phonebooking.model.Phone;
import com.phonebooking.service.PhoneService;

@RestController
@RequestMapping("/phones")
public class PhoneController {
    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/{model}")
    public ResponseEntity<Phone> getPhone(@PathVariable String model) {
        try {
            Phone phone = phoneService.getPhone(model);
            return ResponseEntity.ok(phone);
        } catch (PhoneNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{model}/book")
    public ResponseEntity<String> bookPhone(@PathVariable String model, @RequestBody String bookedBy) {
        try {
            phoneService.bookPhone(model, bookedBy);
            return ResponseEntity.ok("Phone " + model + " successfully booked by " + bookedBy);
        } catch (PhoneNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
    }

    @PostMapping("/{model}/return")
    public ResponseEntity<String> returnPhone(@PathVariable String model) {
        try {
            phoneService.returnPhone(model);
            return ResponseEntity.ok("Phone " + model + " successfully returned");
        } catch (PhoneNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
