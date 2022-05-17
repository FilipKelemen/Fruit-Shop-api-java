package com.FruitShopbackend.FruitShopapi;

import com.FruitShopbackend.FruitShopapi.models.Entities.Color;
import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.FruitShopbackend.FruitShopapi.repo.ColorRepo;
import com.FruitShopbackend.FruitShopapi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@SpringBootApplication
public class FruitShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitShopApiApplication.class, args);
	}

}

//@Component
//class CommandLineRunnerForProductsDummyData implements CommandLineRunner {
//
//	@Autowired
//	private ProductRepo productRepo;
//
//	@Autowired
//	private ColorRepo colorRepo;
//
//	@Override
//	public void run(String... args) throws  Exception {
//
//		List<Color> colorList =  new ArrayList<Color>();
//
//		Color color1 = new Color();
//		Color color2 = new Color();
//		Color color3 = new Color();
//		Color color4 = new Color();
//		color1.setName("red");
//		color2.setName("yellow");
//		color3.setName("green");
//		color4.setName("orange");
//
//		Color color1InDB = colorRepo.save(color1);
//		Color color2InDB = colorRepo.save(color2);
//		Color color3InDB = colorRepo.save(color3);
//		Color color4InDB = colorRepo.save(color4);
//
//		//	UUID productId, @NotEmpty(message = "The name can not be empty or null") String name,
//		//	Set<Color> colors,@NotNull Integer numberInStock,@NotNull String imageUrl,
//		//	@NotNull Integer priceValue, @NotNull String currency,@NotNull String description
//
//		Product apples = new Product();
//
//		apples.setName("apples");
//		Set<Color> appleColors = new HashSet<Color>(){{add(color1InDB);add(color3InDB);}};
//		apples.setColors(appleColors);
//		apples.setNumberInStock(64);
//		apples.setImageUrl("http://localhost:8080/fruits/product/image/apples.jpg");
//		apples.setPriceValue(3300l);
//		apples.setCurrency("$");
//		apples.setDescription("Red big and tasty");
//		productRepo.save(apples);
//
//		Product bananas = new Product();
//
//		bananas.setName("bananas");
//		Set<Color> bananasColors = new HashSet<Color>(){{add(color2InDB);}};
//		bananas.setColors(bananasColors);
//		bananas.setNumberInStock(120);
//		bananas.setImageUrl("http://localhost:8080/fruits/product/image/bananas.jpg");
//		bananas.setPriceValue(15017l);
//		bananas.setCurrency("$");
//		bananas.setDescription("Only top quality bananas are delivered by us");
//		productRepo.save(bananas);
//
//		Product kiwis = new Product();
//
//		kiwis.setName("kiwis");
//		Set<Color> kiwisColors = new HashSet<Color>(){{add(color3InDB);}};
//		kiwis.setColors(kiwisColors);
//		kiwis.setNumberInStock(64);
//		kiwis.setImageUrl("http://localhost:8080/fruits/product/image/kiwis.jpg");
//		kiwis.setPriceValue(4400l);
//		kiwis.setCurrency("$");
//		kiwis.setDescription("Red big and tasty");
//		productRepo.save(kiwis);
//
//		Product mangoes = new Product();
//
//		mangoes.setName("mangoes");
//		Set<Color> mangoesColors = new HashSet<Color>(){{add(color2InDB);add(color4InDB);}};
//		mangoes.setColors(mangoesColors);
//		mangoes.setNumberInStock(115);
//		mangoes.setImageUrl("http://localhost:8080/fruits/product/image/mangoes.jpg");
//		mangoes.setPriceValue(4498l);
//		mangoes.setCurrency("$");
//		mangoes.setDescription("Exotic");
//		productRepo.save(mangoes);
//
//	}
//}

