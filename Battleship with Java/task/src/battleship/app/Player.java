package battleship.app;

import static battleship.app.DepMgr.DEP_MGR;

import battleship.board.AntagoBoard;
import battleship.board.Board;
import battleship.board.ProtagoBoard;
import battleship.board.exception.ShipPlacementException;
import battleship.board.locs.BoardCoordinates;
import battleship.ship.AnchoredShip;
import battleship.ship.model.ShipModel;
import battleship.shot.ShotResult;
import java.text.ParseException;
import java.util.stream.Collectors;

public final class Player {

  // Instance fields

  private final int id;
  private final ProtagoBoard protagoBoard;
  private final AntagoBoard antagoBoard;

  // CRUD-C: Constructors

  public Player(int id, ProtagoBoard protagoBoard, AntagoBoard antagoBoard) {
    this.id = id;
    this.protagoBoard = protagoBoard;
    this.antagoBoard = antagoBoard;
  }

  // CRUD-R

  public String reprForUser() {
    return "Player " + this.id;
  }

  // CRUD-R: Interactions

  public static void passMove() {
    System.out.println("Press Enter and pass the move to another player");
    DEP_MGR.input().nextLine();
  }

  public void seeBoards() {
    System.out.println(this.antagoBoard);
    System.out.println(Board.COL_ENUMERATOR.valStream().map($ -> "-").collect(
        Collectors.joining()));
    System.out.println(this.protagoBoard);
  }

  public void doShootingTurn() {
    this.seeBoards();
    System.out.printf("%s, it's your turn:\n", this.reprForUser());
    var shotRes = this.shoot();
    System.out.println(shotRes.msgForUser());
    if (this.antagoBoard.doesOpponentHaveAliveShips()) {
      passMove();
    } else {
      System.out.println("You sank the last ship. You won. Congratulations!");
      DEP_MGR.markGameAsFinished();
    }
  }

  // CRUD-U: Interactions with user

  public void emplaceAllShips() {
    // Pull dependencies
    var input = DEP_MGR.input();
    // The interaction
    System.out.printf("%s, place your ships on the game field\n",
        this.reprForUser());
    for (var shipModel : ShipModel.variantsInPlacingOrd()) {
      // Base prompt
      System.out.println(this.protagoBoard);
      System.out.printf(
          "Enter the coordinates of the %s (%s cells):\n",
          shipModel, shipModel.volume());
      while (true) {
        try {
          // Draw input from user.
          var coordinates = input.nextLine().split(" ");
          // Input processing
          var start = BoardCoordinates.parse(coordinates[0]);
          var finish = BoardCoordinates.parse(coordinates[1]);
          var ship = new AnchoredShip(shipModel, start, finish);
          this.protagoBoard.emplaceShip(ship);
          break;
        } catch (ShipPlacementException e) {
          System.out.println(e.msgForUser() + " Try again:");
        } catch (RuntimeException e) {
          System.out.println("Caught an unhandled error. Try again:");
        } catch (ParseException e) {
          System.out.println("Caught an unhandled parsing error. Try again:");
        }
      }
      System.out.println(this.protagoBoard);
    }
  }

  private ShotResult shoot() {
    while (true) {
      try {
        // Reading coordinates from user.
        final var coords = BoardCoordinates.parse(DEP_MGR.input().nextLine());
        final var shotRes = this.antagoBoard.shootAt(coords);
        return shotRes;
      } catch (ParseException $) {
        System.out.println(
            "Error! You entered the wrong coordinates! Try again:");
      }
    }
  }
}
