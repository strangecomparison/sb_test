package ru.vasseugs.sb_test.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.vasseugs.sb_test.domain.entity.UserEntity;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void testGetAllUsers() {
        List<UserEntity> allUsers = userService.getAllUsers();
        assertEquals(allUsers.size(), 3);
    }

    @Test
    void testGetUser() throws Exception {
        UserEntity userToCheck = userService.getUser(1L);

        java.sql.Date date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd")
                .parse("1980-04-12")
                .getTime());

        UserEntity user = new UserEntity(1L,
                "Ivan Ivanov",
                "ivan",
                "ivanivan",
                "ivan@mail.com",
                date);

        assertEquals(userToCheck, user);
    }

    @Test
    @Transactional
    void testSaveUser() throws Exception {
        java.sql.Date date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd")
                .parse("1980-04-12")
                .getTime());

        UserEntity savedUser = new UserEntity(4L,
                "Ovan Ovanov",
                "ovan",
                "ovanivan",
                "ovan@mail.com",
                date);
        userService.saveUser(savedUser);

        UserEntity retrievedUser = userService.getUser(savedUser.getId());
        assertEquals(savedUser, retrievedUser);
    }

    @Test
    @Transactional
    void testEditUser() throws Exception {
        java.sql.Date date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd")
                .parse("1980-04-12")
                .getTime());

        UserEntity savedUser = new UserEntity(1L,
                "Ovan Ovanov",
                "ovan",
                "ovanivan",
                "ovan@mail.com",
                date);
        userService.saveUser(savedUser);

        UserEntity retrievedUser = userService.getUser(savedUser.getId());
        assertEquals(savedUser, retrievedUser);
    }

    @Test
    @Transactional
    void testDeleteUser() {
        userService.deleteUser(1L);

        UserEntity retrievedUser = null;
        try{
            retrievedUser = userService.getUser(1L);
        } catch (Exception e) {
            assertNull(retrievedUser);
        }
    }

}
