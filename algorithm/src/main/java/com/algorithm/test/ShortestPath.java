package com.algorithm.test;

/**
 * Created by zhangyaping on 2019/8/16.
 */
public class ShortestPath {

    private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};

    private int n = 4;

    private int[][] mem = new int[4][4];

    public int minDist(int i, int j) { // 调用minDist(n-1, n-1);
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }
        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    public static void main(String[] args) {
        int length = new ShortestPath().minDist(3, 3);
        System.out.println(length);
    }
}

