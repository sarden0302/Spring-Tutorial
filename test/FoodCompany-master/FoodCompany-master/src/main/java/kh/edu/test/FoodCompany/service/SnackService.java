package kh.edu.test.FoodCompany.service;

import kh.edu.test.FoodCompany.dto.Snack;

import java.util.List;

public interface SnackService {
    void addSnack(Snack snack);
    List<Snack> getAllSnacks();
    Snack getSnackById(int snackId);
    void updateSnack(Snack snack);
    void deleteSnack(int snackId);
}