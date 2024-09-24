package battleship.cell;

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

  public Matrix(T defaultVal, int width, int height) {
    int capacity = width * height;
    var cells = new ArrayList<>(Collections.nCopies(capacity, defaultVal));

    this.width = width;
    this.height = height;
    this.cells = cells;
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

  private int calcCellId(int row, int col) {
    // Row-wise storage format.
    return this.width * row + col;
  }

  // CRUD-R: Indexers

  public T valIn(int row, int col) {
    return this.cells.get(this.calcCellId(row, col));
  }

  public void setValIn(int row, int col, T newVal) {
    this.cells.set(this.calcCellId(row, col), newVal);
  }

  // CRUD-R: Fabrication methods for external types

  public Stream<T> rowWiseStream() {
    return this.cells.stream();
  }

  public Iterator<T> rowWiseIter() {
    return this.rowWiseStream().iterator();
  }
}
