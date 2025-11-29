package com.security.sheduler;

import com.security.Enum.sentiment;
import com.security.Repository.UserRepositoryImpl;
import com.security.entity.JournalEntity;
import com.security.entity.User;
import com.security.service.EmailService;
import com.security.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysis;


//    @Scheduled(cron = "0 * * ? * *")
//    @Scheduled(cron = "0 0 9 * * SUN")
//    public void fetchUserAndSendEmail(){
//        List<User> users = userRepository.getUsersForSa();
//        for (User user: users){
//            List<JournalEntity> journalEntities = user.getJournalEntities();
//            List<String> entities = journalEntities.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(m->m.getContent()).collect(Collectors.toList());
//            String entry = String.join(" ", entities);
//            String sentiment = sentimentAnalysis.getSentiment(entry);
//            emailService.sendEmail(user.getEmail(),"Sentiment Analysis for Last 7 days ",sentiment);
//        }
//    }

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUsersForSa();
        for (User user : users) {
            List<JournalEntity> journalEntries = user.getJournalEntities();
            List<sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<sentiment, Integer> sentimentCounts = new HashMap<>();
            for (sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<sentiment, Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
            }
        }
            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days ",mostFrequentSentiment.toString());
            }
    }

//    @Scheduled(cron = "0 0/10 * ? * *")
//    public void clearAppCache() {
//        appCache.init();
//    }

}
}
