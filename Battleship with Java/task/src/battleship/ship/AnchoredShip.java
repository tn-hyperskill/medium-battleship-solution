package battleship.ship;

import battleship.board.BoardCoordinates;
import battleship.board.exception.ShipModelInconsistentWithLayoutException;
import battleship.util.Converter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AnchoredShip extends Ship{

  // Anchors
  public final BoardCoordinates start, finish;

  // CRUD-C

  public AnchoredShip(ShipModel model, BoardCoordinates start, BoardCoordinates finish) {
    super(model);

    this.start = start;
    this.finish = finish;
    // Integrity checks
    if (!this.isThin()) {
      // Currently fat ships are not tolerated by the game rules.
      throw new IllegalArgumentException(
          "Ships that don't have width=1 are invalid.");
    }
    var expectedVolume = this.calcVolume();
    if (this.volume() != expectedVolume) {
      throw new ShipModelInconsistentWithLayoutException(
          this.model(), expectedVolume
      );
    }
  }

  // CRUD-R: Properties
  private int calcVolume() {
    var min = this.minCellCords();
    var max = this.maxCellCords();
    return (max.row - min.row + 1) * (max.col - min.col + 1);
  }

  private boolean isThin() {
    return this.start.row == this.finish.row
        || this.start.col == this.finish.col;
  }

  public BoardCoordinates maxCellCords() {
    return this.start.mergeToMaximizeCords(this.finish);
  }

  public BoardCoordinates minCellCords() {
    return this.start.mergeToMinimizeCords(this.finish);
  }

  public String displayHitBoxesForDbg(){
    return String.format("HitBoxes[ %s ]",
        this.hitBoxesStream()
            .map(BoardCoordinates::displayForUser)
            .collect(Collectors.joining(", ")));
  }
  public String displayHitBoxesMatrixCoordsForDbg(){
    return String.format("HitBoxes[ %s ]",
        this.hitBoxesStream()
            .map(BoardCoordinates::toString)
            .collect(Collectors.joining(", ")));
  }

  // Iterators

  public Stream<BoardCoordinates> ctrlZonesStream() {
    return this.hitBoxesStream();
  }

  public Stream<BoardCoordinates> hitBoxesStream() {
    return Converter.iteratorToStream(this.hitBoxesIter());
  }

  public Iterator<BoardCoordinates> hitBoxesIter() {
    // Temporary variables
    final var $ship = this;
    final var $min = $ship.minCellCords();
    final var $max = $ship.maxCellCords();

    return new Iterator<>() {
      // Immutable instance fields
      private final BoardCoordinates headDest = $max
          .cloneWithCol($max.col + 1);
      private final int savedHeadCol = $min.col;
      // Mutable instance fields
      private BoardCoordinates headCords = $min;

      @Override public boolean hasNext() {
        return (!this.headCords.equals(this.headDest));
      }

      @Override public BoardCoordinates next() {
        while (this.hasNext()) {
          // If we've moved beyond the right margin.
          if (this.headCords.col >= this.headDest.col) {
            // head performs "\r\n"
            this.headCords = this.headCords.toBuilder()
                .mapRow(row -> row.get() + 1)
                .column(this.savedHeadCol)
                .build();
          }
          final var ret = this.headCords;
          this.headCords = this.headCords
              .cloneWithCol(this.headCords.col + 1);
          return ret;
        }
        throw new NoSuchElementException();
      }
    };
  }

}
