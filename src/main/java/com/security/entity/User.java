package com.security.entity;

import com.security.Enum.sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    @Indexed(unique = true, sparse = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    private String email;

    private boolean sentimentAnalysis;
    @DBRef
    private List<JournalEntity> journalEntities = new ArrayList<>();

    private List<String> roles;
}
