package kh.edu.test.FoodCompany.mapper;

import kh.edu.test.FoodCompany.dto.Drink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DrinkMapper {
    void insertDrink(Drink drink);
    List<Drink> getAllDrinks();
    Drink getDrinkById(int drinkId);
    void updateDrink(Drink drink);
    void deleteDrink(int drinkId);
}