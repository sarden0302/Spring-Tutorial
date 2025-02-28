package kh.edu.test.FoodCompany.mapper;

import kh.edu.test.FoodCompany.dto.Snack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SnackMapper {
    void insertSnack(Snack snack);
    List<Snack> getAllSnacks();
    Snack getSnackById(int snackId);
    void updateSnack(Snack snack);
    void deleteSnack(int snackId);
}