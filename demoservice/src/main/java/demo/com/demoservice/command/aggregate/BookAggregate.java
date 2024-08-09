package demo.com.demoservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import demo.com.demoservice.command.command.CreateBookCommand;
import demo.com.demoservice.command.command.DeleteBookCommand;
import demo.com.demoservice.command.command.UpdateBookCommand;
import demo.com.demoservice.command.event.BookCreaterEvent;
import demo.com.demoservice.command.event.BookDeleteEvent;
import demo.com.demoservice.command.event.BookUpdateEvent;



@Aggregate
public class BookAggregate {
	@AggregateIdentifier
	private String bookId;
	private String name;
	private String author;
	private Boolean isReady;
	public BookAggregate() {
		super();
	}
	
	@CommandHandler
	public BookAggregate(CreateBookCommand createBookCommand) {
		BookCreaterEvent bookCreateEvent = new BookCreaterEvent();
		BeanUtils.copyProperties(createBookCommand, bookCreateEvent);
		AggregateLifecycle.apply(bookCreateEvent);
	}
	
	@EventHandler
	public void on(BookCreaterEvent event) {
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.isReady = event.getIsReady();
		this.name = event.getName();
	}
	
	@CommandHandler
	public void handle (UpdateBookCommand updateBookCommand) {
		BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
		BeanUtils.copyProperties(updateBookCommand, bookUpdateEvent);
		AggregateLifecycle.apply(bookUpdateEvent);
	}
	@EventHandler
	public void handle(BookUpdateEvent event) {
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.isReady = event.getIsReady();
		this.name = event.getName();
	}
	@CommandHandler
	public void handle (DeleteBookCommand deleteBookCommand) {
		BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
		bookDeleteEvent.setBookId(deleteBookCommand.getBookId());
		AggregateLifecycle.apply(bookDeleteEvent);
	}
	@EventHandler
	public void handle(BookDeleteEvent event) {
		this.bookId = event.getBookId();
		
	}
	
	
	
	
}
