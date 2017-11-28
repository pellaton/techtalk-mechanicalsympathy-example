package org.sample;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;
import static org.openjdk.jmh.annotations.Level.Invocation;
import static org.openjdk.jmh.annotations.Mode.Throughput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@Fork(1)
public class MatrixTraversalBenchmark {

  private static final int JMH_WARMUP_ITERATIONS = 5;
  private static final int JMH_WARMUP_TIME = 20;
  private static final int JMH_MEASUREMENT_ITERATIONS = 5;
  private static final int JMH_MEASUREMENT_TIME = 20;

  private static final int MATRIX_SIZE = 1000;

  private static final Random RANDOM = new Random(System.currentTimeMillis());

  private int[][] matrixArray;
  private ArrayList<ArrayList<Integer>> matrixArrayList;
  private LinkedList<LinkedList<Integer>> matrixLinkedList;

  @Setup(Invocation)
  public void setup() {
    matrixArray = randomMatrixArray();
    matrixArrayList = randomMatrixArrayList();
    matrixLinkedList = randomMatrixLinkedList();
  }

  private static int[][] randomMatrixArray() {
    int[][] mtrx = new int[MATRIX_SIZE][MATRIX_SIZE];

    for (int i = 0; i < MATRIX_SIZE; i++) {
      mtrx[i] = RANDOM.ints(MATRIX_SIZE).toArray();
    }

    return mtrx;
  }

  private ArrayList<ArrayList<Integer>> randomMatrixArrayList() {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    range(0, MATRIX_SIZE).forEach(i -> result.add(RANDOM.ints(MATRIX_SIZE).boxed().collect(toCollection(ArrayList::new))));

    return result;
  }

  private LinkedList<LinkedList<Integer>> randomMatrixLinkedList() {
    LinkedList<LinkedList<Integer>> result = new LinkedList<>();
    range(0, MATRIX_SIZE).forEach(i -> result.add(RANDOM.ints(MATRIX_SIZE).boxed().collect(toCollection(LinkedList::new))));

    return result;
  }

  @Benchmark
  @BenchmarkMode(Throughput)
  @Warmup(iterations = JMH_WARMUP_ITERATIONS, time = JMH_WARMUP_TIME)
  @Measurement(iterations = JMH_MEASUREMENT_ITERATIONS, time = JMH_MEASUREMENT_TIME)
  public int findMaxValueInLinkedListYthenX() {

    int result = Integer.MIN_VALUE;

    for (int x = 0; x < MATRIX_SIZE; x++) {
      for (int y = 0; y < MATRIX_SIZE; y++) {
        result = result > matrixLinkedList.get(x).get(y) ? result : matrixLinkedList.get(x).get(y);
      }
    }

    return result;
  }

  @Benchmark
  @BenchmarkMode(Throughput)
  @Warmup(iterations = JMH_WARMUP_ITERATIONS, time = JMH_WARMUP_TIME)
  @Measurement(iterations = JMH_MEASUREMENT_ITERATIONS, time = JMH_MEASUREMENT_TIME)
  public int findMaxValueInLinkedListXthenY() {

    int result = Integer.MIN_VALUE;

    for (int x = 0; x < MATRIX_SIZE; x++) {
      for (int y = 0; y < MATRIX_SIZE; y++) {
        result = result > matrixLinkedList.get(y).get(x) ? result : matrixLinkedList.get(y).get(x);
      }
    }

    return result;
  }

  @Benchmark
  @BenchmarkMode(Throughput)
  @Warmup(iterations = JMH_WARMUP_ITERATIONS, time = JMH_WARMUP_TIME)
  @Measurement(iterations = JMH_MEASUREMENT_ITERATIONS, time = JMH_MEASUREMENT_TIME)
  public int findMaxValueInArrayListYthenX() {

    int result = Integer.MIN_VALUE;

    for (int x = 0; x < MATRIX_SIZE; x++) {
      for (int y = 0; y < MATRIX_SIZE; y++) {
        result = result > matrixArrayList.get(x).get(y) ? result : matrixArrayList.get(x).get(y);
      }
    }

    return result;
  }

  @Benchmark
  @BenchmarkMode(Throughput)
  @Warmup(iterations = JMH_WARMUP_ITERATIONS, time = JMH_WARMUP_TIME)
  @Measurement(iterations = JMH_MEASUREMENT_ITERATIONS, time = JMH_MEASUREMENT_TIME)
  public int findMaxValueInArrayListXthenY() {

    int result = Integer.MIN_VALUE;

    for (int x = 0; x < MATRIX_SIZE; x++) {
      for (int y = 0; y < MATRIX_SIZE; y++) {
        result = result > matrixArrayList.get(y).get(x) ? result : matrixArrayList.get(y).get(x);
      }
    }

    return result;
  }

  @Benchmark
  @BenchmarkMode(Throughput)
  @Warmup(iterations = JMH_WARMUP_ITERATIONS, time = JMH_WARMUP_TIME)
  @Measurement(iterations = JMH_MEASUREMENT_ITERATIONS, time = JMH_MEASUREMENT_TIME)
  public int findMaxValueInArrayXthenY() {

    int result = Integer.MIN_VALUE;

    for (int x = 0; x < MATRIX_SIZE; x++) {
      for (int y = 0; y < MATRIX_SIZE; y++) {
        result = result > matrixArray[x][y] ? result : matrixArray[x][y];
      }
    }

    return result;
  }

  @Benchmark
  @BenchmarkMode(Throughput)
  @Warmup(iterations = JMH_WARMUP_ITERATIONS, time = JMH_WARMUP_TIME)
  @Measurement(iterations = JMH_MEASUREMENT_ITERATIONS, time = JMH_MEASUREMENT_TIME)
  public int findMaxValueInArrayYthenX() {

    int result = Integer.MIN_VALUE;

    for (int x = 0; x < MATRIX_SIZE; x++) {
      for (int y = 0; y < MATRIX_SIZE; y++) {
        result = result > matrixArray[y][x] ? result : matrixArray[y][x];
      }
    }

    return result;
  }

}
