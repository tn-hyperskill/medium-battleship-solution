package battleship.util.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class Matrix<T> {

  // Instance fields

  private final int width, height;
  private final List<T> cells;

  public Matrix(T defaultVal, int height, int width) {
    this(
        new ArrayList<T>(Collections.nCopies(width * height, defaultVal)),
        height, width
    );
  }

  /**
   * Constructor good for unit tests.
   */
  Matrix(T[] base, int height, int width) {
    this(Arrays.asList(base), height, width);

    assert base.length == width * height;
  }

  private Matrix(List<T> cells, int height, int width) {
    this.cells = cells;
    this.width = width;
    this.height = height;
  }

  // CRUD-R: Getters

  public int height() {
    return this.height;
  }

  public int width() {
    return this.width;
  }

  public int size() {
    return this.width() * this.height();
  }

  // CRUD-R: Pure instance methods

  private int calcCellId(MatrixCoordinates coordinates) {
    // Row-wise storage format.
    var cellId = this.width * coordinates.row + coordinates.col;
    if (cellId > this.size()) {
      if (coordinates.row >= this.height) {
        throw new IndexOutOfBoundsException(String.format(
            "matrix with height=%s doesn't have row with index=%s",
            this.height(), coordinates.row
        ));
      } else if (coordinates.col >= this.width) {
        throw new IndexOutOfBoundsException(String.format(
            "matrix with width=%s doesn't have column with index=%s",
            this.width(), coordinates.col
        ));
      }
    }
    return cellId;
  }

  // CRUD-R: Indexers

  public T valAt(MatrixCoordinates coordinates) {
    return this.valWithId(this.calcCellId(coordinates));
  }

  protected T valWithId(int existingId) {
    return this.cells.get(existingId);
  }

  // CRUD-R: Fabrication methods for external types

  public Stream<T> rowWiseStream() {
    return this.cells.stream();
  }

  public Iterator<T> rowWiseIter() {
    return this.rowWiseStream().iterator();
  }

  // CRUD-U

  public void setValAt(MatrixCoordinates coordinates, T newVal) {
    this.setValWithId(this.calcCellId(coordinates), newVal);
  }

  protected void setValWithId(int validId, T newVal) {
    this.cells.set(validId, newVal);
  }
}
