package battleship.board.locs;

import battleship.util.matrix.MatrixCoordinates;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <h6>Locations In Rectangle, Iterator</h6>
 */
public final class LocsInRectIterator implements Iterator<BoardCoordinates> {

  // Immutable instance fields
  private final MatrixCoordinates headDest;
  private final int savedHeadCol;
  // Mutable instance fields
  private MatrixCoordinates headCords;

  public LocsInRectIterator(BoardCoordinates minCoords,
      BoardCoordinates maxCoords) {
    this.headCords = minCoords.sliceObj();
    this.savedHeadCol = minCoords.col;
    this.headDest = maxCoords.sliceObj()
        .cloneWithCol(maxCoords.col + 1);  // Move headDest one past the end
  }

  @Override
  public boolean hasNext() {
    return !this.headCords.equals(this.headDest);
  }

  @Override
  public BoardCoordinates next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }

    // If we've moved beyond the right margin.
    if (this.headCords.col >= this.headDest.col) {
      // head performs "\r\n"
      this.headCords = this.headCords.toBuilder()
          .mapRow(row -> row.get() + 1)
          .column(this.savedHeadCol)
          .build();
    }

    final var ret = this.headCords;
    this.headCords = this.headCords.cloneWithCol(this.headCords.col + 1);
    return BoardCoordinates.valueOf(ret);
  }
}
