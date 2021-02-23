package ru.itis.models;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private long id;
    private String email;
    private String name;
    private String password;
    private String sessionId;
    private String confirmCode;
    private State state;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }
}
