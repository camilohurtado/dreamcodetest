package service;

import domain.*;
import exception.*;

import java.util.*;

public class CategoryService {

  List<Category> categories;

  public CategoryService(List<Category> categories) {
    this.categories = categories;
  }

  public List<String> getKeywordsForCategory(String categoryName) {
    Category category = findCategory(categoryName);
    return searchForKeywords(category);
  }

  /**
   * Utility method
   *
   * Assure if category exist in list of categories, given a category name.
   * @param categoryName
   * @return
   * @throws CategoryNotFoundException
   */
  private Category findCategory(String categoryName) throws CategoryNotFoundException{
    return categories
             .stream()
             .filter(category1 -> category1.getCategoryName().equals(categoryName)).findAny()
             .orElseThrow(CategoryNotFoundException::new);
  }

  /**
   * This method traverse categories node structure from given category to root category
   * and accumulate respective keywords.
   *
   * @param givenCategory
   * @return
   */
  private List<String> searchForKeywords(Category givenCategory) {

    if (givenCategory == null) {
      throw new RuntimeException();
    }

    List<String> keywords = new ArrayList<>(Optional.ofNullable(givenCategory.getKeywords()).orElse(new ArrayList<>()));

    if(givenCategory.getParentCat() == null) return keywords;

    Category parentCategory = givenCategory.getParentCat();

    while(parentCategory != null) {
      keywords.addAll(parentCategory.getKeywords());
      parentCategory = parentCategory.getParentCat();
    }

    return keywords;
  }

  public int getCategoryLevel(String categoryName) {

    Category category = findCategory(categoryName);
    int level = 1;

    if(category.getParentCat() == null) return level;

    Category parentCategory = category.getParentCat();

    while(parentCategory != null) {
      level += 1;
      parentCategory = parentCategory.getParentCat();
    }

    return level;
  }
}
