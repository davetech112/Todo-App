package com.todo.dave.TodoApp.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id")
    private Long id;
    @Column(unique = true, nullable = false)

    private String username;
    @Column(nullable = false)
    private String password;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private List<TodoItem> todoItems = new ArrayList<>();
//    @OneToMany(targetEntity = TodoItem.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "ids", referencedColumnName = "id")
//    private List<TodoItem> todoItems;

//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }


}
