package ru.vasseugs.sb_test.service;

import org.springframework.stereotype.Service;
import ru.vasseugs.sb_test.domain.entity.UserEntity;
import ru.vasseugs.sb_test.domain.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    public UserEntity getUser(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with this id is not found"));
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    public void editUser(UserEntity user) throws Exception {
        UserEntity editedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new Exception("User with this id is not found"));

        editedUser.setLogin(user.getLogin());
        editedUser.setEmail(user.getEmail());
        editedUser.setName(user.getName());
        editedUser.setDateOfBirth(user.getDateOfBirth());
        editedUser.setPassword(user.getPassword());

        userRepository.save(editedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
