package ru.gb.userIceBoxCheck.model;

import jakarta.persistence.*;
import lombok.Data;

import ru.gb.userIceBoxCheck.service.RandomService;

import java.util.List;

@Data
@Entity
@Table(name = "icebox")
public class IceBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "list")
    private List<String> list;

    public IceBox() {
        RandomService randomService = new RandomService();
        this.list = randomService.randomList();
    }

    @OneToOne
}
