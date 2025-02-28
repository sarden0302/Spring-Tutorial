package kh.edu.test.FoodCompany.service;


import kh.edu.test.FoodCompany.dto.Drink;
import kh.edu.test.FoodCompany.mapper.DrinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {
    @Autowired
    private DrinkMapper drinkMapper;

    @Override
    public void addDrink(Drink drink) {
        drinkMapper.insertDrink(drink);
    }

    @Override
    public List<Drink> getAllDrinks() {
        return drinkMapper.getAllDrinks();
    }

    @Override
    public Drink getDrinkById(int drinkId) {
        return drinkMapper.getDrinkById(drinkId);
    }

    @Override
    public void updateDrink(Drink drink) {
        drinkMapper.updateDrink(drink);
    }

    @Override
    public void deleteDrink(int drinkId) {
        drinkMapper.deleteDrink(drinkId);
    }
}