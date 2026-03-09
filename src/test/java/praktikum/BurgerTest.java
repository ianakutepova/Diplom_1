package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    @Mock
    private Bun bun;


    @Before
    public void setUp() {
        burger = new Burger();
        bun = Mockito.mock(Bun.class); // ручная инициализация мока
    }

    @Test
    public void testSetBuns() {
        Database database = new Database();
        List<Bun> availableBuns = database.availableBuns();

        Bun chosenBun = availableBuns.get(0);

        burger.setBuns(chosenBun);

        assertEquals(chosenBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        Database database = new Database();
        List<Ingredient> availableIngredients = database.availableIngredients();

        Ingredient ingredient1 = availableIngredients.get(1); // sour cream
        Ingredient ingredient2 = availableIngredients.get(2); // chili sauce
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        Database database = new Database();
        List<Ingredient> availableIngredients = database.availableIngredients();

        Ingredient ingredient1 = availableIngredients.get(3); // cutlet
        Ingredient ingredient2 = availableIngredients.get(4); // dinosaur
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(1, 0);

        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        Mockito.when(bun.getPrice()).thenReturn(10.0f);
        burger.setBuns(bun);

        Database database = new Database();
        List<Ingredient> availableIngredients = database.availableIngredients();

        Ingredient ingredient1 = availableIngredients.get(0); // hot sauce
        Ingredient ingredient2 = availableIngredients.get(3); // cutlet
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceipt() {

        Mockito.when(bun.getName()).thenReturn("Test Bun");
        Mockito.when(bun.getPrice()).thenReturn(10.0f);

        burger.setBuns(bun);


        Database database = new Database();
        List<Ingredient> availableIngredients = database.availableIngredients();

        Ingredient ingredient1 = availableIngredients.get(0); // hot sauce
        Ingredient ingredient2 = availableIngredients.get(3); // cutlet

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expectedReceipt = String.format("(==== %s ====)%n", bun.getName());
        expectedReceipt += String.format("= %s %s =%n", ingredient1.getType().toString().toLowerCase(), ingredient1.getName());
        expectedReceipt += String.format("= %s %s =%n", ingredient2.getType().toString().toLowerCase(), ingredient2.getName());
        expectedReceipt += String.format("(==== %s ====)%n", bun.getName());
        expectedReceipt += String.format("%nPrice: %f%n", burger.getPrice());

        assertEquals(expectedReceipt, burger.getReceipt());
    }

}
