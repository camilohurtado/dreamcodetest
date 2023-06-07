package domain;

import java.util.*;

public class Category {

  private Category parentCat;
  private String categoryName;
  private List<String> keywords;

  public Category(Category parentCat,
                  String categoryName,
                  List<String> keywords)
  {
    this.parentCat = parentCat;
    this.categoryName = categoryName;
    this.keywords = keywords;
  }

  public Category getParentCat() {
    return parentCat;
  }

  public Category setParentCat(Category parentCat) {
    this.parentCat = parentCat;
    return this;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public Category setCategoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public Category setKeywords(List<String> keywords) {
    this.keywords = keywords;
    return this;
  }
}
