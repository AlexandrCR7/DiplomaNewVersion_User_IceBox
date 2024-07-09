package ru.gb.userIceBoxCheck.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    //    @OneToOne
    @OneToOne(mappedBy = "icebox")
    @JsonBackReference
    private User user;

    public IceBox() {
        RandomService randomService = new RandomService();
        this.list = randomService.randomList();
    }


}
