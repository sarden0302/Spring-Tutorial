package com.kh.khtAcademy.service;

import com.kh.khtAcademy.dto.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;



// 자바에서 사용할 기능의 목록만 작성!!!
public interface UserProfileService {
    // service로 사용할 기능 설정

    // html로 서비스 기능을 통해 나타난 결과를 보여줄 기능들 작성

    // 모든 유저 보기 기능
    List<Map<String, Object>> getAllUsers();

    /* 유저 저장하기 기능
    1번 방법 : 유저의 모든 정보가 오로지 글자일 경우 사용

    void insertUser(User user);
    */
    /* 유저 저장하기 기능
    2번 방법 : 유저의 정보 중 글자 외 이미지 관련 데이터나 글자가 존재할 경우

    void insertUser(String 컬럼명, String 컬럼명, String 컬럼명, MultipartFile 파일);
    */
    // 유저 저장하기 2번 방법
    void insertUser(
            String username,
            String email,
            Date birthdate,
            String accountBalance,
            String gender,
            String hobbies,
            MultipartFile profileImagePath
    );

    String findByUsername(String email);

    String findByEmail(String username, String gender);

    User getUser(int userId);

    User login(String username, String email);

    // 취미가 동일한 유저 검색
    List<User> searchHobby(String hobbies);

    // 일치하는 회원 조회
    // 1이상이 나오면 true 나 false를 넣어주고
    // 0일 경우에만 회원가입 가능할 수 있도록 설정
    boolean checkDuplicatedUsername(String username);

}