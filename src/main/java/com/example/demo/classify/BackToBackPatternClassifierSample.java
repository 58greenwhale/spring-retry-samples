package com.example.demo.classify;

import java.util.HashMap;
import java.util.Map;

import org.springframework.classify.BackToBackPatternClassifier;
import org.springframework.classify.Classifier;
import org.springframework.classify.PatternMatcher;
import org.springframework.classify.PatternMatchingClassifier;
import org.springframework.classify.SubclassClassifier;

import com.example.demo.exception.AnotherParentException;
import com.example.demo.exception.ChildException;
import com.example.demo.exception.ParentException;

public class BackToBackPatternClassifierSample {

	public static void main(String[] args) {
		Classifier<Throwable, String>  router  = createSubclassClassifier();
		Classifier<String,Class<? extends Throwable>>  matcher = createPatternMatchingClassifier();
		
		Classifier<Throwable,Class<? extends Throwable>> classfier = new BackToBackPatternClassifier<>(router,matcher);
		Class<? extends Throwable> result = classfier.classify(new ParentException("ParentException"));//应用程序抛出了一个ParentException异常
		System.out.println(String.format("ParentException Result: %s", result));
		
		Class<? extends Throwable> result2 = classfier.classify(new ChildException("ChildException"));//应用程序抛出了一个ChildException异常
		System.out.println(String.format("ChildException Result: %s", result2));
		
		
		Class<? extends Throwable> result3 = classfier.classify(new AnotherParentException("AnotherParentException"));//应用程序抛出了一个ParentException异常
		System.out.println(String.format("AnotherParentException Result: %s", result3));
	}
	
	public static Classifier<Throwable, String>  createSubclassClassifier(){
		Map<Class<? extends Throwable>, String> routerTypeMap = new HashMap<>();
		routerTypeMap.put(ParentException.class, "*Parent*");
		routerTypeMap.put(AnotherParentException.class, "*AnotherParenT*");
		Classifier<Throwable,String>  router = new  SubclassClassifier<>(routerTypeMap,"defaultValue");
		return router;
	}
	
	public static Classifier<String, Class<? extends Throwable>>  createPatternMatchingClassifier(){
		Map<String, Class<? extends Throwable>> map = new HashMap<>();
		map.put("*Parent*", new ParentException("ParentException").getClass());
		map.put("*Child*", new ChildException("ChildException").getClass());
		Classifier<String, Class<? extends Throwable>> classfier = new PatternMatchingClassifier<>(map);
		return classfier;
	}
}



















