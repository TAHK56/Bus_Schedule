package com.geeksforless.station.persistence.repository.user;

import com.geeksforless.station.persistence.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<U extends User> extends JpaRepository<U,Integer> {


}