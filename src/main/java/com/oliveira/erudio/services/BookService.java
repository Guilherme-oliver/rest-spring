package com.oliveira.erudio.services;

import com.oliveira.erudio.controllers.BookController;
import com.oliveira.erudio.data.vo.v1.BookVO;
import com.oliveira.erudio.exceptions.RequiredObjectIsNullException;
import com.oliveira.erudio.exceptions.ResourceNotFoundException;
import com.oliveira.erudio.mapper.DozerMapper;
import com.oliveira.erudio.model.Book;
import com.oliveira.erudio.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    private Logger logger = Logger.getLogger(BookService.class.getName());
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookVO> findAll() {
        logger.info("Finding all books");

        var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
        books
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return books;
    }

    public BookVO findById(Long id) {
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        try {
            vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public BookVO create(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one book!");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo =  DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        try {
            vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public BookVO update(BookVO bookVO) {

        if (bookVO == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one book!");

        var entity = bookRepository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setPrice(bookVO.getPrice());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setTitle(bookVO.getTitle());

        var vo =  DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        try {
            vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one book!");

        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        bookRepository.delete(entity);
    }
}