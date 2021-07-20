package com.tirmizee.redis.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import com.tirmizee.redis.model.Student;

@Repository
public interface StudentRepository extends KeyValueRepository<Student, String> {

}
