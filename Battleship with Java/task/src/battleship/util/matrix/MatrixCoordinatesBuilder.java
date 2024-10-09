package battleship.util.matrix;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class MatrixCoordinatesBuilder {

  /**
   * Future value for {@link MatrixCoordinates#row} field of
   * {@link MatrixCoordinates}. <br/><br/> Requirements:
   * <ul>
   *   <li>Non-null while calling {@link MatrixCoordinatesBuilder#build()}</li>
   * </ul>
   */
  protected Integer row = null;

  /**
   * Future value for {@link MatrixCoordinates#col} field of
   * {@link MatrixCoordinates}. <br/><br/> Requirements:
   * <ul>
   *   <li>Non-null while calling {@link MatrixCoordinatesBuilder#build()}</li>
   * </ul>
   */
  protected Integer col = null;

  // CRUD-R: Getters
  public final Optional<Integer> row() {
    return Optional.ofNullable(this.row);
  }

  public final Optional<Integer> column() {
    return Optional.ofNullable(this.col);
  }

  // CRUD-R: Fabrication methods for external types

  /**
   * @return {@link MatrixCoordinates} an instance built with cloned values of
   * all fields set in {@code this} builder.
   */
  public MatrixCoordinates build() {
    try {
      return new MatrixCoordinates(this.row().get(), this.column().get());
    } catch (NoSuchElementException e) {
      throw new RuntimeException("not all fields have been initialized", e);
    }
  }

  // CRUD-U: Setters

  /**
   * Builder's setter for built {@link MatrixCoordinates#row} field.
   * @param rowIndex value for {@link MatrixCoordinates#row}
   * @return {@code this} with row index preset.
   */
  public MatrixCoordinatesBuilder row(int rowIndex) {
    if (rowIndex < 0) {
      throw new IllegalArgumentException(
          "matrix's row can't have negative index"
      );
    }
    this.row = rowIndex;
    return this;
  }

  /**
   * Builder's setter for built {@link MatrixCoordinates#col} field.
   * @param columnIndex value for {@link MatrixCoordinates#col}
   * @return {@code this} with column index preset.
   */
  public MatrixCoordinatesBuilder column(int columnIndex) {
    if (columnIndex < 0) {
      throw new IllegalArgumentException(
          "matrix's column can't have negative index"
      );
    }
    this.col = columnIndex;
    return this;
  }

  // CRUD-U: Transformers

  /**
   * Like {@link MatrixCoordinatesBuilder#row(int)} but uses provided
   * function for transforming previously set value (or not set if it is
   * {@link Optional#isEmpty()}) into new value.
   */
  public MatrixCoordinatesBuilder mapRow(
      Function<Optional<Integer>, Integer> mapper) {
    return this.row(mapper.apply(this.row()));
  }

  /**
   * Like {@link MatrixCoordinatesBuilder#column(int)} but uses provided
   * function for transforming previously set value (or not set if it is
   * {@link Optional#isEmpty()}) into new value.
   */
  public MatrixCoordinatesBuilder mapColumn(
      Function<Optional<Integer>, Integer> mapper) {
    return this.column(mapper.apply(this.column()));
  }
}
