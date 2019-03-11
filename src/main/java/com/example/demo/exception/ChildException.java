package com.example.demo.exception;

@SuppressWarnings("serial")
public class ChildException extends ParentException {
	public ChildException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ChildException(String msg) {
		super(msg);
	}
}
