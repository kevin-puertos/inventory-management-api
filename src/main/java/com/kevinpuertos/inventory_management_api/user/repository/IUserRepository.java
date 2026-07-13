package com.kevinpuertos.inventory_management_api.user.repository;

import com.kevinpuertos.inventory_management_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
