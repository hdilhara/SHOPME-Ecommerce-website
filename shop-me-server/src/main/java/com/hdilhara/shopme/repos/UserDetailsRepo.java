package com.hdilhara.shopme.repos;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.shopme.entity.UserDetails;

public interface UserDetailsRepo extends CrudRepository<UserDetails, String> {

}
