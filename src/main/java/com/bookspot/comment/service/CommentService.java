package com.bookspot.comment.service;

import com.bookspot.comment.dao.CommentRepo;
import com.bookspot.comment.exception.BookNotFoundException;
import com.bookspot.comment.model.BookUserComment;
import com.bookspot.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo repository;

    private List<Comment> comments = new ArrayList<>();
    private BookUserComment userComments = new BookUserComment();

    public List<Comment>getAllCommentsByBookId(String bookId)throws BookNotFoundException {
        if (repository.existsById(bookId)) {
            comments = repository.findById(bookId).get().getComments();
        }
        else {
            throw new BookNotFoundException("Book Not Found!");
        }
        return comments;
    }

    public boolean addComment(Comment comment, String bookId) throws BookNotFoundException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime now = LocalDateTime.now();

            if (repository.existsById(bookId)) {
                BookUserComment userComments =  repository.findById(bookId).get();
                comment.setTimestamp(dtf.format(now));
                userComments.addComment(comment);
                repository.save(userComments);
            }else{
                comment.setTimestamp(dtf.format(now));
                userComments.addComment(comment);
                userComments.setBookId(bookId);
                repository.save(userComments);
            }
            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteComment(String bookId,int commentId) throws BookNotFoundException {
        if (repository.existsById(bookId)) {
            try {
                comments = repository.findById(bookId).get().getComments();
                comments.removeIf(comment -> comment.getCommentId() == commentId);
                userComments.setBookId(bookId);
                userComments.setComments(comments);
                repository.save(userComments);
                return true;
            }catch (Exception e){
                System.out.println("Error : "+e.getMessage());
            }
            return false;
        }
        else {
            throw new BookNotFoundException("Book Doesn't exist");
        }
    }
}
