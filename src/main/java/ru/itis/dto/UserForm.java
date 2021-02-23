package ru.itis.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String email;
    private String name;
    private String password;
}
