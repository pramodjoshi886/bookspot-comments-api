package com.bookspot.comment.model;

import java.sql.Time;

public class Comment {

    private String userName;
    private String userComment;
    private int commentId;
    private String timestamp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userName='" + userName + '\'' +
                ", userComment='" + userComment + '\'' +
                ", commentId=" + commentId +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
