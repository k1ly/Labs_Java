package by.belstu.it.lyskov.controller;

import by.belstu.it.lyskov.model.Book;
import by.belstu.it.lyskov.form.BookForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class BookController {

    private static final List<Book> bookList = new ArrayList<>();

    static {
        bookList.add(new Book("Full Stack Development with JHipster", "Deepu K Sasidharan, Sendil Kumar N"));
        bookList.add(new Book("Pro Spring Security", "Carlo Scarioni, Massimo Nardone"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called");
        return modelAndView;
    }

    @GetMapping(value = {"/books"})
    public ModelAndView showBookListPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookList");
        model.addAttribute("bookList", bookList);
        log.info("/books was called");
        return modelAndView;
    }

    @GetMapping(value = {"/addbook"})
    public ModelAndView showAddBookPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addBook");
        model.addAttribute("bookform", new BookForm());
        log.info("/addbook was called");
        return modelAndView;
    }

    @PostMapping(value = {"/addbook"})
    public ModelAndView addBook(Model model, @ModelAttribute("bookform") BookForm bookForm) {
        ModelAndView modelAndView = new ModelAndView();
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();
        if (title != null && !title.isBlank() && author != null && !author.isBlank()) {
            Book newBook = new Book(title, author);
            bookList.add(newBook);
            modelAndView.setViewName("redirect:/books");
            model.addAttribute("bookList", bookList);
        } else {
            modelAndView.setViewName("addbook");
            model.addAttribute("errorMessage", errorMessage);
        }
        log.info("/addbook POST was called");
        return modelAndView;
    }

    @RequestMapping(value = {"/editbook"})
    public ModelAndView showEditBookPage(Model model, @RequestParam(value = "book", defaultValue = "") Integer bookIndex) {
        ModelAndView modelAndView = new ModelAndView();
        if (bookIndex == null) {
            modelAndView.setViewName("redirect:/books");
            model.addAttribute("bookList", bookList);
        } else {
            modelAndView.setViewName("editBook");
            Book book = bookList.get(bookIndex);
            model.addAttribute("bookIndex", bookIndex);
            model.addAttribute("bookform", new BookForm(book.getTitle(), book.getAuthor()));
        }
        log.info("/editbook was called");
        return modelAndView;
    }

    @PostMapping(value = {"/editbook"}, params = "delete")
    public ModelAndView deleteBook(Model model, @RequestParam(value = "book", defaultValue = "") Integer bookIndex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/books");
        model.addAttribute("bookList", bookList);
        if (bookIndex != null)
            bookList.remove((int) bookIndex);
        log.info("/editbook POST was called");
        return modelAndView;
    }

    @PostMapping(value = {"/editbook"}, params = "confirm")
    public ModelAndView editBook(Model model, @ModelAttribute("bookform") BookForm bookForm,
                                 @RequestParam("book") Integer bookIndex) {
        System.out.println(bookIndex);
        ModelAndView modelAndView = new ModelAndView();
        String title = bookForm.getTitle();
        String author = bookForm.getAuthor();
        Book book = bookList.get(bookIndex);
        if (title != null && !title.isBlank() && author != null && !author.isBlank()) {
            book.setTitle(title);
            book.setAuthor(author);
            modelAndView.setViewName("redirect:/books");
            model.addAttribute("bookList", bookList);
        } else {
            modelAndView.setViewName("editBook");
            model.addAttribute("bookIndex", bookIndex);
            model.addAttribute("errorMessage", errorMessage);
        }
        log.info("/editbook POST was called");
        return modelAndView;
    }
}
