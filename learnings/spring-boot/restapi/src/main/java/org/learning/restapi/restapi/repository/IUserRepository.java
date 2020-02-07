package org.learning.restapi.restapi.repository;

import java.util.List;

import org.learning.restapi.restapi.entities.Login;
import org.learning.restapi.restapi.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Biradar
 *
 */
@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Integer> {

	List<User> findByName(String name);

	List<User> findByAge(int age);

	List<User> findByAgeLessThan(int age);

	
	
	@Query(value = "SELECT * FROM rest_api.tbl_user where user_address = ?1", nativeQuery = true)
	List<User> findByAddress(String address);

	@Query(value = "SELECT * FROM rest_api.tbl_user where year(cast(user_dob as date)) between ?1 and  ?2", nativeQuery = true)
	List<User> findBetweenDob(int year1, int year2);

	@Query(value = "select * from rest_api.tbl_user where user_age= ?1", countQuery = "select count(*) from rest_api.tbl_user where user_age = ?1", nativeQuery = true)
	Page<User> getdetailsByAge(int age, Pageable pageable);

	@Query(value = "select new User(u.name, u.id) from User u where u.name like ?1%")
	List<User> findByNameAndSort(String name, Sort sort);

}
