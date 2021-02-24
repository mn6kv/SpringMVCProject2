package ru.itis.dto;

import lombok.*;
import ru.itis.validation.ValidPassword;

import javax.validation.constraints.Email;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    @Email(message = "{errors.incorrect.email}")
    private String email;
    private String name;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
}
