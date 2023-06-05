package com.FruitShopbackend.FruitShopapi.utility;

import com.FruitShopbackend.FruitShopapi.models.Entities.Category;
import com.FruitShopbackend.FruitShopapi.models.Entities.Color;
import com.FruitShopbackend.FruitShopapi.models.Entities.Product;
import com.FruitShopbackend.FruitShopapi.repo.CartEntryRepo;
import com.FruitShopbackend.FruitShopapi.repo.CategoryRepo;
import com.FruitShopbackend.FruitShopapi.repo.ColorRepo;
import com.FruitShopbackend.FruitShopapi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class DbInit {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ColorRepo colorRepo;

    @Autowired
    private CartEntryRepo cartEntryRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public DbInit() {}

    public void initDb() {
        Color color1 = new Color();color1.setName("red");Color red = colorRepo.save(color1);
        Color color2 = new Color();color2.setName("yellow");Color yellow = colorRepo.save(color2);
        Color color3 = new Color();color3.setName("green");Color green = colorRepo.save(color3);
        Color color4 = new Color();color4.setName("orange");Color orange = colorRepo.save(color4);
        Color color5 = new Color();color5.setName("black");Color black = colorRepo.save(color5);
        Color color6 = new Color();color6.setName("blue");Color blue = colorRepo.save(color6);
        Color color7 = new Color();color7.setName("pink");Color pink = colorRepo.save(color7);
        Color color8 = new Color();color8.setName("purple");Color purple = colorRepo.save(color8);
        Color color9 = new Color();color9.setName("white");Color white = colorRepo.save(color9);
        Color color10 = new Color();color10.setName("brown");Color brown = colorRepo.save(color10);


        Category fruitCategory = categoryRepo.save(new Category("Fruit","fruit description"));
        Category vegetablesCategory = categoryRepo.save(new Category("Vegetables","vegetables description"));
        Category mushroomsCategory = categoryRepo.save(new Category("Mushrooms","Mushrooms description"));
        Category spicesCategory = categoryRepo.save(new Category("Spices","Spices description"));
        Category preparedProduceCategory = categoryRepo.save(new Category("Prepared Produce","prepared produce description"));
        Category mixesCategory = categoryRepo.save(new Category("Mixes","mixes description"));
        Category berriesCategory = categoryRepo.save(new Category("Berries","berries description"));
        Category trueBerriesCategory = categoryRepo.save(new Category("True Berries","true berries description"));
        Category applesAndPearsCategory = categoryRepo.save(new Category("Apples And Pears","apples and pears description"));
        Category tropicalAndExoticCategory = categoryRepo.save(new Category("Tropical and Exotic","tropical description"));
        Category citrusCategory = categoryRepo.save(new Category("Citrus","Citrus description"));
        Category stoneFruitCategory = categoryRepo.save(new Category("Stone Fruits","Stone fruits description"));
        Category melonsCategory = categoryRepo.save(new Category("Melons","Melons description"));
        Category tomatoesAndAvocadoCategory = categoryRepo.save(new Category("Tomatoes and Avocado","Tomatoes and avocado description"));

        {
            Product berriesMixture = new Product();
            berriesMixture.setName("berries mixture");
            berriesMixture.setColors(Set.of(red, black, blue));
            berriesMixture.setNumberInStock(64);
            berriesMixture.setImageUrl("http://localhost:8080/fruits/product/image/fruit/berries-mixture.webp");
            berriesMixture.setPriceValue(3300l);
            berriesMixture.setCurrency("$");
            berriesMixture.setDescription("Berries are small, sweet fruits that are typically juicy and flavorful. They are usually colorful, with red, blue, or purple skins, and are often used in desserts, jams, and juices. Some common types of berries include strawberries, raspberries, blackberries, and blueberries. Berries are a good source of fiber and vitamins C and K, and are often considered a \"superfood\" due to their high levels of antioxidants. Berries can be eaten fresh, frozen, or dried, and are often used in baking and cooking.");
            berriesMixture.setCategories(Set.of(mixesCategory, berriesCategory, fruitCategory));
            productRepo.save(berriesMixture);
        }
        {
            Product strawberries = new Product();
            strawberries.setName("strawberries");
            strawberries.setColors(Set.of(red, pink));
            strawberries.setNumberInStock(120);
            strawberries.setImageUrl("http://localhost:8080/fruits/product/image/fruit/strawberries.webp");
            strawberries.setPriceValue(15017l);
            strawberries.setCurrency("$");
            strawberries.setDescription("Strawberries are small, red fruits that are known for their sweet and juicy flavor. They have a characteristic aroma and are often used in desserts, jams, and juices. Strawberries are native to Europe, but are now grown in many parts of the world, including North and South America, Asia, and Australia. They are typically harvested in the spring and summer months and are available year-round in most supermarkets. Strawberries are a good source of vitamins C and K, as well as antioxidants and other beneficial plant compounds. They can be eaten fresh, frozen, or dried, and are often used in baking and cooking.");
            strawberries.setCategories(Set.of(berriesCategory, fruitCategory));
            productRepo.save(strawberries);
        }
        {
            Product kiwis = new Product();
            kiwis.setName("kiwis");
            kiwis.setColors(Set.of(green));
            kiwis.setNumberInStock(64);
            kiwis.setImageUrl("http://localhost:8080/fruits/product/image/fruit/kiwis.webp");
            kiwis.setPriceValue(4400l);
            kiwis.setCurrency("$");
            kiwis.setDescription("Kiwi fruit, also known as Chinese gooseberries, are small, oval-shaped fruits with a brown, fuzzy exterior. Inside, the fruit has a bright green flesh with tiny black seeds. Kiwi fruit are known for their sweet and tangy flavor, and are often used in salads, desserts, and smoothies. They are rich in vitamins C and E, as well as potassium and folate. Kiwi fruit are native to China, but are now grown in many other parts of the world, including New Zealand, Italy, and California. They are typically harvested in the fall and are available year-round in most supermarkets");
            kiwis.setCategories(Set.of(berriesCategory, fruitCategory));
            productRepo.save(kiwis);
        }
        {
            Product mangoes = new Product();
            mangoes.setName("mangoes");
            mangoes.setColors(Set.of(yellow, orange));
            mangoes.setNumberInStock(115);
            mangoes.setImageUrl("http://localhost:8080/fruits/product/image/fruit/mangoes.webp");
            mangoes.setPriceValue(4498l);
            mangoes.setCurrency("$");
            mangoes.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            mangoes.setCategories(Set.of(tropicalAndExoticCategory, fruitCategory));
            productRepo.save(mangoes);
        }
        {
            Product apricots = new Product();
            apricots.setName("apricots");
            apricots.setColors(Set.of(yellow, orange, red));
            apricots.setNumberInStock(12000);
            apricots.setImageUrl("http://localhost:8080/fruits/product/image/fruit/apricots.webp");
            apricots.setPriceValue(438l);
            apricots.setCurrency("$");
            apricots.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            apricots.setCategories(Set.of(stoneFruitCategory, fruitCategory));
            productRepo.save(apricots);
        }
        {
            Product avocados = new Product();
            avocados.setName("avocados");
            avocados.setColors(Set.of(green, brown));
            avocados.setNumberInStock(12000);
            avocados.setImageUrl("http://localhost:8080/fruits/product/image/fruit/avocados.webp");
            avocados.setPriceValue(438002l);
            avocados.setCurrency("$");
            avocados.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            avocados.setCategories(Set.of(tomatoesAndAvocadoCategory, fruitCategory));
            productRepo.save(avocados);
        }
        {
            Product bananas = new Product();
            bananas.setName("bananas");
            bananas.setColors(Set.of(yellow));
            bananas.setNumberInStock(48982);
            bananas.setImageUrl("http://localhost:8080/fruits/product/image/fruit/bananas.webp");
            bananas.setPriceValue(89422l);
            bananas.setCurrency("$");
            bananas.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            bananas.setCategories(Set.of(tropicalAndExoticCategory, fruitCategory));
            productRepo.save(bananas);
        }
        {
            Product blackberries = new Product();
            blackberries.setName("blackberries");
            blackberries.setColors(Set.of(blue, black));
            blackberries.setNumberInStock(12111);
            blackberries.setImageUrl("http://localhost:8080/fruits/product/image/fruit/blackberries.webp");
            blackberries.setPriceValue(90099l);
            blackberries.setCurrency("$");
            blackberries.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            blackberries.setCategories(Set.of(berriesCategory, fruitCategory));
            productRepo.save(blackberries);
        }
        {
            Product clementines = new Product();
            clementines.setName("clementines");
            clementines.setColors(Set.of(yellow, orange));
            clementines.setNumberInStock(12000);
            clementines.setImageUrl("http://localhost:8080/fruits/product/image/fruit/blackberries.webp");
            clementines.setPriceValue(438l);
            clementines.setCurrency("$");
            clementines.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            clementines.setCategories(Set.of(citrusCategory, fruitCategory));
            productRepo.save(clementines);
        }
        {
            Product dragonFruits = new Product();
            dragonFruits.setName("dragon fruits");
            dragonFruits.setColors(Set.of(green,pink,white,black));
            dragonFruits.setNumberInStock(120);
            dragonFruits.setImageUrl("http://localhost:8080/fruits/product/image/fruit/dragon-fruits.webp");
            dragonFruits.setPriceValue(43908l);
            dragonFruits.setCurrency("$");
            dragonFruits.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            dragonFruits.setCategories(Set.of(tropicalAndExoticCategory, fruitCategory));
            productRepo.save(dragonFruits);
        }
        {
            Product galaApples = new Product();
            galaApples.setName("gala apples");
            galaApples.setColors(Set.of(yellow, red));
            galaApples.setNumberInStock(10);
            galaApples.setImageUrl("http://localhost:8080/fruits/product/image/fruit/gala-apples.webp");
            galaApples.setPriceValue(9938l);
            galaApples.setCurrency("$");
            galaApples.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            galaApples.setCategories(Set.of(applesAndPearsCategory, fruitCategory));
            productRepo.save(galaApples);
        }
        {
            Product grannySmithApples = new Product();
            grannySmithApples.setName("granny smith apples");
            grannySmithApples.setColors(Set.of(yellow, red));
            grannySmithApples.setNumberInStock(222);
            grannySmithApples.setImageUrl("http://localhost:8080/fruits/product/image/fruit/granny-smith-apples.webp");
            grannySmithApples.setPriceValue(22290l);
            grannySmithApples.setCurrency("$");
            grannySmithApples.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            grannySmithApples.setCategories(Set.of(applesAndPearsCategory, fruitCategory));
            productRepo.save(grannySmithApples);
        }
        {
            Product grapeFruits = new Product();
            grapeFruits.setName("grape fruits");
            grapeFruits.setColors(Set.of(red, orange, pink));
            grapeFruits.setNumberInStock(122);
            grapeFruits.setImageUrl("http://localhost:8080/fruits/product/image/fruit/grape-fruits.webp");
            grapeFruits.setPriceValue(1038l);
            grapeFruits.setCurrency("$");
            grapeFruits.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            grapeFruits.setCategories(Set.of(citrusCategory, fruitCategory));
            productRepo.save(grapeFruits);
        }
        {
            Product grapes = new Product();
            grapes.setName("grapes");
            grapes.setColors(Set.of(purple, pink, green));
            grapes.setNumberInStock(12000);
            grapes.setImageUrl("http://localhost:8080/fruits/product/image/fruit/grapes.webp");
            grapes.setPriceValue(1616l);
            grapes.setCurrency("$");
            grapes.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            grapes.setCategories(Set.of(trueBerriesCategory, berriesCategory, fruitCategory));
            productRepo.save(grapes);
        }
        {
            Product lemons = new Product();
            lemons.setName("lemons");
            lemons.setColors(Set.of(yellow));
            lemons.setNumberInStock(333);
            lemons.setImageUrl("http://localhost:8080/fruits/product/image/fruit/lemons.webp");
            lemons.setPriceValue(33333l);
            lemons.setCurrency("$");
            lemons.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            lemons.setCategories(Set.of(citrusCategory, fruitCategory));
            productRepo.save(lemons);
        }
        {
            Product oranges = new Product();
            oranges.setName("oranges");
            oranges.setColors(Set.of(yellow, orange));
            oranges.setNumberInStock(5454);
            oranges.setImageUrl("http://localhost:8080/fruits/product/image/fruit/oranges.webp");
            oranges.setPriceValue(5454l);
            oranges.setCurrency("$");
            oranges.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            oranges.setCategories(Set.of(citrusCategory, fruitCategory));
            productRepo.save(oranges);
        }
        {
            Product papayas = new Product();
            papayas.setName("papayas");
            papayas.setColors(Set.of(yellow, orange,black,green));
            papayas.setNumberInStock(56);
            papayas.setImageUrl("http://localhost:8080/fruits/product/image/fruit/papayas.webp");
            papayas.setPriceValue(438l);
            papayas.setCurrency("$");
            papayas.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            papayas.setCategories(Set.of(tropicalAndExoticCategory, fruitCategory));
            productRepo.save(papayas);
        }
        {
            Product passionFruits = new Product();
            passionFruits.setName("passion fruits");
            passionFruits.setColors(Set.of(yellow, orange, purple, black));
            passionFruits.setNumberInStock(767);
            passionFruits.setImageUrl("http://localhost:8080/fruits/product/image/fruit/passion-fruits.webp");
            passionFruits.setPriceValue(7878l);
            passionFruits.setCurrency("$");
            passionFruits.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            passionFruits.setCategories(Set.of(tropicalAndExoticCategory, fruitCategory));
            productRepo.save(passionFruits);
        }
        {
            Product peaches = new Product();
            peaches.setName("peaches");
            peaches.setColors(Set.of(yellow, orange));
            peaches.setNumberInStock(89);
            peaches.setImageUrl("http://localhost:8080/fruits/product/image/fruit/peaches.webp");
            peaches.setPriceValue(438l);
            peaches.setCurrency("$");
            peaches.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            peaches.setCategories(Set.of(stoneFruitCategory, fruitCategory));
            productRepo.save(peaches);
        }
        {
            Product pears = new Product();
            pears.setName("pears");
            pears.setColors(Set.of(yellow,green));
            pears.setNumberInStock(10);
            pears.setImageUrl("http://localhost:8080/fruits/product/image/fruit/pears.webp");
            pears.setPriceValue(438l);
            pears.setCurrency("$");
            pears.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            pears.setCategories(Set.of(applesAndPearsCategory, fruitCategory));
            productRepo.save(pears);
        }
        {
            Product pineApples = new Product();
            pineApples.setName("pineapples");
            pineApples.setColors(Set.of(yellow,brown,green));
            pineApples.setNumberInStock(876);
            pineApples.setImageUrl("http://localhost:8080/fruits/product/image/fruit/pineapples.webp");
            pineApples.setPriceValue(865l);
            pineApples.setCurrency("$");
            pineApples.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            pineApples.setCategories(Set.of(tropicalAndExoticCategory, fruitCategory));
            productRepo.save(pineApples);
        }
        {
            Product raspberries = new Product();
            raspberries.setName("raspberries");
            raspberries.setColors(Set.of(red, pink));
            raspberries.setNumberInStock(657);
            raspberries.setImageUrl("http://localhost:8080/fruits/product/image/fruit/raspberries.webp");
            raspberries.setPriceValue(8545l);
            raspberries.setCurrency("$");
            raspberries.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            raspberries.setCategories(Set.of(berriesCategory, fruitCategory));
            productRepo.save(raspberries);
        }
        {
            Product sourcherries = new Product();
            sourcherries.setName("sour cherries");
            sourcherries.setColors(Set.of(red, black));
            sourcherries.setNumberInStock(334);
            sourcherries.setImageUrl("http://localhost:8080/fruits/product/image/fruit/sour-cherries.webp");
            sourcherries.setPriceValue(33452l);
            sourcherries.setCurrency("$");
            sourcherries.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            sourcherries.setCategories(Set.of(stoneFruitCategory, fruitCategory));
            productRepo.save(sourcherries);
        }
        {
            Product watermelons = new Product();
            watermelons.setName("watermelons");
            watermelons.setColors(Set.of(red, green, black));
            watermelons.setNumberInStock(6565);
            watermelons.setImageUrl("http://localhost:8080/fruits/product/image/fruit/watermelons.webp");
            watermelons.setPriceValue(7878l);
            watermelons.setCurrency("$");
            watermelons.setDescription("Mangoes are a type of tropical fruit that are known for their juicy, sweet flavor and vibrant yellow or orange color. They have a large, oblong shape and a smooth, leathery skin that covers a large, flat pit. Mangoes are native to South Asia, but are now grown in many warm climates around the world. They are typically harvested in the summer and are available year-round in most supermarkets. Mangoes are a good source of vitamins C and A, as well as potassium and other nutrients. They can be eaten fresh, frozen, or dried, and are often used in cooking, baking, and smoothies.");
            watermelons.setCategories(Set.of(melonsCategory, fruitCategory));
            productRepo.save(watermelons);
        }
    }

    public void emptyProductTables() {
        cartEntryRepo.deleteAll();
        colorRepo.deleteAll();
        categoryRepo.deleteAll();
        productRepo.deleteAll();
    }
}
