package com.tirmizee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.tirmizee.dto.EmployeeDTO;
import com.tirmizee.redis.model.Student;
import com.tirmizee.redis.repository.StudentRepository;

@SpringBootApplication
public class SpringBootRedirApplication implements CommandLineRunner{

	@Autowired
	public ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedirApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisObjectTemplate = context.getBean("redisObjectTemplate", RedisTemplate.class);
		
		@SuppressWarnings("unchecked")
		RedisTemplate<String, String> redisStringTemplate = context.getBean("redisStringTemplate", RedisTemplate.class);
		
		StudentRepository studentRepository = context.getBean(StudentRepository.class);
		
		Student student = new Student("Eng2015001", "John Does2", Student.Gender.MALE, 1);
		student.incrementGrade(1);
		studentRepository.save(student);
		
		
//		valueOperationExample(redisObjectTemplate);
		
//		setOperationExample(redisStringTemplate.opsForSet());
		
//		hashOperationExample(redisObjectTemplate);

	}
	
	public void setOperationExample(SetOperations<String, String> setOperations) {
		setOperations.add("NAMES", "Pratya", "Tirmizee");
		setOperations.add("NAMES", "C");
	}
	
	public void valueOperationExample(RedisTemplate<String, Object> redisTemplate) {
		ValueOperations<String, Object> valueIntegerOperations = redisTemplate.opsForValue();
//		valueIntegerOperations.set("ID2", 56161177);
		valueIntegerOperations.increment("ID2");
		valueIntegerOperations.increment("ID2");
		valueIntegerOperations.increment("ID2");
	}
	
	public void hashOperationExample(RedisTemplate<String, Object> redisObjectTemplate) {
		
		HashOperations<String, String, Object> hashOperations = redisObjectTemplate.opsForHash();
		hashOperations.put("MAP", "useId", 9999);
		hashOperations.put("MAP", "userName", "Tirmizee");
		hashOperations.put("MAP", "running", 1);
		hashOperations.increment("MAP", "running", 1);
		hashOperations.increment("MAP", "running", -1);
		
		HashOperations<String, Long, EmployeeDTO> emplyeeOperations = redisObjectTemplate.opsForHash();
		emplyeeOperations.put("EMPLOYEE", 1L, new EmployeeDTO(1L, "Pratya2", "Tirmizee"));
		
		System.out.println(emplyeeOperations.get("EMPLOYEE", 1L).getFirstName());
		
	}
	
 
}
