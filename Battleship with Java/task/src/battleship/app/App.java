package battleship.app;

import static battleship.app.DepMgr.DEP_MGR;

import battleship.board.BoardCoordinates;
import battleship.board.ProtagoBoard;
import battleship.ship.AnchoredShip;
import battleship.ship.ShipModel;

public final class App {
  public static void run(String[] args){
    var protagoBoard = ProtagoBoard.empty();
    // IO – Pull dependencies
    var input = DEP_MGR.input();

    for (var shipModel : ShipModel.variantsInPlacingOrd()){
      System.out.println(protagoBoard);
      System.out.printf(
          "Enter the coordinates of the %s (%s cells):\n",
          shipModel, shipModel.volume());
      var coordinates = input.nextLine().split(" ");
      try{
        var start = BoardCoordinates.parse(coordinates[0]);
        var finish = BoardCoordinates.parse(coordinates[1]);
        var ship = new AnchoredShip(shipModel, start, finish);
        protagoBoard.emplaceShip(ship);
      }catch (Exception e){
        e.printStackTrace();
        System.out.println("Error!");
      }
    }
    System.out.println(protagoBoard);
  }
}
