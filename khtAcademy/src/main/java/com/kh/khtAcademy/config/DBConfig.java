package com.kh.khtAcademy.config;


/* @Configuration
 * - 설정용 클래스임 명시
 *   + 객체로 생성해서 내부 코드를 서버 실행시 모두 수행
 *
 *
 * @PropertySource("경로")
 * - 지정된 경로의 properties 파일 내용을 읽어와 사용
 * - 사용할 properties 파일이 다수일 경우
 *   해당 어노테이션을 연속해서 작성하면됨
 *
 * @Bean
 * - 개발자가 수동으로 생성한 객체의 관리를
 *   스프링에게 넘기는 어노테이션
 *   (Bean 등록)
 *
 * @ConfigurationProperties(prefix = "spring.datasource.hikari")
 * - @PropertySource 로 읽어온 properties 파일의 내용 중
 *   접두사(앞부분, prefix)이 일치하는 값만 읽어옴
 *
 * - 읽어온 내용을 생성하려는 Bean에 자동으로 세팅
 *
 *
 * DataSource : Connection 생성 + Connection Pool 지원 하는 객체를
 * 				참조하기 위한 Java 인터페이스
 * 				(DriverManager 대안, Java JNDI 기술 이용)
 *
 * @Autowired
 * - 등록된 Bean 중에서
 *   타입이 일치하거나, 상속 관계에 있는 Bean을
 *   지정된 필드에 주입
 *    == 의존성 주입(DI, Dependency Injection, IOC 관련 기술)
 */


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
// PropertySource : config.properties -> MySQL 아이디 비밀번호 데이터베이스 저장소 명칭
// 따로 저장하는 이유 : 데이터 정보를 유저에게 보내지 않고 따로 저장
@Configuration // 설정
@PropertySource("classpath:/config.properties") //
public class DBConfig {

    // 필드

    // import org.springframework.context.ApplicationContext;

    @Autowired // (DI, 의존성 주입)
    private ApplicationContext applicationContext; // 현재 프로젝트



    /////////// HikariCP 설정 /////////////

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {

        // HikariConfig 설정 객체 생성
        // -> config.properties 파일에서 읽어온
        //    spring.datasource.hikari로 시작하는 모든 값이
        //    알맞은 필드에 세팅된 상태
        return new HikariConfig();
    }


    @Bean
    public DataSource dataSource(HikariConfig config) {
        // 매개 변수 HikariConfig config
        // -> 등록된 Bean 중 HikariConfig 타입의 Bean이 자동으로 주입

        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }


    ///////////  Mybatis 설정 /////////////
    // sessionFactoryBean : 데이터를 임시로 저장하겠다.
    @Bean
    public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception{

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();


        sessionFactoryBean.setDataSource(dataSource);


        // mapper.xml(SQL) 파일이 모이는 경로 지정
        // -> Mybatis 코드 수행 시 mapper.xml을 읽을 수 있음
        // sessionFactoryBean.setMapperLocations("현재프로젝트.자원.어떤파일");

        sessionFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath:/mappers/**.xml")  );
        // session
        // mappers/**.xml -> templates 폴더 밑에 mappers 폴더 내에 존재하는 모든 sql문 바라보기
        // **.xml 파일명에 관계없이 확장명이 xml 이면 xml 로 마지막에 작성된 모든 파일 바라보기 설정


        // 해당 패키지 내 모든 클래스의 별칭을 등록
        // - Mybatis는 특정 클래스 지정 시 패키지명.클래스명을 모두 작성해야함
        //  -> 긴 이름을 짧게 부를 별칭 설정할 수 있음

        // - setTypeAliasesPackage("패키지") 이용 시
        //   클래스 파일명이 별칭으로 등록

        // ex) (원본) edu.kh.todo.model.dto.Todo   -->  Todo (별칭 등록)
        sessionFactoryBean.setTypeAliasesPackage("com.kh.khtAcademy");


        // 마이바티스 설정 파일 경로 지정
        // MySQL 컬럼명 = user_id             JAVA 변수명 = userId 로 설정시 자동 적용
        // classpath = src/main/resources 의 줄임말 표기
        sessionFactoryBean.setConfigLocation(
                applicationContext.getResource("classpath:mybatis-config.xml"));

        // 설정 내용이 모두 적용된 객체 반환
        return sessionFactoryBean.getObject();
    }

    // 자동 commit
    // DBCP(DataBase Connection Pool)
    // SqlSessionTemplate : Connection + DBCP + Mybatis + 트랜잭션 제어 처리
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }


    // DataSourceTransactionManager : 트랜잭션 매니저(제어 처리)
    @Bean
    public DataSourceTransactionManager
    dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}