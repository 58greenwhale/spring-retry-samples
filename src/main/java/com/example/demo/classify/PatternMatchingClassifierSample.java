package com.example.demo.classify;

import java.util.HashMap;
import java.util.Map;

import org.springframework.classify.Classifier;
import org.springframework.classify.PatternMatcher;
import org.springframework.classify.PatternMatchingClassifier;

import com.example.demo.exception.ChildException;
import com.example.demo.exception.ParentException;

public class PatternMatchingClassifierSample {

	public static void main(String[] args) {

		Map<String, Class<? extends Throwable>> map = new HashMap<>();
		map.put("*Parent*", new ParentException("ParentException").getClass());
		map.put("*Child*", new ChildException("ChildException").getClass());
		PatternMatcher<Class<? extends Throwable>> patternMatcher = new PatternMatcher<>(map);
		printPatternMatcher(patternMatcher);

		Classifier<String, Class<? extends Throwable>> classfier = new PatternMatchingClassifier<>(map);
		printClassifier(classfier);
	}

	private static void printPatternMatcher(PatternMatcher<Class<? extends Throwable>> patternMatcher) {
		Class<? extends Throwable> parentException = patternMatcher.match("ParentException");
		System.out.println(String.format("ParentException : %s", parentException));

		Class<? extends Throwable> childException = patternMatcher.match("ChildException");
		System.out.println(String.format("ChildException : %s", childException));
	}

	private static void printClassifier(Classifier<String, Class<? extends Throwable>> classfier) {
		Class<? extends Throwable> parentException = classfier.classify("ParentException");
		System.out.println(String.format("ParentException : %s", parentException));

		Class<? extends Throwable> childException = classfier.classify("ChildException");
		System.out.println(String.format("ChildException : %s", childException));
	}

}
