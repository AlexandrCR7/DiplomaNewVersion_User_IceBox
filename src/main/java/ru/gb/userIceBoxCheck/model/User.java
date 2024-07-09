package ru.gb.userIceBoxCheck.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(nullable = false, name = "name")
    private String name;
    @Column(name = "products")
    private List<String> products;
    @Column(nullable = false, name = "icebox_id")
    private Integer iceBox_id;

    public User() {
        IceBox iceBox = new IceBox();
        this.products = iceBox.getList();
    }
//    @OneToOne

    @OneToOne
    @JoinTable(
            name = "icebox_ingredient",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "icebox_id")
    )
    @JsonManagedReference
    private IceBox icebox;

}
