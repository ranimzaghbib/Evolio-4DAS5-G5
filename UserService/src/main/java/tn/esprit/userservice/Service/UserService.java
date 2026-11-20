
package tn.esprit.userservice.Service;

import tn.esprit.userservice.Dto.UserRequestDTO;
import tn.esprit.userservice.Dto.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    UserResponseDTO getUserById(UUID id);

    UserResponseDTO getUserByEmail(String email);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(UUID id, UserRequestDTO dto);

    void deleteUser(UUID id);
}
