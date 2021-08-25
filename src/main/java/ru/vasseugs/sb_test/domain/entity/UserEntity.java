package ru.vasseugs.sb_test.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 70, message = "Your name must be between 2 and 50 symbols length")
    @Pattern(regexp = "[A-Z][a-z]+ [A-Z][a-z]+", message = "Name format is \"Name Lastname\"")
    private String name;

    @NotEmpty
    @Size(min = 3, max = 50, message = "Your login must be between 3 and 50 symbols length")
    private String login;

    @NotEmpty
    @Size(min = 8, max = 100, message = "Your password must be between 8 and 100 symbols length")
    private String password;

    @NotEmpty
    @Email(message = "Enter correct email")
    private String email;

    @Column(name="date_of_birth")
    private Date dateOfBirth;
}
