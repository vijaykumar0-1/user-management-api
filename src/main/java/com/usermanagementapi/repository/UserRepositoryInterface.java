package com.usermanagementapi.repository;

import com.usermanagementapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositoryInterface extends MongoRepository<User,String>
{
    User findByEmail(String email);
    User findByUsername(String username);
}
