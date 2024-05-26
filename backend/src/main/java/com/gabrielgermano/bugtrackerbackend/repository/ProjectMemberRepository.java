package com.gabrielgermano.bugtrackerbackend.repository;

import com.gabrielgermano.bugtrackerbackend.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

}
