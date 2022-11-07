package com.order.services;

import java.util.Random;

public class RandomString {
	
	public static String getRandomString() {
		
		Random random = new Random();
		int number = random.nextInt();
		return String.format("%06d", number);
	}
}
