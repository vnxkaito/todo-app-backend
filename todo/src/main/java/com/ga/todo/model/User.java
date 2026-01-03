package com.ga.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password", "userProfile"})
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column(unique = true)
    private String emailAddress;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // one user can have only one profile (1 - 1)
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfile userProfile;


    @JsonIgnore
    public String getPassword(){
        return password;
    }

}
