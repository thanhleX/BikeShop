package com.chronosx.bikeshop.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blog_categories")
public class BlogCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "blogCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Blog> blogs;
}
