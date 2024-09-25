package battleship.board.locs;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <h6>Locations In Rectangle, Iterator</h6>
 */
public class LocsInRectIterator implements Iterator<BoardCoordinates> {

  // Immutable instance fields
  private final BoardCoordinates headDest;
  private final int savedHeadCol;
  // Mutable instance fields
  private BoardCoordinates headCords;

  public LocsInRectIterator(BoardCoordinates minCoords, BoardCoordinates maxCoords) {
    this.headCords = minCoords;
    this.savedHeadCol = minCoords.col;
    this.headDest = maxCoords.cloneWithCol(maxCoords.col + 1);  // Move headDest one past the end
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
    return ret;
  }
}
