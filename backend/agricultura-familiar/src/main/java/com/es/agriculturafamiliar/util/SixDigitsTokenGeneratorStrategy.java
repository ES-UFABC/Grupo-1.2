package com.es.agriculturafamiliar.util;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SixDigitsTokenGeneratorStrategy implements ITokenGeneratorStrategy<String> {	
	private static final int UPPER_BOUND = 999999;
	private final Random random;
	
	public SixDigitsTokenGeneratorStrategy() {
		random = new Random();
	}

	@Override
	public String generateToken() {		
		int generatedToken = random.nextInt(UPPER_BOUND);
		return String.format("%06d", generatedToken);
	}

}
