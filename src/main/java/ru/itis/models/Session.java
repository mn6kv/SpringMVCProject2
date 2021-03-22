package ru.itis.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
public class Session {
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String series;
    private String token;
    private Date lastUsed;
}