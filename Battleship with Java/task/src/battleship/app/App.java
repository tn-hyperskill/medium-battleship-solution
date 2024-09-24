package battleship.app;

import battleship.board.ProtagoBoard;

public final class App {
  public static void run(String[] args){
    var board1 = ProtagoBoard.empty();

    System.out.println(board1);
  }
}
