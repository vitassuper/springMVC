package edu.javavt17.controllers;

import edu.javavt17.model.Book;
import edu.javavt17.model.BookAuthor;
import edu.javavt17.service.BookService;
import edu.javavt17.service.BookAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class JpaController {

    private static final String INSTRUMENT = "jpa";
    private static final String TITLE = "JPA";

    @Autowired
    @Qualifier("bookJpaService")
    private BookService bookService;
    @Autowired
    @Qualifier("bookAuthorJpaService")
    private BookAuthorService bookAuthorService;

    @RequestMapping(value = "/" + INSTRUMENT + "", method = RequestMethod.GET)
    public String printJdbc(ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("instrument", INSTRUMENT);

        List<BookAuthor> listBookAuthor = bookAuthorService.list();
        List<Book> listBook = bookService.list();

        model.addAttribute("listBook", listBook);
        model.addAttribute("listBookAuthor", listBookAuthor);

        return "content";
    }


    @RequestMapping(value = "/" + INSTRUMENT + "/newAuthor", method = RequestMethod.GET)
    public String addAuthor(ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Добавить нового");

        BookAuthor bookAuthor = new BookAuthor();
        model.addAttribute("author", bookAuthor);

        return "authorForm";
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/newAuthor"}, method = RequestMethod.POST)
    public String saveAuthor(BookAuthor bookAuthor) {

        bookAuthorService.saveOrUpdate(bookAuthor);
        return "redirect:/" + INSTRUMENT;
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/delete-author/{idAuthor}"}, method = RequestMethod.GET)
    public String deleteAuthor(@PathVariable int idAuthor) {
        bookAuthorService.delete(idAuthor);
        return "redirect:/" + INSTRUMENT;
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/edit-author/{idAuthor}"}, method = RequestMethod.GET)
    public String editAuthor(@PathVariable int idAuthor, ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Редактирование");

        BookAuthor bookAuthor = bookAuthorService.get(idAuthor);
        model.addAttribute("author", bookAuthor);
        model.addAttribute("edit", true);
        return "authorForm";
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/edit-author/{idAuthor}"}, method = RequestMethod.POST)
    public String updateAuthor(BookAuthor bookAuthor) {
        bookAuthorService.saveOrUpdate(bookAuthor);
        return "redirect:/" + INSTRUMENT;
    }

    @RequestMapping(value = "/" + INSTRUMENT + "/newBook", method = RequestMethod.GET)
    public String addModel(ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Add new");

        List<BookAuthor> listBookAuthor = bookAuthorService.list();
        System.out.println(listBookAuthor);
        Book book = new Book();
        model.addAttribute("listBookAuthor", listBookAuthor);
        model.addAttribute("book", book);

        return "bookForm";
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/newBook"}, method = RequestMethod.POST)
    public String saveModel(Book book) {

        int idAuthor = book.getIdAuthor();
        book.setBookAuthor(bookAuthorService.get(idAuthor));
        bookService.saveOrUpdate(book);
        return "redirect:/" + INSTRUMENT;
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/delete-book/{idModel}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int idModel) {
        bookService.delete(idModel);
        return "redirect:/" + INSTRUMENT;
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/edit-book/{idModel}"}, method = RequestMethod.GET)
    public String editModel(@PathVariable int idModel, ModelMap model) {
        model.addAttribute("title", TITLE);
        model.addAttribute("action", "Редактирование");

        Book book = bookService.get(idModel);
        List<BookAuthor> listBookAuthor = bookAuthorService.list();

        model.addAttribute("book", book);
        model.addAttribute("listBookAuthor", listBookAuthor);

        return "bookForm";
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/edit-book/{idModel}"}, method = RequestMethod.POST)
    public String updateModel(Book book) {

        int idAuthor = book.getIdAuthor();
        book.setBookAuthor(bookAuthorService.get(idAuthor));

        bookService.saveOrUpdate(book);
        return "redirect:/" + INSTRUMENT;
    }

    @RequestMapping(value = {"/" + INSTRUMENT + "/pdfReport", "/" + INSTRUMENT + "/xlsxReport.xlsx"}, method = RequestMethod.GET)
    public ModelAndView downloadReport(@RequestParam("view") String view) {
        ModelAndView modelAndView = new ModelAndView();

        List<BookAuthor> listBookAuthor = bookAuthorService.list();
        List<Book> listBook = bookService.list();
        // return a view which will be resolved by a ResourceBundleViewResolver
        modelAndView.addObject("bookAuthors", listBookAuthor);
        modelAndView.addObject("books", listBook);
        modelAndView.setViewName(view);

        return modelAndView;
    }
}

