package com.security.Repository;

import com.security.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String>{

    User findByUserName(String username);

    void deleteByUserName(String name);
}
