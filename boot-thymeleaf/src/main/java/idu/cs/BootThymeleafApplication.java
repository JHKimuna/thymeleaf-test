package idu.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
// spring에서는 기본적으로 h2database 사용, DB를 사용하지 않으려면 pom의 dependency를 제거하고 주석된 어노테이션 사용

public class BootThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootThymeleafApplication.class, args);
	}

}