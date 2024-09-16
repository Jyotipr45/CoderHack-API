package com.crio.coderhack.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.coderhack.entities.User;

public interface UserRepository extends MongoRepository<User, String>{
    
}
