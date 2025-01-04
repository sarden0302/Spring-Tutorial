package com.kh.khtAcademy.dto;

import jakarta.persistence.Id;
import lombok.*;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BalanceGameDto {

    @Id
    private int questionNumber;
    private String answerLeft;
    private int scoreS;
    private int scoreC;
    private int scoreH;
    private int scoreD;
    private String problem;
    private String answer;
}