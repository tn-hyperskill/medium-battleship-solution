package battleship.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class DoublyIndexedTable<T> {

  // Instance fields
  private final List<T> table;
  private final Map<T, Integer> secondIndexer;

  // CRUD-C: Factory methods

  public static <T> DoublyIndexedTable<T> fromSeq(final Stream<T> base) {
    return new DoublyIndexedTable(base.toList());
  }

  public static <T> DoublyIndexedTable<T> fromSeq(final List<T> base) {
    return new DoublyIndexedTable(List.copyOf(base));
  }

  public static <T> DoublyIndexedTable<T> fromSeq(final T[] base) {
    return new DoublyIndexedTable(List.of(base));
  }

  // CRUD-C: Priv. constructors

  private DoublyIndexedTable(final List<T> immutBaseTable) {
    // Declaring pre-field values
    var $secondIndexer = new HashMap<T, Integer>();
    // Initializing pre-field values
    IntStream.range(0, immutBaseTable.size())
        .forEach(curIdx -> {
          var currEl = immutBaseTable.get(curIdx);
          Integer oldIdx = $secondIndexer.put(currEl, curIdx);
          if (oldIdx != null) {
            throw new IllegalArgumentException(String.format(
                """
                    the provided array contains 2+ `Object.equal` values:
                    {%s, %s, ...} 
                    """,
                curIdx, oldIdx
            ));
          }
        });
    // Initializing fields.
    this.table = immutBaseTable;
    this.secondIndexer = Collections.unmodifiableMap($secondIndexer);
  }

  // CRUD-R: Indexers

  /**
   * @return value at the provided index
   */
  public T value(final int index) {
    return this.table.get(index);
  }

  /**
   * @return index of the provided value
   */
  public Integer index(T value) {
    return this.secondIndexer.get(value);
  }

  // CRUD-R: Getters

  public int size() {
    return this.table.size();
  }

  // CRUD-R: Streams

  public Stream<T> valStream() {
    return this.table.stream();
  }

  public Iterator<T> valIter() {
    return this.valStream().iterator();
  }
}
