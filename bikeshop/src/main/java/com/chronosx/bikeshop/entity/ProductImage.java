package com.chronosx.bikeshop.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "public_id")
    private String publicId;

    @ManyToOne
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetail productDetail;
}
