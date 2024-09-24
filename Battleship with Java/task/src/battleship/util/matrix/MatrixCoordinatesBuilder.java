package battleship.util.matrix;

public class MatrixCoordinatesBuilder {

  private Integer row = null;
  private Integer column = null;

  public MatrixCoordinatesBuilder row(int row) {
    this.row = row;
    return this;
  }

  public MatrixCoordinatesBuilder column(int column) {
    this.column = column;
    return this;
  }

  public MatrixCoordinates build() {
    try{
      return new MatrixCoordinates(this.row, this.column);
    }catch (NullPointerException npe){
      throw new RuntimeException("not all fields have been initialized", npe);
    }
  }
}