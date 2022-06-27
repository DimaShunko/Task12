package example.service;

import example.main.BookRepository;
import example.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List getAllBooks() {
        List books = new ArrayList();
        bookRepository.findAll().forEach(book -> books.add(book));
        return books;
    }

    public void getBook(String title, String author, String release) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setRelease(release);
        bookRepository.save(book);
    }

    public Book getBookById(int id) {
        return  bookRepository.findById(id).get();
    }

    public List<Book> getAuthorByTitle(String title) {
        return  bookRepository.findAuthorByTitle(title);
    }

    public void delById(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void delByAuthor(String author) {
        bookRepository.deleteByAuthor(author);
    }
}
