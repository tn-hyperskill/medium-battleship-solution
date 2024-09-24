package battleship.util.matrix.exceptions;

public class ColIndexOutOfMatrixBounds extends IndexOutOfMatrixBounds {

  public ColIndexOutOfMatrixBounds(int index) {
    super(index, "column");
  }
}
