package jina.hyunseo.synergy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jina.hyunseo.synergy.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); // 이미 email을 통해 생성된 사용자인지 체크
}