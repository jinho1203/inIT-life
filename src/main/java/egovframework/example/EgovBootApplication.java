package egovframework.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("egovframework.example.mapper")
public class EgovBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgovBootApplication.class, args);
	}

}
