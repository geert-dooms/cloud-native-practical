package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.dto.ShoppingListResource;
import com.ezgroceries.shoppinglist.repository.ShoppingListRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class ShoppingListServiceBootTests {


    @InjectMocks
    private ShoppingListService shoppingListService;

    @Test
    public void getAllShoppingListsTest() {
        List<ShoppingListResource> testShoppingListResources = new ArrayList<>();

        testShoppingListResources.add(new ShoppingListResource(UUID.randomUUID(),
                                "MisterG",
                                      new HashSet<>(Arrays.asList("Alcohol"))));
        testShoppingListResources.add(new ShoppingListResource(UUID.randomUUID(),
                                "Jos",
                                      new HashSet<>(Arrays.asList("Booze"))));





    }


}
