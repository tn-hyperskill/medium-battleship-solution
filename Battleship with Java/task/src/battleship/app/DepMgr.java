package battleship.app;

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

  // CRUD-C: Constructors
  protected DepMgr() {
  }

  // CRUD-R: Getters
  public Scanner input() {
    return this.input;
  }

  // CRUD-D: Cleanup-ers

  @Override public void close() throws IOException {
    this.input.close();
  }
}
