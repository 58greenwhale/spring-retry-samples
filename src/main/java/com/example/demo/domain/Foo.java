package com.example.demo.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public  class Foo {

	@Id
	public String id;
	
	public String foo;
	
	public Foo() {
		this.id = UUID.randomUUID().toString();
	}

	public Foo(String foo) {
		this.id = UUID.randomUUID().toString();
		this.foo = foo;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	@Override
	public String toString() {
		return foo;
	}
}