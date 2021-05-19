package com.bachelor.warehouse_one;

import com.bachelor.warehouse_one.inventory.Inventory;
import com.bachelor.warehouse_one.inventory.InventoryRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WarehouseOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseOneApplication.class, args);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    /**
     * Allows for http request on localhost
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        return new RestTemplate(factory);
    }

    /**
     * Gets all the product ids from warehouse server
     * @return
     */
    private static String[] getIds(){
        final String url = "http://localhost:8090/getProductIds";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String ids = response.getBody();

        String subStr = ids.substring(1,ids.length()-1);

        String[] listOfIds = subStr.split(",");

        return listOfIds;
    }

    /**
     * Generates inventory list. Sets quantity for every id in the list
     * @param inventoryRepository
     * @return
     */
    @Bean
    CommandLineRunner clr(InventoryRepository inventoryRepository){
         return args -> {
             Faker faker = new Faker();
             String[] mylist = getIds();
             List<Inventory> inventoryList = new ArrayList<>();

             for(String s : mylist){

                 int quantity = faker.random().nextInt(0,12);
                 boolean inStock = true;

                 if(quantity == 0){
                     inStock = false;
                 }

                 Long productId = Long.parseLong(s);


                 inventoryList.add(new Inventory(productId,inStock,quantity));
             }

             inventoryRepository.saveAll(inventoryList);

         };
    }


}
