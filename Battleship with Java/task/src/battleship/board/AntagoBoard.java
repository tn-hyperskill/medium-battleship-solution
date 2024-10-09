package battleship.board;

import battleship.board.locs.BoardCoordinates;
import battleship.cell.Cell;
import battleship.shot.ShotResult;

/**
 * <h6>Antagonist's Board</h6>
 */
public final class AntagoBoard extends Board {
  // Instance fields

  private final ProtagoBoard antagonized;

  // CRUD-C

  public static AntagoBoard antagonizing(ProtagoBoard opponent) {
    return new AntagoBoard(opponent);
  }

  protected AntagoBoard(ProtagoBoard antagonized) {
    super(Cell.fog());
    this.antagonized = antagonized;
  }

  // CRUD-R

  public boolean doesOpponentHaveAliveShips() {
    return this.antagonized.hasAliveShips();
  }

  // CRUD-U

  public ShotResult shootAt(final BoardCoordinates coordinates) {
    final var shotResult = this.antagonized.takeShotAt(coordinates);

    this.cellsMtx.setValAt(coordinates, shotResult.transformedCell());

    return shotResult;
  }
}
