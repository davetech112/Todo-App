package com.todo.dave.TodoApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank
    @Length(min = 2, max = 16, message = "Invalid Password")
    private String password;
}
