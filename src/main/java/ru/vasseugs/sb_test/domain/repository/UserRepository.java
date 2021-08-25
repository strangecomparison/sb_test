package ru.vasseugs.sb_test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasseugs.sb_test.domain.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public List<UserEntity> findAllByOrderByIdAsc();
}
