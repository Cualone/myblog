package com.example.myBlog.posts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Entity
@ToString
public class Post {

    // 번호
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제목
    @Setter
    private String title;

    // 내용
    @Setter
    @Column(columnDefinition = "TEXT")
    private String body;

    //작성자

    // 작성 시간
    private LocalDateTime createdAt = LocalDateTime.now();
    // 수정 시간
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void updatePost(String title, String body) {
        this.title = title;
        this.body = body;

        this.updatedAt = LocalDateTime.now();
    }

    public static Post createPost(String title, String body) {
        Post post = new Post();
        post.title = title;
        post.body = body;

        return post;
    }

}
