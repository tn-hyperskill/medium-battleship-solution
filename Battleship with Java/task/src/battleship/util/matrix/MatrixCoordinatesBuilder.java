package battleship.util.matrix;

import battleship.board.BoardCoordinates;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

public class MatrixCoordinatesBuilder {

  protected Integer row = null;
  protected Integer col = null;

  // CRUD-R: Getters
  public Optional<Integer> row(){
    return Optional.ofNullable(this.row);
  }
  public Optional<Integer> column(){
    return Optional.ofNullable(this.col);
  }

  // CRUD-R: Fabrication methods for external types
  public MatrixCoordinates build() {
    try{
      return new MatrixCoordinates(this.row().get(), this.column().get());
    }catch (NoSuchElementException e){
      throw new RuntimeException("not all fields have been initialized", e);
    }
  }

  // CRUD-U: Setters
  public MatrixCoordinatesBuilder row(int rowIndex) {
    if (rowIndex < 0){
      throw new IllegalArgumentException(
          "matrix's row can't have negative index"
      );
    }
    this.row = rowIndex;
    return this;
  }
  public MatrixCoordinatesBuilder column(int columnIndex) {
    if (columnIndex < 0){
      throw new IllegalArgumentException(
          "matrix's column can't have negative index"
      );
    }
    this.col = columnIndex;
    return this;
  }

  // CRUD-U: Transformers
  public MatrixCoordinatesBuilder mapRow(Function<Optional<Integer>, Integer> mapper){
    return this.row(mapper.apply(this.row()));
  }
  public MatrixCoordinatesBuilder mapColumn(Function<Optional<Integer>, Integer> mapper){
    return this.column(mapper.apply(this.column()));
  }
}