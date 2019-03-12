package com.example.demo.support;

import java.util.List;

import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.SleepingBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetrySimulation;
import org.springframework.retry.support.RetrySimulation.SleepSequence;
import org.springframework.retry.support.RetrySimulator;

public class RetrySimulatorSample {

	public static void main(String[] args) {
		
//		SleepingBackOffPolicy<?> backOffPolicy = new FixedBackOffPolicy(); // 默认backOffPeriod为1000ms
		SleepingBackOffPolicy<?> backOffPolicy = new ExponentialBackOffPolicy(); 
		RetryPolicy RetryPolicy = new SimpleRetryPolicy(5);//最多重试5次
		RetrySimulator simulator = new RetrySimulator(backOffPolicy, RetryPolicy);
		List<Long> sleeps0 = simulator.executeSingleSimulation();
		sleeps0.forEach(item -> {
			System.out.println(String.format("Sleep: %sms", item));//FixedBackOffPolicy的默认值为1000ms，所以此处打印的都是1000ms
		});
		
		RetrySimulation retrySimulation = simulator.executeSimulation(3);
		List<Double> percentiles = retrySimulation.getPercentiles();
		percentiles.forEach(item->{
			System.out.println(String.format("Percentile: %s", item));
		});
		
		SleepSequence sleepSequence = retrySimulation.getLongestTotalSleepSequence();
		List<Long> sleeps = sleepSequence.getSleeps();
		sleeps.forEach(item->{
			System.out.println(String.format("Sleep: %sms", item));//FixedBackOffPolicy的默认值为1000ms，所以此处打印的都是1000ms
		});
		
	}
}
