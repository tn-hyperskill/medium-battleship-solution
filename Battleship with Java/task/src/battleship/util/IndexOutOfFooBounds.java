package battleship.util;

public class IndexOutOfFooBounds extends IndexOutOfBoundsException {

  public final int index;
  public final String fooName, attributeName;

  public IndexOutOfFooBounds(int index, String fooName, String attributeName) {
    super(String.format("%s index = %s is out of matrix bounds for %s",
        attributeName, index, fooName));
    this.index = index;
    this.fooName = fooName;
    this.attributeName = attributeName;
  }
}
