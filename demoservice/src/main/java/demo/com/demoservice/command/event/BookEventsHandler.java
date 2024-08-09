package demo.com.demoservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.com.demoservice.command.data.Book;
import demo.com.demoservice.command.data.BookRepository;


@Component
public class BookEventsHandler {

	@Autowired
	private BookRepository bookRepository;
	
	@EventHandler
	public void on(BookCreaterEvent event) {
		Book book = new Book();
		BeanUtils.copyProperties(event, book);
		bookRepository.save(book);
	}
	@EventHandler
	public void on(BookUpdateEvent event) {
		
		Book book = bookRepository.findById(event.getBookId()).orElse(null);
		book.setName(event.getName());
		book.setAuthor(event.getAuthor());
		book.setIsReady(event.getIsReady());
		bookRepository.save(book);
	}
	@EventHandler
	public void on(BookDeleteEvent event) {
		bookRepository.deleteById(event.getBookId());
	}
}
