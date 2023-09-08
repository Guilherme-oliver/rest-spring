package com.oliveira.erudio.controllers;

import com.oliveira.erudio.data.vo.v1.BookVO;
import com.oliveira.erudio.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.oliveira.erudio.util.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class BookController {

    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all Books", description = "Find all Books",
    tags = {"Book"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                    )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public List<BookVO> findAll() {
        return bookService.findAll();
    }

    @GetMapping(value = "/{id}",
        produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds one Book", description = "Finds one Book",
    tags = {"Book"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No Contente", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public BookVO findById(@PathVariable(value = "id") Long id) {
        return bookService.findById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds one Book", description = "Adds a new Book by a JSON, XML or YML representation of the book!",
    tags = {"Book"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public BookVO create(@RequestBody BookVO bookVO) {
        return bookService.create(bookVO);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Update one Book", description = "Update a Book by a JSON, XML or YML representation of the Book!",
    tags = {"Book"},
    responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public BookVO update(@RequestBody BookVO bookVO) {
        return bookService.update(bookVO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes one Book", description = "Deletes one Book",
    tags = {"Book"},
    responses = {
            @ApiResponse(description = "No Contente", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}