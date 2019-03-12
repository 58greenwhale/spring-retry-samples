package com.example.demo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Foo;
import com.example.demo.service.FooService;

@RestController
public class FooEndpoint {

	@Autowired
	public FooService fooService;
	
	@RequestMapping("foo")
	public Foo save() throws Exception {
		Foo foo = new Foo("foo");
		return fooService.save(foo);
	}
	
}
