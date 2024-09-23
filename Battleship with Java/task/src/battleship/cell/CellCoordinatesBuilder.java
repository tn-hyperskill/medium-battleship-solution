package battleship.cell;

import battleship.board.Board;

public class CellCoordinatesBuilder {

  private Integer row = null;
  private Integer column = null;

  public CellCoordinatesBuilder row(int row) {
    if (!Board.ROW_ENUMERATOR.hasIdx(row)){
      throw new IllegalArgumentException("nonexistent row index");
    }
    this.row = row;
    return this;
  }

  public CellCoordinatesBuilder column(int column) {
    if (!Board.COL_ENUMERATOR.hasIdx(column)){
      throw new IllegalArgumentException("nonexistent column index");
    }
    this.column = column;
    return this;
  }

  public CellCoordinates build() {
    try{
      return new CellCoordinates(this.row, this.column);
    }catch (NullPointerException npe){
      throw new RuntimeException("not all fields have been initialized", npe);
    }
  }
}