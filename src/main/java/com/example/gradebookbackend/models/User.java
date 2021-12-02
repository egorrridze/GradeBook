package com.example.gradebookbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "group_id")
    private Integer group_id;

    @Column(name = "subject_id")
    private Integer subject_id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            schema = "public"
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private Group group;

    public String getGroupName() {
        return group.getName();
    }

    public String getSurnameInitials() {
        return this.getSurname().toString() + " " +
                this.getName().charAt(0) + "." +
                (this.getPatronymic().isEmpty() ? "" : this.getPatronymic().charAt(0) + ".");
    }

    public boolean isEnabled() {
        //return user.isEnabled();
        return true;
    }
}
