package ru.itis.models;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String email;
    private String name;
    private String password;
}
