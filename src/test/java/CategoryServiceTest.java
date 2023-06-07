import domain.*;
import exception.*;
import org.junit.jupiter.api.*;
import service.*;

import java.util.*;

/*
  - Root Category (According to desc, root category exists) {"default keyword"}
    - Furniture {...}
    - Electronics {...}
    - Home Appliance {...}
      - Major Appliance
        - Kitchen Appliance
        - General Appliance
      - Minor Appliance
      - Lawn and Garden {"Lawn", "Garden", "GardenindTools"}
  */

public class CategoryServiceTest {

  private CategoryService categoryService;

  @BeforeEach
  void setUp() {
    List<Category> categories = new ArrayList<>();

    //Root Category
    List<String> rootKeywords = new ArrayList<>();
    rootKeywords.add("default");
    Category rootCategory = new Category(null, "ROOT", rootKeywords);

    //Home appliance
    List<String> homeApplianceKeywords = new ArrayList<>();
    homeApplianceKeywords.add("homeApplianceKeyword");
    Category homeApplianceCategory = new Category(rootCategory, "Home Appliance", homeApplianceKeywords);

    //Major Appliance
    Category majorAppliance = new Category(homeApplianceCategory, "Major Appliance", new ArrayList<>());

    //Kitechen Appliance
    List<String> kitchenApplianceKeywords = new ArrayList<>();
    kitchenApplianceKeywords.add("kitchenApplianceKeyword1");
    kitchenApplianceKeywords.add("kitchenApplianceKeyword2");
    Category kitchenAppliance = new Category(majorAppliance, "Kitchen Appliance", kitchenApplianceKeywords);

    //General Appliance
    List<String> generalApplianceKeywords = new ArrayList<>();
    generalApplianceKeywords.add("generalApplianceKeyword1");
    generalApplianceKeywords.add("generalApplianceKeyword2");
    Category generalAppliance = new Category(majorAppliance, "General Appliance", generalApplianceKeywords);

    categories.add(rootCategory);
    categories.add(homeApplianceCategory);
    categories.add(majorAppliance);
    categories.add(kitchenAppliance);
    categories.add(generalAppliance);

    categoryService = new CategoryService(categories);
  }

  @Test
  public void testGetKeywordsFromCategory() {

    List<String> result = categoryService.getKeywordsForCategory("General Appliance");
    List<String> expectedKeywords = new ArrayList<>();
    expectedKeywords.add("generalApplianceKeyword1");
    expectedKeywords.add("generalApplianceKeyword2");
    expectedKeywords.add("homeApplianceKeyword");
    expectedKeywords.add("default");

    Assertions.assertTrue(result.size() > 0);
    Assertions.assertEquals(expectedKeywords, result, "Los keywords esperados no estan presentes");
  }

  @Test
  public void testGetKeywordsFromCategory_assureExceptionIsThrown() {
    Assertions.assertThrows(CategoryNotFoundException.class, () -> {
      categoryService.getKeywordsForCategory("Bad category");
    });
  }

  @Test
  public void testGetCategoryLevel_assertLastLevel() {
    int result = categoryService.getCategoryLevel("General Appliance");
    Assertions.assertEquals(4, result);
  }

  @Test
  public void testGetCategoryLevel_assertRootLevel() {
    int result = categoryService.getCategoryLevel("ROOT");
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testGetCategoryLevel_assureExceptionIsThrown() {
    Assertions.assertThrows(CategoryNotFoundException.class, () -> {
      categoryService.getCategoryLevel("Bad category");
    });
  }
}
