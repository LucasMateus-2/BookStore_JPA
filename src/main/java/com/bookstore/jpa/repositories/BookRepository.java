package com.bookstore.jpa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.bookModel;

public interface BookRepository extends JpaRepository<bookModel, UUID>
{
	bookModel findbookModleByTitle(String title);

}
