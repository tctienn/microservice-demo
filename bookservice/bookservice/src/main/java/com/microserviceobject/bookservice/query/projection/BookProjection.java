package com.microserviceobject.bookservice.query.projection;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microserviceobject.bookservice.command.data.Book;
import com.microserviceobject.bookservice.command.data.BookRepository;
import com.microserviceobject.bookservice.query.model.BookResponseModel;
import com.microserviceobject.bookservice.query.queries.GetAllBookQuery;
import com.microserviceobject.bookservice.query.queries.GetBookQuery;

@Component
public class BookProjection {

	@Autowired
	private BookRepository bookRepository;
	
	@QueryHandler
	public BookResponseModel handle(GetBookQuery getBookQuery) {
		BookResponseModel model = new BookResponseModel();
		Book book = bookRepository.findById(getBookQuery.getBookId()).orElse(null);
		if(book !=null) {
			System.out.println("có data -------------------------");
			BeanUtils.copyProperties(book, model);
		}
		return model;
	}
	
	@QueryHandler List<BookResponseModel> handle(GetAllBookQuery getAllBookQuery){
		List<Book> listEntity = bookRepository.findAll();
		List<BookResponseModel> listbook = new ArrayList<BookResponseModel>();
		listEntity.forEach(s ->{
			BookResponseModel model = new BookResponseModel();
			BeanUtils.copyProperties(s, model);
			listbook.add(model);
		});
		return listbook;
	}
}
