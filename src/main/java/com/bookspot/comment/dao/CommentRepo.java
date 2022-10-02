package com.bookspot.comment.dao;

import com.bookspot.comment.model.BookUserComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo extends MongoRepository<BookUserComment,String> {
}
