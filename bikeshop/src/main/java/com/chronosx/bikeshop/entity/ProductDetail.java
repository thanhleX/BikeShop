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
@Table(name = "product_details")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "handlebar_id")
    private ProductHandlebar productHandlebar;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private ProductColor productColor;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private ProductMaterial productMaterial;

    @OneToMany(mappedBy = "productDetail")
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "productDetail")
    private List<OrderDetail> orderDetails;
}
