package com.fullstack.backend_application.Repository;

import com.fullstack.backend_application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User , Long> {
}
