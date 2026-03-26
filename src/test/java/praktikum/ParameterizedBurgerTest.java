package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedBurgerTest {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;

    private String bunName;
    private float bunPrice;


    public ParameterizedBurgerTest(String bunName, float bunPrice, Ingredient ingredient) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredient = ingredient;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100, new Ingredient(IngredientType.SAUCE, "hot sauce", 100)},
                {"white bun", 200, new Ingredient(IngredientType.FILLING, "cutlet", 100)},
                {"red bun", 300, new Ingredient(IngredientType.SAUCE, "sour cream", 200)},
                {"black bun", 100, new Ingredient(IngredientType.FILLING, "dinosaur", 200)},
                {"white bun", 200, new Ingredient(IngredientType.SAUCE, "chili sauce", 300)},
                {"red bun", 300, new Ingredient(IngredientType.FILLING, "sausage", 300)}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        ingredient = Mockito.mock(Ingredient.class); // Создаем мок для ingredient
    }

    @Test
    public void testAddIngredientSize() {
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(100.0f);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientContent() {
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(100.0f);

        burger.addIngredient(ingredient);

        assertEquals(ingredient, burger.ingredients.get(0));
    }
}