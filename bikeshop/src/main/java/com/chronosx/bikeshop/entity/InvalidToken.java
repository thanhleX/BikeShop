package com.chronosx.bikeshop.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InvalidToken {
    @Id
    private String tokenId;

    private Date expiryTime;
}
