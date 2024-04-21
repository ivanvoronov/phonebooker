package com.phonebooking.service;

import com.phonebooking.model.Phone;

public interface PhoneService {

	Phone getPhone(String model);

	void bookPhone(String model, String bookedBy);

	void returnPhone(String model);
}
