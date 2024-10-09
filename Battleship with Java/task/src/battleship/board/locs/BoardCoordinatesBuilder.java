package battleship.board.locs;

import battleship.board.Board;
import battleship.util.matrix.MatrixCoordinatesBuilder;
import battleship.util.matrix.exceptions.ColIndexOutOfMatrixBounds;
import battleship.util.matrix.exceptions.RowIndexOutOfMatrixBounds;
import java.util.Optional;
import java.util.function.Function;

public final class BoardCoordinatesBuilder extends MatrixCoordinatesBuilder {

  // CRUD-C
  public BoardCoordinatesBuilder() {
  }

  // CRUD-R: Factory methods
  @Override
  public BoardCoordinates build() {
    final var superCoords = super.build();
    return new BoardCoordinates(superCoords.row, superCoords.col);
  }

  // CRUD-U: Setters
  @Override
  public BoardCoordinatesBuilder row(int rowIndex) {
    if (!Board.ROW_ENUMERATOR.hasIdx(rowIndex)) {
      throw new RowIndexOutOfMatrixBounds(rowIndex);
    }
    this.row = rowIndex;
    return this;
  }

  @Override
  public BoardCoordinatesBuilder column(int columnIndex) {
    if (!Board.COL_ENUMERATOR.hasIdx(columnIndex)) {
      throw new ColIndexOutOfMatrixBounds(columnIndex);
    }
    this.col = columnIndex;
    return this;
  }

  // CRUD-U: Transformers
  @Override
  public BoardCoordinatesBuilder mapRow(
      Function<Optional<Integer>, Integer> mapper) {
    return this.row(mapper.apply(this.row()));
  }

  @Override
  public BoardCoordinatesBuilder mapColumn(
      Function<Optional<Integer>, Integer> mapper) {
    return this.column(mapper.apply(this.column()));
  }

  public BoardCoordinatesBuilder tryMapRow(
      Function<Optional<Integer>, Integer> mapper) {
    try {
      this.mapRow(mapper);
    } catch (RowIndexOutOfMatrixBounds $) {
      // Silencing error.
    }
    return this;
  }

  public BoardCoordinatesBuilder tryMapColumn(
      Function<Optional<Integer>, Integer> mapper) {
    try {
      this.mapColumn(mapper);
    } catch (ColIndexOutOfMatrixBounds $) {
      // Silencing error.
    }
    return this;
  }

}
