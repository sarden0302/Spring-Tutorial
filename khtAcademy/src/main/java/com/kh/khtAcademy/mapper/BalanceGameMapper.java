package com.kh.khtAcademy.mapper;

import com.kh.khtAcademy.dto.BalanceGameDto;
import com.kh.khtAcademy.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BalanceGameMapper {
    List<BalanceGameDto> selectAllQuestions();
    void insertUser(User user);

}