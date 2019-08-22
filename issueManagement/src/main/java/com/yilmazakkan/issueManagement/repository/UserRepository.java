package com.yilmazakkan.issueManagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.yilmazakkan.issueManagement.entity.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

	
	
	Page<User> findAll(Pageable pageable);  //tablodan kayıt çekerken sayfalama yapar kaçın sayfadan başlasın sıralama yaosın mı...
	
	List<User> findAll(Sort sort); // sıralamayı sadecede yapabiliyoruz

	User findByUsername(String username);
	

}
