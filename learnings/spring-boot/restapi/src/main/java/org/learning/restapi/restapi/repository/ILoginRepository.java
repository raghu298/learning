package org.learning.restapi.restapi.repository;

import org.learning.restapi.restapi.entities.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginRepository extends CrudRepository<Login, Integer> {
	
	 Login findByUsername(String username);
}
