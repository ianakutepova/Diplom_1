package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@org.junit.runner.RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient hotSauce;
    @Mock
    private Ingredient sourCream;
    @Mock
    private Ingredient chiliSauce;
    @Mock
    private Ingredient cutlet;
    @Mock
    private Ingredient dinosaur;


    @Before
    public void setUp() {
        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        hotSauce = Mockito.mock(Ingredient.class);
        sourCream = Mockito.mock(Ingredient.class);
        chiliSauce = Mockito.mock(Ingredient.class);
        cutlet = Mockito.mock(Ingredient.class);
        dinosaur = Mockito.mock(Ingredient.class);
    }

    @Test
    public void testSetBuns() {
        List<Bun> availableBuns = new ArrayList<>();
        availableBuns.add(new Bun("Test Bun", 100.0f));

        Bun chosenBun = availableBuns.get(0);
        burger.setBuns(chosenBun);

        assertEquals(chosenBun, burger.bun);
    }



    @Test
    public void testRemoveIngredient_Size() {
        burger.addIngredient(sourCream);
        burger.addIngredient(chiliSauce);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }


    @Test
    public void testRemoveIngredient_Content() {
        burger.addIngredient(sourCream); burger.addIngredient(chiliSauce);
        burger.removeIngredient(0);
        assertEquals(chiliSauce, burger.ingredients.get(0)); }


    @Test
    public void testMoveIngredient_Size() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);

        burger.moveIngredient(1, 0);

        assertEquals(2, burger.ingredients.size());
    }


    @Test
    public void testMoveIngredient_FirstPosition() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);

        burger.moveIngredient(1, 0);

        assertEquals(dinosaur, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient_SecondPosition() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);

        burger.moveIngredient(1, 0);

        assertEquals(cutlet, burger.ingredients.get(1));
    }



    @Test
    public void testGetPrice() {
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        burger.setBuns(bun);

        Mockito.when(hotSauce.getPrice()).thenReturn(100.0f);

        Mockito.when(cutlet.getPrice()).thenReturn(200.0f);

        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        float expectedPrice = bun.getPrice() * 2 + hotSauce.getPrice() + cutlet.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }


    @Test
    public void testGetReceipt_BunName() {
        Mockito.when(bun.getName()).thenReturn("Test Bun");
        burger.setBuns(bun);

        String expectedReceipt = String.format("(==== %s ====)%n", bun.getName());
        assertTrue(burger.getReceipt().contains(expectedReceipt));
    }

    @Test
    public void testGetReceipt_Ingredients() {
        burger.setBuns(bun);

        Mockito.when(hotSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(hotSauce.getName()).thenReturn("hot sauce");
        Mockito.when(hotSauce.getPrice()).thenReturn(100.0f);

        Mockito.when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(cutlet.getName()).thenReturn("cutlet");
        Mockito.when(cutlet.getPrice()).thenReturn(200.0f);

        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        String expectedIngredients = String.format("= %s %s =%n", hotSauce.getType().toString().toLowerCase(), hotSauce.getName());
        expectedIngredients += String.format("= %s %s =%n", cutlet.getType().toString().toLowerCase(), cutlet.getName());

        assertTrue(burger.getReceipt().contains(expectedIngredients));
    }

    @Test
    public void testGetReceipt_Price() {
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        burger.setBuns(bun);

        Mockito.when(hotSauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(hotSauce.getName()).thenReturn("hot sauce");
        Mockito.when(hotSauce.getPrice()).thenReturn(100.0f);

        Mockito.when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(cutlet.getName()).thenReturn("cutlet");
        Mockito.when(cutlet.getPrice()).thenReturn(200.0f);

        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        float expectedPrice = bun.getPrice() * 2 + hotSauce.getPrice() + cutlet.getPrice();
        String expectedPriceString = String.format("%nPrice: %f%n", expectedPrice);
        assertTrue(burger.getReceipt().contains(expectedPriceString));
    }
}