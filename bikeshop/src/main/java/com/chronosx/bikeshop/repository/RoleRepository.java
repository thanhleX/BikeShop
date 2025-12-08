package com.chronosx.bikeshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chronosx.bikeshop.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String roleName);

    boolean existsRoleByRoleName(String roleName);
}
