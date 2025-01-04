package com.kh.khtAcademy.dto;
// javax -> jakarta
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 숫자자동 증가
    private int userId;
    private String username;
    private String email;
    /*
       private String birthdate;
       birthdate SQL에서는 DATE 타입
                 Java에서는 String 타입
                 만약 SQL에서 DATE 타입인 자료형을 JAVA에서 String 타입으로 사용하려면
                 mybatis-config.xml 에서 SQL DATE 타입을 String으로 받아서 가져오겠다는 형식 변환 표기

       private Date birthdate;
       birthdate SQL에서는 DATE 타입
                 Java에서는 DATE 타입을 사용한다면
                 import java.util.Date를 가져와서 사용할 것!
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String accountBalance;
    private String gender;
    private String hobbies;
    private String profileImagePath;

}
