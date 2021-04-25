package com.taw.student.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@SequenceGenerator(name = "seqIdGen", initialValue = 20000, allocationSize = 1)//allocationSize = "w grupach po"
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqIdGen")
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
}
