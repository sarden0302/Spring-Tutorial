package kh.edu.test.FoodCompany.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Snack {
    private int snackId;
    private String snackName;
    private String snackBrand;
    private int snackWeightG;
    private double snackPrice;
    private int snackStock;
    private String snackCategory;
    private boolean snackSpicy;
    private String snackAllergyInfo;
    private Date snackExpirationDate;
}