package com.microserviceobject.bookservice.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserviceobject.bookservice.query.model.BookResponseModel;
import com.microserviceobject.bookservice.query.queries.GetAllBookQuery;
import com.microserviceobject.bookservice.query.queries.GetBookQuery;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {
	@Autowired
	private QueryGateway queryGateway;
	
	@GetMapping("/{bookId}")
	public BookResponseModel getBookDetail(@PathVariable String bookId) {
		GetBookQuery getBooksQuery = new GetBookQuery();
		getBooksQuery.setBookId(bookId);
		BookResponseModel bookResponseModel = queryGateway.query(getBooksQuery, ResponseTypes.instanceOf(BookResponseModel.class)).join();
		return bookResponseModel;
	}
	@GetMapping
	public List<BookResponseModel> getAllBooks(){
		GetAllBookQuery getAllBookQuery = new GetAllBookQuery();
		List<BookResponseModel> list = queryGateway.query(getAllBookQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
		return list;
	}
}
