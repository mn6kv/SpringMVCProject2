package ru.itis.models;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String password;
    private String sessionId;
    private String confirmCode;
    @Enumerated(value = EnumType.STRING)
    private State state;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }
}