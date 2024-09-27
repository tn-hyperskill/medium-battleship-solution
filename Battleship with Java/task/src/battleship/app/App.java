package battleship.app;

import static battleship.app.DepMgr.DEP_MGR;

public final class App {

  public static void run(String[] _args) {
    App.playersPlaceAllShips();
    App.playersExchangeFire();
  }

  private static void playersPlaceAllShips() {
    for (var player : DEP_MGR.players()) {
      player.emplaceShips();
      player.passMove();
    }
  }

  private static void playersExchangeFire() {
    while (true) {
      for (var player : DEP_MGR.players()) {
        player.doShootingTurn();
        if (DEP_MGR.isGameFinished()){
          return;
        }
      }
    }
  }
}
