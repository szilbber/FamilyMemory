package org.example.memorymap.service;

import org.example.memorymap.dto.UserCreateRequest;
import org.example.memorymap.dto.UserUpdateRequest;
import org.example.memorymap.entity.User;
import org.example.memorymap.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(UserCreateRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(request.password());
        return userRepository.save(user);
    }

    public void delete(Long id){
        User user = getById(id);
        userRepository.delete(user);
    }

    public User update(Long id, UserUpdateRequest request) {
        User updatedUser = getById(id);
        updatedUser.setName(request.name());
        updatedUser.setEmail(request.email());
        return userRepository.save(updatedUser);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
