package edu.eci.arep.twitter.dto;

import edu.eci.arep.twitter.model.Post;

public class PostDTO {
    private String content;
    private String authorName;

    public PostDTO(Post post) {
        this.content = post.getContent();
        this.authorName = post.getAuthor().getName();
    }

    public String getContent() {
        return content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
