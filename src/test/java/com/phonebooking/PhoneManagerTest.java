package com.phonebooking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.phonebooking.exceptions.PhoneNotFoundException;
import com.phonebooking.model.Phone;
import com.phonebooking.repository.PhoneManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PhoneManagerTest {
    private PhoneManager phoneManager;

    @BeforeEach
    public void setUp() {
        phoneManager = PhoneManager.getInstance();
    }

    @Test
    public void testGetAllPhones() {
        List<Phone> phones = phoneManager.getAllPhones();
        assertNotNull(phones);
        assertEquals(10, phones.size());
    }

    @Test
    public void testGetPhone() {
        Phone phone = phoneManager.getPhone("Samsung Galaxy S9");
        assertNotNull(phone);
        assertEquals("Samsung Galaxy S9", phone.getModel());
    }

    @Test
    public void testBookPhone() {
        phoneManager.bookPhone("Samsung Galaxy S9", "Ivan Voronov");
        assertFalse(phoneManager.getPhone("Samsung Galaxy S9").isAvailable());
    }

    @Test
    public void testBookAndReturnPhone() {
        phoneManager.bookPhone("Apple iPhone 13", "Ivan Voronov");
    	phoneManager.returnPhone("Apple iPhone 13");
        assertTrue(phoneManager.getPhone("Apple iPhone 13").isAvailable());
    }
    
    @Test
    public void testBookAndReturnSamsungGalaxyS8Twice() {        
        // Book Samsung Galaxy S8 twice (there are 2 such phones)
        phoneManager.bookPhone("Samsung Galaxy S8", "User1");
        phoneManager.bookPhone("Samsung Galaxy S8", "User2");
        
        // Ensure both phones are successfully booked and unavailable
        // (there is no possibility to book this phone again)
        assertThrows(PhoneNotFoundException.class, () -> phoneManager.bookPhone("Samsung Galaxy S8", "User1"));
        
        // Return Samsung Galaxy S8 twice
        phoneManager.returnPhone("Samsung Galaxy S8");
        phoneManager.returnPhone("Samsung Galaxy S8");

        // Ensure both phones are successfully returned and available
        // (there is no possibility to return this phone again)
        assertThrows(PhoneNotFoundException.class, () -> phoneManager.returnPhone("Samsung Galaxy S8"));
    }
}
