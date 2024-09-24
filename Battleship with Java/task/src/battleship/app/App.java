package battleship.app;

import static battleship.app.DepMgr.DEP_MGR;

import battleship.board.BoardCoordinates;
import battleship.board.ProtagoBoard;
import battleship.ship.AnchoredShip;
import java.util.stream.Collectors;

public final class App {
  public static void run(String[] args){
    var protagoBoard = ProtagoBoard.empty();
    // IO – Pull dependencies
    var input = DEP_MGR.input();
    System.out.println(protagoBoard);
    System.out.println("Enter the coordinates of the ship:");
    var coordinates = input.nextLine().split(" ");
    try{
      var start = BoardCoordinates.parse(coordinates[0]);
      var finish = BoardCoordinates.parse(coordinates[1]);
      var ship = new AnchoredShip(start, finish);
      protagoBoard.placeShip(ship);
      System.out.printf("""
          Length: %s
          Parts: %s
          """, ship.size.volume, ship.hitBoxesStream()
          .map(Object::toString)
          .collect(Collectors.joining(" ")));
    }catch (Exception e){
      e.printStackTrace();
      System.out.println("Error!");
    }
  }
}
