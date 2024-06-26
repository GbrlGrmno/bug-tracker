package com.gabrielgermano.bugtrackerbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor @NoArgsConstructor
@Data @Builder
@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;






}
