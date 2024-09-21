package battleship.board;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public final class CellMatrix {

  // Instance fields

  private final int width, height;
  private final Cell[] cells;

  public CellMatrix(int width, int height) {
    var cells = new Cell[width * height];
    Arrays.fill(cells, Cell.FOG);

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

  private Cell cellIn(int row, int col) {
    return this.cells[this.calcCellId(row, col)];
  }

  private void setCellIn(int row, int col, Cell newVal) {
    this.cells[this.calcCellId(row, col)] = newVal;
  }

  // CRUD-R: Fabrication methods for external types

  public Stream<Cell> streamRowWisely() {
    var copiedCells = Arrays.copyOf(this.cells, this.size());
    return Arrays.stream(copiedCells);
  }

  public Iterator<Cell> rowWiseIter() {
    return this.streamRowWisely().iterator();
  }
}
