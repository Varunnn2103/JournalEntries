package com.security.service;

import com.security.entity.JournalEntity;
import com.security.entity.User;
import com.security.Repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {


    private final JournalEntryRepo journalEntryRepo;

    private static final Logger logger = LoggerFactory.getLogger(JournalService.class);

    public JournalService(JournalEntryRepo journalEntryRepo, UserService userService) {
        this.journalEntryRepo = journalEntryRepo;
        this.userService = userService;
    }

    private final UserService userService;


    @Transactional
    public void createJournalEntry(JournalEntity journalEntity, String userName){
        try {
            User user = userService.getByUserName(userName);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity save = journalEntryRepo.save(journalEntity);
            user.getJournalEntities().add(save);
            userService.createJournalEntry(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("An Error has Occurred during execution",e);
        }
    }

    public void createJournalEntry(JournalEntity journalEntity){
        journalEntryRepo.save(journalEntity);
    }


    public List<JournalEntity> getAll(){
        return journalEntryRepo.findAll();
    }


    @Transactional
    public boolean deleteEntry(String id, String userName){
        boolean removed = false;
        try {
            User user= userService.getByUserName(userName);
            removed = user.getJournalEntities().removeIf(x -> x.getId().equals(id));
            if(removed){
            userService.createJournalEntry(user);
            journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            logger.error("Some Error is occurred in the Entry deletion : {} :",userName);
            throw new IllegalArgumentException(e);
        }
        return removed;
    }

    public Optional<JournalEntity> getEntryById(String id){
        return journalEntryRepo.findById(id);
    }

    public Optional<JournalEntity> update(ObjectId id){
        return journalEntryRepo.findById(id);
    }


}
