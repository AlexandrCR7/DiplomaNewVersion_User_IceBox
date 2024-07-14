package ru.gb.userIceBoxCheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //включаем OpenFeign для связи с другим микросервисом
public class UserIceBoxCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserIceBoxCheckApplication.class, args);
	}

}
