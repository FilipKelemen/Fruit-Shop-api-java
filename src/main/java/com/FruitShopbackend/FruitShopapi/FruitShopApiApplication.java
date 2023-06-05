package com.FruitShopbackend.FruitShopapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FruitShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitShopApiApplication.class, args);
	}

}

//	@Component
//	class CommandLineRunnerForProductsDummyData implements CommandLineRunner {
//		final DbInit dbInit;
//
//		public CommandLineRunnerForProductsDummyData(@Autowired DbInit dbInit) {
//			this.dbInit = dbInit;
//		}
//
//		@Override
//		public void run(String... args) {
//			dbInit.initDb();
//		}
//	}

