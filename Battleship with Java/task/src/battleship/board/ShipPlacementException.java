package battleship.board;

public abstract class ShipPlacementException extends RuntimeException{

  public ShipPlacementException(String message) {
    super(message);
  }

  // CRUD-R
  public abstract String msgForUser();
}
