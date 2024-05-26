package com.gabrielgermano.bugtrackerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    private Long createdBy;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectMember> projectMembers;

    public void addMember(User user) {
        ProjectMember projectMember = new ProjectMember();

        projectMember.setProject(this);
        projectMember.setUser(user);


        projectMembers.add(projectMember);
    }



}
