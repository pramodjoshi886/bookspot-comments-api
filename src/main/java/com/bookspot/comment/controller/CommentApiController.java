package com.bookspot.comment.controller;

import com.bookspot.comment.exception.BookNotFoundException;
import com.bookspot.comment.model.Comment;
import com.bookspot.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@EnableSwagger2
@RequestMapping("v1/comment")
public class CommentApiController {

    @Autowired
    private CommentService service;

    @GetMapping()
    public ResponseEntity<?> getAllBooksByUserId(@RequestHeader String bookId) {
        System.out.println("GET comments request : "+bookId);
        try {
            List<Comment> userComments = service.getAllCommentsByBookId(bookId);

            if (userComments != null) {
                return new ResponseEntity<>(userComments, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There are no comments for this book", HttpStatus.OK);
            }
        }
        catch(BookNotFoundException e) {
            System.out.println("************GET all comments error**********"+e.getLocalizedMessage());
            return new ResponseEntity<>("Book Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestHeader String bookId,@RequestBody Comment comment){
        System.out.println("ADD comment request : "+bookId);
        System.out.println(comment.toString());
        try {
            if (service.addComment(comment,bookId) ){
                return new ResponseEntity<>(comment, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Book already exists", HttpStatus.CONFLICT);
            }
        }catch(BookNotFoundException e) {
            System.out.println("************POST comment error**********"+e.getLocalizedMessage());
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping()
    public ResponseEntity<String> deleteComment(@RequestHeader String bookId,@RequestParam int commentId) {
        System.out.println("DELETE comment request for bookID : "+bookId +" ,commentId : "+commentId);
        try {
            if (service.deleteComment(bookId,commentId)) {
                return new ResponseEntity("Delete comment successful for book id : "+bookId,HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Book Not Found", HttpStatus.NOT_FOUND);
            }
        }catch(BookNotFoundException e) {
            System.out.println("************Delete comment by ID error**********"+e.getLocalizedMessage());
            return new ResponseEntity<String>("Error while deleting comment for bookId : "+bookId, HttpStatus.NOT_FOUND);
        }
    }

}
