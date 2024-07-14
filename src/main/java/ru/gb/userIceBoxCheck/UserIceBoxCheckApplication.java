package ru.gb.userIceBoxCheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //включаем фиджн клиента
public class UserIceBoxCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserIceBoxCheckApplication.class, args);
	}

}
