package com.example.demo.exception;

import org.springframework.core.NestedRuntimeException;

@SuppressWarnings("serial")
public class ParentException extends NestedRuntimeException{
	
	
	public ParentException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ParentException(String msg) {
		super(msg);
	}
	
}
