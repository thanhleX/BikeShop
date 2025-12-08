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
@Table(name = "product_materials")
public class ProductMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "material")
    private String material;

    @OneToMany(mappedBy = "productMaterial")
    private List<ProductDetail> productDetails;
}
