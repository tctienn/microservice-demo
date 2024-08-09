package demo.com.demoservice.command.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BookDeleteEvent {
	@TargetAggregateIdentifier
	private String bookId;

	
	public BookDeleteEvent() {
		super();
	}

	public BookDeleteEvent(String bookId) {
		super();
		this.bookId = bookId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	
}
