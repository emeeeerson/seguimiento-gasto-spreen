package com.gastos.seguimiento.service.user;

import com.gastos.seguimiento.model.user.User;
import com.gastos.seguimiento.repository.UserRepository;
import com.gastos.seguimiento.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException.UserNotFoundException("User with ID " + id + " not found"));
    }

    public User save(User user) {
        // Validar datos del usuario (puedes agregar más validaciones según tus reglas)
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserException.InvalidUserDataException("Email is required");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserException.UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }

        // Cifrar la contraseña antes de guardarla
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }

        return userRepository.save(user);
    }

    public User updateUser(String id, User userDetails) {
        User user = findById(id);  // Asegúrate de que este método encuentra al usuario en la base de datos

        // Verificar si el correo actualizado ya pertenece a otro usuario
        User existingUser = userRepository.findByEmail(userDetails.getEmail()).orElse(null);
        if (existingUser != null && !existingUser.getId().equals(id)) {
            throw new UserException.UserAlreadyExistsException("Email " + userDetails.getEmail() + " already in use");
        }

        // Actualizar campos
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setTelefono(userDetails.getTelefono());
        user.setDireccion(userDetails.getDireccion());

        // Solo actualiza la contraseña si se proporciona
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
            user.setPassword(encodedPassword);
        }

        return userRepository.save(user);
    }

    public void delete(String id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
