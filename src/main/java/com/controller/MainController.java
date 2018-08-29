package com.controller;


import com.service.PublisherService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Locale;

@Controller
@RequestMapping(value = "/*")
public class MainController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BookService bookService;

    @Autowired
    private PublisherService publisherService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Locale locale, Model model) {

        return "index";
    }


    //BOOK CONTROLLERS

    @RequestMapping(value = "/loadBooks.ajax")
    public @ResponseBody
    String loadBooks( HttpServletRequest request, HttpServletResponse response) {

        JSONObject jsonObject = bookService.loadBooks();
        return jsonObject.toString();


    }

    @RequestMapping(value = "/saveOrUpdateBook.ajax")
    public @ResponseBody
    String saveOrUpdateBooks(@RequestParam String data, HttpServletRequest request, HttpServletResponse response) throws ParseException {

        JSONObject jsonObject = bookService.saveOrUpdateBook(JSONObject.fromObject(data));
        return jsonObject.toString();


    }


    @RequestMapping(value = "/deleteBooks.ajax")
    public @ResponseBody
    String deleteBook(@RequestParam Long bookId, HttpServletRequest request, HttpServletResponse response) throws ParseException {

        JSONObject jsonObject = bookService.deleteBook(bookId);
        return jsonObject.toString();


    }


    //PUBLISHER CONTROLLERS


    @RequestMapping(value = "/loadPublishers.ajax")
    public @ResponseBody
    String loadPublishers(HttpServletRequest request, HttpServletResponse response) {

        JSONObject jsonObject = publisherService.loadPublishers();
        return jsonObject.toString();


    }

    @RequestMapping(value = "/saveOrUpdatePublisher.ajax")
    public @ResponseBody
    String saveOrUpdatePublisher(@RequestParam String data, HttpServletRequest request, HttpServletResponse response) throws ParseException {

        JSONObject jsonObject = publisherService.saveOrUpdatePublisher(JSONObject.fromObject(data));
        return jsonObject.toString();


    }

    @RequestMapping(value = "/deletePublisher.ajax")
    public @ResponseBody
    String deletePublisher(@RequestParam Integer publisherId, HttpServletRequest request, HttpServletResponse response) throws ParseException {

        JSONObject jsonObject = publisherService.deletePublisher(publisherId);
        return jsonObject.toString();


    }


}