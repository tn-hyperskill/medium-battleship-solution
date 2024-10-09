package battleship.util.matrix.exceptions;

public class RowIndexOutOfMatrixBounds extends IndexOutOfMatrixBounds {

  public RowIndexOutOfMatrixBounds(int index) {
    super(index, "row");
  }
}
