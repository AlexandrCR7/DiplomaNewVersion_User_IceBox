package ru.gb.userIceBoxCheck.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "products")
    private List<String> products;

    public User() {
        IceBox iceBox = new IceBox();
        this.products = iceBox.getList();
    }
}
