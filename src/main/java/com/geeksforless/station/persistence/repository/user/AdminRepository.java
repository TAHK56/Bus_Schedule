package com.geeksforless.station.persistence.repository.user;

import com.geeksforless.station.persistence.entity.user.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}