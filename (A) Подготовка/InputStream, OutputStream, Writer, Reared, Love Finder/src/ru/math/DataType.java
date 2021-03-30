package ru.math;

public enum DataType {
  BYTE(1),
  KILOBYTE(1_000),
  MEGABYTE(1_000_000),
  GIGABYTE(1_000_000_000);

  private final long multiplier;

  DataType(long multiplier) {
    this.multiplier = multiplier;
  }

  public long count(int count) {
    return multiplier * count;
  }
}
