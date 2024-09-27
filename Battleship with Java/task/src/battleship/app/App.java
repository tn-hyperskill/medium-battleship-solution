package battleship.app;

import static battleship.app.DepMgr.DEP_MGR;

import battleship.board.AntagoBoard;
import battleship.board.ProtagoBoard;
import battleship.board.exception.ShipPlacementException;
import battleship.board.locs.BoardCoordinates;
import battleship.ship.AnchoredShip;
import battleship.ship.model.ShipModel;
import java.text.ParseException;

public final class App {

  public static void run(String[] args) {
    var protagoBoard = ProtagoBoard.empty();
    // IO – Pull dependencies
    var input = DEP_MGR.input();

    for (var shipModel : ShipModel.variantsInPlacingOrd()) {
      // Base prompt
      System.out.println(protagoBoard);
      System.out.printf(
          "Enter the coordinates of the %s (%s cells):\n",
          shipModel, shipModel.volume());
      while (true) {
        try {
          // Pull input
          var coordinates = input.nextLine().split(" ");
          // Input processing
          var start = BoardCoordinates.parse(coordinates[0]);
          var finish = BoardCoordinates.parse(coordinates[1]);
          var ship = new AnchoredShip(shipModel, start, finish);
          protagoBoard.emplaceShip(ship);
          break;
        } catch (ShipPlacementException e) {
          System.out.println(e.msgForUser() + " Try again:");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    System.out.println(protagoBoard);
    System.out.println("The game starts!");

    var antagoBoard = AntagoBoard.antagonizing(protagoBoard);
    for (var gameFinished = false; !gameFinished;){
      System.out.println(antagoBoard);
      System.out.println("Take a shot!");

      while (true) {
        try {
          final BoardCoordinates coords =
              BoardCoordinates.parse(input.nextLine());
          var shotRes = antagoBoard.shootAt(coords);
          System.out.println(antagoBoard);
          if (protagoBoard.hasAliveShips()){
            System.out.println(shotRes.msgForUser());
          }else {
            gameFinished = true;
            System.out.println("You sank the last ship. You won. Congratulations!");
          }

          break;
//          if (!protagoBoard.hasAliveShips()) {
//            break;
//          }
        } catch (ParseException $) {
          System.out.println(
              "Error! You entered the wrong coordinates! Try again:");
        }
      }
    }

  }

  private static void printShipConstructionErr() {

  }
}
