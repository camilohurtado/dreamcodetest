package exception;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException() {
    this("Categoria no encontrada");
  }

  private CategoryNotFoundException(String message) {
    super(message);
  }
}
