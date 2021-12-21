package com.zama.bookstore.services;

import com.zama.bookstore.bookdto.BookDto;
import com.zama.bookstore.bookrepository.BookRepository;
import com.zama.bookstore.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        ModelMapper modelMapper = new ModelMapper();
        List<Book> books = bookRepository.findAll();
        modelMapper.map(books,BookDto.class);
        return Arrays.asList(modelMapper.map(books, BookDto[].class));
    }

}
