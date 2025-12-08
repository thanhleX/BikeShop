package com.chronosx.bikeshop.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_color")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;

    @OneToMany(mappedBy = "productColor")
    private List<ProductDetail> productDetails;
}
