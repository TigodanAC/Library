package com.example.library.library.controller;

import com.example.library.library.service.BookService;
import com.example.library.library.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationController {

    private BookService bookService;
    private UserService userService;

    public NavigationController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/users"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/user";
    }

    @GetMapping("/references")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'LIBRARIAN')")
    public String references() {
        return "references/references";
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'LIBRARIAN')")
    public String statistics() {
        return "statistics";
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('USER')")
    public String getBooksForUser(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }
}
