package ru.gb.userIceBoxCheck.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "name")
    private String name;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private IceBox icebox;

}
