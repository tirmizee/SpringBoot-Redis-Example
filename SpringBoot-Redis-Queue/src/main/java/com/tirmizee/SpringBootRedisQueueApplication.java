package com.tirmizee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.ListOperations;

@SpringBootApplication
public class SpringBootRedisQueueApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisQueueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ListOperations<String, String> listOperations = applicationContext.getBean("listOperations", ListOperations.class);
		listOperations.rightPush("queue", "1");
		listOperations.rightPush("queue", "2");
		listOperations.rightPush("queue", "3");
		listOperations.rightPush("queue", "4");
		listOperations.rightPush("queue", "5");
		listOperations.rightPush("queue", "6");
		listOperations.rightPush("queue", "7");
		listOperations.rightPush("queue", "8");
		
//		System.out.println(listOperations.leftPop("queue"));
//		System.out.println(listOperations.leftPop("queue"));
//		System.out.println(listOperations.leftPop("queue"));
//		System.out.println(listOperations.leftPop("queue"));
//		System.out.println(listOperations.leftPop("queue"));
		
		String val = null;
		while ((val = listOperations.leftPop("queue")) != null) {
			System.out.println(val);
		}
		
	}

}
