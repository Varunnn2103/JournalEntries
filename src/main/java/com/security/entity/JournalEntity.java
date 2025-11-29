package com.security.entity;


import com.security.Enum.sentiment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@NoArgsConstructor
public class JournalEntity {


    @Id
    private ObjectId id;
    private String title;
    private String content;
    private sentiment sentiment;
    private LocalDateTime date;
}