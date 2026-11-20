
package tn.esprit.userservice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.userservice.Dto.UserRequestDTO;
import tn.esprit.userservice.Dto.UserResponseDTO;

import tn.esprit.userservice.Entities.User;

import tn.esprit.userservice.Mapper.UserMapper;
import tn.esprit.userservice.Repositories.UserRepository;
import tn.esprit.userservice.Service.UserService;
import tn.esprit.userservice.Exceptions.ConflictException;
import tn.esprit.userservice.Exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new ConflictException("Email already exists");
        }

        if (userRepository.existsByKeycloakId(dto.keycloakId())) {
            throw new ConflictException("Keycloak ID already exists");
        }

        User user = UserMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        return UserMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        return UserMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UserRequestDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        if (!user.getEmail().equals(dto.email())
                && userRepository.existsByEmail(dto.email())) {

            throw new ConflictException("Email already exists");
        }

        user.setNom(dto.nom());
        user.setPrenom(dto.prenom());
        user.setEmail(dto.email());

        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        userRepository.delete(user);
    }
}
