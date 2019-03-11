package com.example.demo;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class Sample {

	public static class Foo {

		public String foo;

		public Foo(String foo) {
			this.foo = foo;
		}
	}

	public static Foo doBusinessService() {
		return new Foo("doBusinessService");
	}

	public static void main(String[] args) throws Throwable {
		RetryTemplate template = new RetryTemplate();

//		TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
//		policy.setTimeout(30000L);

		RetryPolicy policy = new ExceptionClassifierRetryPolicy();
		
		template.setRetryPolicy(policy);

		Foo result = template.execute(new RetryCallback<Foo, Throwable>() {

			public Foo doWithRetry(RetryContext context) {
				// Do stuff that might fail, e.g. webservice operation

				return doBusinessService();
			}

		});

		System.out.println(result);
	}
}
