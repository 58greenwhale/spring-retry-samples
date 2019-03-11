package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.classify.Classifier;
import org.springframework.classify.SubclassClassifier;

import com.example.demo.exception.ChildException;
import com.example.demo.exception.ParentException;

public class SubclassClassifierSample {
	
	public static void main(String[] args) {
		
		Map<Class<? extends Throwable>, String> typeMap = new HashMap<>();
		
		typeMap.put(ParentException.class, "ParentException");
		
		Classifier<Throwable,String>  classifier = new  SubclassClassifier<>(typeMap,"defaultValue");
		String defaultValue = classifier.classify(new Exception());
		
		System.out.println(String.format("defaultValue: %s", defaultValue));
		String parentException = classifier.classify(new ParentException("ParentException"));
		System.out.println(String.format("parentException: %s", parentException));
		
		String childException = classifier.classify(new ChildException("childException"));
		System.out.println(String.format("childException:%s", childException));
		
	}

}
