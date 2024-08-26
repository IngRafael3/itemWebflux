package com.example.webfluxdemo;

import com.example.webfluxdemo.models.Item;
import com.example.webfluxdemo.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebfluxdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ItemRepository itemRepository){
		return args -> {
			Item item = new Item("computador");
			itemRepository.save(item).subscribe();
		};
	}

}
