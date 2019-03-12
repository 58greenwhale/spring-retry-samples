package com.example.demo.exception;

import org.springframework.core.NestedRuntimeException;

@SuppressWarnings("serial")
public class AnotherParentException extends NestedRuntimeException{
	
	
	public AnotherParentException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public AnotherParentException(String msg) {
		super(msg);
	}
	
}
