package io.github.vieirajunior90.apiwithtests.repository;

import io.github.vieirajunior90.apiwithtests.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}