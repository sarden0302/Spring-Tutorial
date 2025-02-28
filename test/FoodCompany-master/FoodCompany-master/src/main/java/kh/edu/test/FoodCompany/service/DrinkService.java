package kh.edu.test.FoodCompany.service;


import kh.edu.test.FoodCompany.dto.Drink;

import java.util.List;

public interface DrinkService {
    void addDrink(Drink drink);
    List<Drink> getAllDrinks();
    Drink getDrinkById(int drinkId);
    void updateDrink(Drink drink);
    void deleteDrink(int drinkId);
}