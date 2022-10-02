package com.bookspot.comment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "BookComments")
public class BookUserComment {

    @Id
    private String bookId;
    private List<Comment> comments;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if(this.comments ==null) {
            this.comments = new ArrayList<Comment>();
        }
        comments.add(comment);
    }
}
