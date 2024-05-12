package com.innsys.usermanagement.repository;

import com.innsys.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    int countByUsername(String username);

}
