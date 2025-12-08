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
@Table(name = "product_handlebars")
public class ProductHandlebar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "style")
    private String style;

    @OneToMany(mappedBy = "productHandlebar")
    private List<ProductDetail> productDetails;
}
