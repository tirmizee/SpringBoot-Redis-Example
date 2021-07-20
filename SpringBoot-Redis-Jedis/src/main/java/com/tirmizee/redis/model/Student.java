package com.tirmizee.redis.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.tirmizee.redis.definition.RedisConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(RedisConstant.STUDENT_HAST)
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum Gender { 
        MALE, 
        FEMALE
    }
	
	@Id
	private String id;
    private String name;
    private Gender gender;
    private int grade;
    
    public void incrementGrade(int increment) {
    	this.grade += increment;
    }
    
    public void decrementGrade(int decrement) {
    	this.grade -= decrement;
    }

}
