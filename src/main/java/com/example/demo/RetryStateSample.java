package com.example.demo;

import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.RetryState;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import com.example.demo.domain.Foo;
import com.example.demo.exception.ParentException;

public class RetryStateSample {

	public static Foo doBusinessService() {
		return new Foo("doBusinessService Foo");
	}

	public static Foo doBusinessService2() {
		throw new ParentException("haha");
	}

	public static void main(String[] args) throws Throwable {
		RetryTemplate template = new RetryTemplate();
		RetryPolicy policy = new SimpleRetryPolicy(10);
		template.setRetryPolicy(policy);
		RetryState retryState = new DefaultRetryState("userID=1");

		Foo result = template.execute(context -> {
			System.out.println(String.format("RetryContext: %s", context));
			String state = (String) context.getAttribute(RetryContext.STATE_KEY);
			System.out.println(String.format("RetryState In First Call: %s", state));
			return doBusinessService2();
		}, context -> {
			return new Foo("Recovery Foo");
		}, retryState);

		System.out.println(String.format("Result1: %s", result));

	}

}
