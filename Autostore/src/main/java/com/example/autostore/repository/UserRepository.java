package com.example.autostore.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.autostore.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    boolean existsByUserName(String userName);

    Optional<AppUser> findByUserName(String userName);
}
