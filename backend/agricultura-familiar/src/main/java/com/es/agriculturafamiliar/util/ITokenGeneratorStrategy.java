package com.es.agriculturafamiliar.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface ITokenGeneratorStrategy<T> {
	T generateToken();

}
