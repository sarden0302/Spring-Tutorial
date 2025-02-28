package kh.edu.test.FoodCompany.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Drink{
    private int drinkId;
    private String drinkName;
    private String drinkBrand;
    private int drinkVolumeMl;
    private double drinkPrice;
    private int drinkStock;
    private String drinkCategory;
    private boolean drinkSugarFree;
    private boolean drinkCaffeine;
    private Date drinkExpirationDate;
}