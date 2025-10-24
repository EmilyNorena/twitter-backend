package edu.eci.arep.twitter.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 140)
    private String body;
    private LocalDateTime date;
    @ManyToOne
    private User author;
}
