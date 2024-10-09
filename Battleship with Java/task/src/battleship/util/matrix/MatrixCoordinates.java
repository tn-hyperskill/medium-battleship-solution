package battleship.util.matrix;

public class MatrixCoordinates {

  // Instance fields

  /**
   * The row index for indexing a matrix.
   */
  public final int row;

  /**
   * The column index for indexing a matrix.
   */
  public final int col;

  // CRUD-C: Common constructors

  protected MatrixCoordinates(int row, int column) {
    this.row = row;
    this.col = column;
  }

  // CRUD-C: Builder (Lite) Pattern

  /**
   * @return clone of {@code this} with row index changed.
   */
  public MatrixCoordinates cloneWithRow(int $row) {
    return MatrixCoordinates.builder().row($row).column(this.col).build();
  }

  /**
   * @return clone of {@code this} with column index changed.
   */
  public MatrixCoordinates cloneWithCol(int $col) {
    return MatrixCoordinates.builder().row(this.row).column($col).build();
  }

  // CRUD-R: Factory methods

  public static MatrixCoordinatesBuilder builder() {
    return new MatrixCoordinatesBuilder();
  }

  // CRUD-R: Properties

  /**
   * @return true &lt;==&gt; both objects are {@link MatrixCoordinates} that
   * point to the same location in any matrix.
   */
  @Override public boolean equals(Object rhs) {
    return (rhs instanceof MatrixCoordinates brother) && (this.row
        == brother.row)
        && (this.col == brother.col);
  }

  /**
   * Converts {@code this} into a prototype that happens to be a
   * <b>builder</b>.
   * <br><br>
   * Useful for applying prototype design pattern utilizing builder design
   * pattern. If you want to treat {@code this} as a prototype for creation of
   * similar objects, then {@link MatrixCoordinates#toBuilder()} will create new
   * builder by cloning {@code this} object's fields. Then you can easily
   * overwrite some fields using builder pattern. Finally produce any amount of
   * {@link MatrixCoordinates} with chosen fields overwritten.
   *
   * @return corresponding builder holding "same field values" as default.
   */
  public MatrixCoordinatesBuilder toBuilder() {
    return builder().row(this.row).column(this.col);
  }

  /**
   * @return universal hash code for coordinates of square matrix.
   */
  @Override public int hashCode() {
    return (this.row & 0b10101010101010101010101010101010)
        | (this.col & 0b01010101010101010101010101010101);
  }

  // CRUD-R: Displayers

  /**
   * @return {@code this} described in debug oriented format.
   */
  @Override
  public String toString() {
    return String.format("{col: %s, row: %s}", this.col, this.row);
  }

  // CRUD-R: Sexual methods

  /**
   * Produces a child of {@code this} and {@code lover}. <br/><br/> The child
   * will inherit each coordinate either from {@code this} or from
   * {@code lover}, in such a way that resulting coordinates will be <b>minimal</b>.
   * Thus, this value inheritance is deterministic, not random.
   *
   * @param lover to be merged with {@code this}
   * @return
   */
  public MatrixCoordinates mergeToMinimizeCords(
      MatrixCoordinates lover) {
    return new MatrixCoordinatesBuilder()
        .row(Math.min(this.row, lover.row))
        .column(Math.min(this.col, lover.col))
        .build();
  }

  /**
   * Produces a child of {@code this} and {@code lover}. <br/><br/> The child
   * will inherit each coordinate either from {@code this} or from
   * {@code lover}, in such a way that resulting coordinates will be <b>maximal</b>.
   * Thus, this value inheritance is deterministic, not random.
   *
   * @param lover to be merged with {@code this}
   * @return
   */
  public MatrixCoordinates mergeToMaximizeCords(
      MatrixCoordinates lover) {
    return new MatrixCoordinatesBuilder()
        .row(Math.max(this.row, lover.row))
        .column(Math.max(this.col, lover.col))
        .build();
  }

}
