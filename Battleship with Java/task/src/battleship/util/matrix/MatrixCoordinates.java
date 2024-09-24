package battleship.util.matrix;

public class MatrixCoordinates {

  // Instance fields
  public final int row, col;

  // CRUD-C: Common constructors

  protected MatrixCoordinates(int row, int column) {
    this.row = row;
    this.col = column;
  }

  // CRUD-C: Builder (Lite) Pattern
  public MatrixCoordinates cloneWithRow(int $row) {
    return MatrixCoordinates.builder().row($row).column(this.col).build();
  }
  public MatrixCoordinates cloneWithCol(int $col) {
    return MatrixCoordinates.builder().row(this.row).column($col).build();
  }

  public static MatrixCoordinatesBuilder builder(){
    return new MatrixCoordinatesBuilder();
  }

  // CRUD-R: Properties
  @Override public boolean equals(Object rhs) {
    return (rhs instanceof MatrixCoordinates brother) && (this.row == brother.row)
        && (this.col == brother.col);
  }

  @Override public int hashCode() {
    return (this.row & 0b10101010101010101010101010101010) |
        (this.col & 0b01010101010101010101010101010101);
  }

  // CRUD-R: Sexual methods

  public MatrixCoordinates mergeToMinimizeCords(
      MatrixCoordinates lover) {
    var child = new MatrixCoordinatesBuilder().row(Math.min(this.col, lover.col))
        .column(Math.min(this.row, lover.row)).build();
    return child;
  }

  public MatrixCoordinates mergeToMaximizeCords(
      MatrixCoordinates lover) {
    var child = new MatrixCoordinatesBuilder().row(Math.max(this.col, lover.col))
        .column(Math.max(this.row, lover.row)).build();
    return child;
  }

}
