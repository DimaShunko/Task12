package example.controller;

import example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    private List getAllBooks(@RequestParam(value = "title", defaultValue = "null") String title, @RequestParam(value = "author", defaultValue = "null") String author, @RequestParam(value = "release", defaultValue = "null") String release) {
        String regex1 = "[а-яА-Я]+";
        String regex2 = "[0-9]+";
        if ((title.matches(regex1)) & (author.matches(regex1)) & (release.matches(regex2))) {
            bookService.getBook(title, author, release);
        }

        return bookService.getAllBooks();
    }

    @GetMapping("/books/findById")
    private Object findByIdBook(@RequestParam(value = "id", defaultValue = "null") String id) {
        Object result;
        String regex = "[0-9]+";
        if ((id.matches(regex))) {
            try {
                result = bookService.getBookById(Integer.valueOf(id));
            } catch (NoSuchElementException nse) {
                result = "Элемент не найден";
            }
            return result;
        } else {
            return "Ошибка ввода";
        }
    }

    @GetMapping("/books/findByTitle")
    private List<Object> findAuthorByTitle(@RequestParam(value = "title", defaultValue = "null") String title) {
        List<Object> result = new ArrayList<>();
        String regex = "[а-яА-Я]+";
        if ((title.matches(regex))) {
            try {
                result.add(bookService.getAuthorByTitle(title));
            } catch (NoSuchElementException nse) {
                result.add("Элемент не найден");
            }
            return result;
        } else {
            result.add("Ошибка ввода");
            return result;
        }
    }

    @GetMapping("/books/delByAuthor")
    @Transactional
    String delByAuthorBook(@RequestParam(value = "author", defaultValue = "null") String author) {
        String result;
        String regex = "[а-яА-Я]+";
        if ((author.matches(regex))) {
            try {
                bookService.delByAuthor(author);
                result = "Удаление прошло успешно";
            } catch (EmptyResultDataAccessException erd) {
                result = "Элемент не найден";
            }
            return result;
        } else {
            return "Ошибка ввода";
        }
    }

    @GetMapping("/books/delById")
    private String delByIdBook(@RequestParam(value = "id", defaultValue = "null") String id) {
        String result;
        String regex = "[0-9]+";
        if ((id.matches(regex))) {
            try {
                bookService.delById(Integer.valueOf(id));
                result = "Удаление прошло успешно";
            } catch (EmptyResultDataAccessException erd) {
                result = "Элемент не найден";
            }
            return result;
        } else {
            return "Ошибка ввода";
        }
    }
}
