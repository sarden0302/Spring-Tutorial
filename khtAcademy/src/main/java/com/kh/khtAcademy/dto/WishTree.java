package com.kh.khtAcademy.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishTree {

    @Id
    private String userId;
    private String userWish;
    private String wishDate;
}
