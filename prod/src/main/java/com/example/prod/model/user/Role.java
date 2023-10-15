package com.example.prod.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles", schema = "hr_back")
@Data
public class Role {
    @Id
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
