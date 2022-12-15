package com.ticket.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticket.entities.Contact;
import com.ticket.entities.User;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	//pagination...
	
	@Query("from Contact as c where c.user.id =:userId")
	public List<Contact> findContactsByUser(@Param("userId")int userId);
	
}