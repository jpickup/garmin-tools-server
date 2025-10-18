package com.johnpickup.garmintools.repository;

import java.util.List;

import com.johnpickup.garmintools.data.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);
}
