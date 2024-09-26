package battleship.ship;

import battleship.board.locs.BoardCoordinates;
import battleship.board.locs.LocsInRectIterator;
import battleship.ship.model.ShipModel;
import battleship.ship.model.ShipModelInconsistentWithLayoutException;
import battleship.util.Converter;
import battleship.util.matrix.MatrixCoordinates;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AnchoredShip extends Ship {

  // Anchors
  public final BoardCoordinates start, finish;

  // CRUD-C

  public AnchoredShip(ShipModel model, BoardCoordinates start,
      BoardCoordinates finish) {
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
    var min = this.minCoords();
    var max = this.maxCoords();
    return (max.row - min.row + 1) * (max.col - min.col + 1);
  }

  private boolean isThin() {
    return this.start.row == this.finish.row
        || this.start.col == this.finish.col;
  }

  public BoardCoordinates maxCoords() {
    return this.start.mergeToMaximizeCords(this.finish);
  }

  public BoardCoordinates minCoords() {
    return this.start.mergeToMinimizeCords(this.finish);
  }

  public String displayHitBoxesForDbg() {
    return String.format("HitBoxes[ %s ]",
        this.hitBoxesStream()
            .map(BoardCoordinates::displayForUser)
            .collect(Collectors.joining(", ")));
  }

  public String displayHitBoxesMatrixCoordsForDbg() {
    return String.format("HitBoxes[ %s ]",
        this.hitBoxesStream()
            .map(MatrixCoordinates::toString)
            .collect(Collectors.joining(", ")));
  }

  // Iterators

  public Stream<BoardCoordinates> ctrlZonesStream() {
    return Converter.iteratorToStream(this.ctrlZonesIter());
  }

  public Iterator<BoardCoordinates> ctrlZonesIter() {
    return new LocsInRectIterator(this.minCoords().saturating_decrement(),
        this.maxCoords().saturating_increment());
  }

  public Stream<BoardCoordinates> hitBoxesStream() {
    return Converter.iteratorToStream(this.hitBoxesIter());
  }

  public Iterator<BoardCoordinates> hitBoxesIter() {
    return new LocsInRectIterator(this.minCoords(), this.maxCoords());
  }

}
