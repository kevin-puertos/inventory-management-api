package io.github.macebot.inventory_management_api.user.entity;

import io.github.macebot.inventory_management_api.user.UserRole;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user_account")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUser;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole rol;



}
