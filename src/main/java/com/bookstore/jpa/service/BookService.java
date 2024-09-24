package com.bookstore.jpa.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.models.bookModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService
{

	private final BookRepository bookRepository;

	private final AuthorRepository authorRepository;
	private final PublisherRepository publisherRepository;

	public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
			PublisherRepository publisherRepository)
	{
		super();
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
	}

	@Transactional
	public bookModel saveBook(BookRecordDto bookRecordDto)
	{
		bookModel book = new bookModel();
		book.setTitle(bookRecordDto.title());
		book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
		book.setAuthors(authorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

		ReviewModel reviewModel = new ReviewModel();
		reviewModel.setComment(bookRecordDto.reviewComment());
		reviewModel.setBook(book);
		book.setReview(reviewModel);

		return bookRepository.save(book);
	}

}
