package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Foo;
import com.example.demo.domain.FooRepository;

@Service
public class FooService {

	@Autowired
	private FooRepository repository;

	@Transactional
	@Retryable(stateful = true, maxAttempts = 3)
	public Foo save(Foo foo) throws Exception {
		System.out.println("try!");
		throw new Exception("出异常了");
//		return repository.save(foo);
	}

	@Recover
	public Foo saveRecover(Exception e, Foo foo) {
		System.out.println("saveRecover!!!!!!!!!!!!!!!!!!");
		return null;
	}
}
