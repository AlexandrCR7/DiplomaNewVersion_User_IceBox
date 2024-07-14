package ru.gb.userIceBoxCheck.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "icebox")
public class IceBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "ingredients")
    private String ingredients;

    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    @JsonBackReference
    private User user;

}
