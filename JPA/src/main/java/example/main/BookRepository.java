package example.main;

import example.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAuthorByTitle(String title);
    Optional<Book> findById(int id);
    void deleteByAuthor( String author);
}
