package com.gastos.seguimiento.repository;

import com.gastos.seguimiento.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    // Método para verificar si ya existe un usuario con un email específico
    boolean existsByEmail(String email);
}
