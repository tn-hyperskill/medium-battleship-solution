package battleship.board;

public enum Cell {
  FOG('~'), MISS('≈'), HIT('*'), SUNK('$');
  // Instance fields
  final char symbol;

  // CRUD-C

  Cell(char symbol) {
    this.symbol = symbol;
  }

  // CRUD-R

  @Override
  public String toString() {
    return String.valueOf(this.symbol);
  }
}
