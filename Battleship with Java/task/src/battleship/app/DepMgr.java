package battleship.app;

import battleship.board.AntagoBoard;
import battleship.board.ProtagoBoard;
import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

public final class DepMgr implements Closeable {

  // Singleton
  public static final DepMgr DEP_MGR = new DepMgr();

  static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        DEP_MGR.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }));
  }

  // Instance fields
  private final Scanner input = new Scanner(System.in);
  private boolean gameFinished = false;
  private final Player[] players;

  // CRUD-C: Constructors
  protected DepMgr() {
    var protagoBoard1 = ProtagoBoard.empty();
    var protagoBoard2 = ProtagoBoard.empty();

    this.players = new Player[]{
        new Player(1, protagoBoard1, AntagoBoard.antagonizing(protagoBoard2)),
        new Player(2, protagoBoard2, AntagoBoard.antagonizing(protagoBoard1))
    };
  }

  // CRUD-R: Getters

  public Scanner input() {
    return this.input;
  }

  public boolean isGameFinished() {
    return this.gameFinished;
  }

  public Player[] players() {
    return this.players;
  }

  // CRUD-U: Setters

  public void markGameAsFinished() {
    this.gameFinished = true;
  }

  // CRUD-D: Cleanup-ers

  @Override public void close() throws IOException {
    this.input.close();
  }
}
