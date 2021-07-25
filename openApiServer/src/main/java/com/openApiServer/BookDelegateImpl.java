package com.openApiServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.openApiServer.api.BookApiDelegate;
import com.openApiServer.model.Book;

@Service
public class BookDelegateImpl implements BookApiDelegate {


	public List<Book> bookList = new ArrayList<>();
	
	public BookDelegateImpl() {
		bookList.add(new Book()
				.id(100L)
                .title("Name of the Wind")
                .author("Patrick")
                .price("1000"));
		bookList.add(new Book()
				.id(101L)
                .title("Mistborn")
                .author("Brandon")
                .price("2000"));
	}
	
	@Override
	public ResponseEntity<List<Book>> allBooks() {
        return ResponseEntity.ok(this.bookList);
	}

	@Override
	public ResponseEntity<Book> create(Book book) {
		this.bookList.add(book);
		return ResponseEntity.ok(book);
	}

	@Override
	public ResponseEntity<Void> delete(Long id) {
		Iterator<Book> itr = this.bookList.iterator();
        while (itr.hasNext())
        {
            Book book = itr.next();
            if (!book.id(id).equals(null))
                itr.remove();
        }
		return BookApiDelegate.super.delete(id);
	}

	@Override
	public ResponseEntity<Book> find(Long id) {
		Iterator<Book> itr = this.bookList.iterator();
		Book foundBook = new Book();
        while (itr.hasNext())
        {
            Book book = itr.next();
            if (!book.id(id).equals(null))
            	foundBook = book;
        }
		return ResponseEntity.ok(foundBook);
	}

	@Override
	public ResponseEntity<Book> updateBook(Long id, Book book) {
		Iterator<Book> itr = this.bookList.iterator();
		Book foundBook = new Book();
        while (itr.hasNext())
        {
            Book itrBook = itr.next();
            if (!itrBook.id(id).equals(null)) {
            	foundBook.setAuthor(book.getAuthor());
            	foundBook.setPrice(book.getPrice());
            	foundBook.setTitle(book.getTitle());
            }
        }
		return ResponseEntity.ok(foundBook);
	}
}
