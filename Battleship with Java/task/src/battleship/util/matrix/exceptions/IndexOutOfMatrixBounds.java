package battleship.util.matrix.exceptions;

import battleship.util.IndexOutOfFooBounds;

public class IndexOutOfMatrixBounds extends IndexOutOfFooBounds {

  public IndexOutOfMatrixBounds(int index, String attributeName) {
    super(index, "matrix", attributeName);
  }
}
