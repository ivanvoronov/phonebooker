package com.phonebooking.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.phonebooking.exceptions.PhoneNotFoundException;
import com.phonebooking.model.Phone;

@Repository
public class PhoneManager {
    private static volatile PhoneManager instance;
    private List<Phone> phones;

    private PhoneManager() {
        phones = new ArrayList<>();
        phones.add(new Phone("Samsung Galaxy S9"));
        phones.add(new Phone("Samsung Galaxy S8"));
        phones.add(new Phone("Samsung Galaxy S8"));
        phones.add(new Phone("Motorola Nexus 6"));
        phones.add(new Phone("Oneplus 9"));
        phones.add(new Phone("Apple iPhone 13"));
        phones.add(new Phone("Apple iPhone 12"));
        phones.add(new Phone("Apple iPhone 11"));
        phones.add(new Phone("iPhone X"));
        phones.add(new Phone("Nokia 3310"));
    }

    public static PhoneManager getInstance() {
        if (instance == null) {
            synchronized (PhoneManager.class) {
                if (instance == null) {
                    instance = new PhoneManager();
                }
            }
        }
        return instance;
    }
    
    public synchronized Phone getPhone(String model) {
    	for (Phone phone : this.phones) {
    		if (phone.getModel().equalsIgnoreCase(model) ) {
    			return phone;
    		}
    	}
    	throw new PhoneNotFoundException("Phone " + model + " not found");
    }
    
    public synchronized List<Phone> getAllPhones() {
    	return phones;
    }

    public synchronized void bookPhone(String model, String bookedBy) {
        Phone phone = getNextPhone(model, true);
        phone.setAvailable(false);
        phone.setBookedAt(LocalDateTime.now());
        phone.setBookedBy(bookedBy);
    }

    public synchronized void returnPhone(String model) {
        Phone phone = getNextPhone(model, false);
        phone.setAvailable(true);
        phone.setBookedAt(null);
        phone.setBookedBy(null);
    }
    
    private Phone getNextPhone(String model, boolean isAvailable) {
        for (Phone phone : phones) {
            if (phone.getModel().equalsIgnoreCase(model) && phone.isAvailable() == isAvailable) {
                return phone;
            }
        }
        throw new PhoneNotFoundException((isAvailable ? "Available" : "Booked") + " phone not found: " + model);
    }
}
