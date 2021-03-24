package ru.itis.models;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String hashPassword;
    private String sessionId;
    private String confirmCode;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.state == State.CONFIRMED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }
}