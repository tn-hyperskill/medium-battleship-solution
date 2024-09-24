package battleship.ship;

import battleship.cell.CellCoordinates;
import battleship.util.Converter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public final class AnchoredShip {
  // Instance fields
  public final ShipSize size;
  private int hpLeft;
  // Anchors
  public final CellCoordinates start, finish;

  // CRUD-C

  public AnchoredShip(ShipSize size, CellCoordinates start, CellCoordinates finish) {
    this.size = size;
    this.hpLeft = size.volume;
    this.start = start;
    this.finish = finish;

    if (!this.isThin() || this.calcVolume() != this.volume()){
      // Currently fat ships are not tolerated by the game rules.
      throw new IllegalArgumentException();
    }
  }

  // CRUD-R

  // Properties
  private int calcVolume(){
    var min = this.minCellCords();
    var max = this.maxCellCords();
    return (max.row - min.row) * (max.col - min.col);
  }
  private boolean isThin(){
    return this.start.row == this.finish.row || this.start.col == this.finish.col;
  }
  public Stream<CellCoordinates> ctrlZonesStream(){
    return this.hitBoxesStream();
  }
  public Stream<CellCoordinates> hitBoxesStream(){
    return Converter.iteratorToStream(this.hitBoxesIter());
  }
  public Iterator<CellCoordinates> hitBoxesIter(){
    final var ship = this;

    return new Iterator<>() {
      private CellCoordinates headCords = ship.minCellCords();
      private final CellCoordinates headDest = ship.maxCellCords();
      private int miniHeadCol = this.headCords.col;

      @Override public boolean hasNext() {
        return (! this.headCords.equals(this.headDest)) && (ship.volume() > 0);
      }

      @Override public CellCoordinates next() {
        while (this.hasNext()){
          this.miniHeadCol++;
          if (this.miniHeadCol > this.headDest.col){
            // mini-head do \r
            this.miniHeadCol = this.headCords.col;
            // mini-head do \n
            this.headCords = this.headCords.cloneWithRow(this.headCords.row+1);
          }
          return this.headCords.cloneWithCol(this.miniHeadCol);
        }
        throw new NoSuchElementException();
      }
    };
  }

  private CellCoordinates maxCellCords() {
    return this.start.mergeToMaximizeCords(this.finish);
  }

  private CellCoordinates minCellCords() {
    return this.start.mergeToMinimizeCords(this.finish);
  }

  // Getters

  public int hpMax(){
    return this.size.volume;
  }
  public int hpLeft(){
    return this.hpLeft;
  }
  public int volume(){
    return this.size.volume;
  }
}
