package com.phonebooking.service;

import org.springframework.stereotype.Service;

import com.phonebooking.model.Phone;
import com.phonebooking.repository.PhoneManager;

@Service
public class PhoneServiceImpl implements PhoneService {

	private PhoneManager phoneManager;
	
	public PhoneServiceImpl(PhoneManager phoneManager) {
		this.phoneManager = phoneManager;
	}

	@Override
	public Phone getPhone(String model) {
		return phoneManager.getPhone(model);
	}

	@Override
	public void bookPhone(String model, String bookedBy) {
		phoneManager.bookPhone(model, bookedBy);
	}

	@Override
	public void returnPhone(String model) {
		phoneManager.returnPhone(model);
	}

}
