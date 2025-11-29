package com.security.Repository;

import com.security.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId>{
    Optional<JournalEntity> findById(String id);

    void deleteById(String id);
}
