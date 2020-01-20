package com.algorithm.test;

/**
 * Created by zhangyaping on 2019/8/5.
 */
public class EightQueensTest {

    //下标表示行   值表示queen存储在哪一列
    int[] result = new int[8];

    public void cal8Queens(int row) {
        // 8个棋子都放置好了，打印结果
        if (row == 8) {
            printQueens(result);
            return;
        }
        // 每一行都有8中放法
        for (int column = 0; column < 8; ++column) {
            if (isOk(row, column)) {
                result[row] = column;
                // 考察下一行
                cal8Queens(row + 1);
            }
        }
    }

    /**
     * 判断row行column列放置是否合适
     */
    private boolean isOk(int row, int column) {
        int leftup = column - 1, rightup = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            // 第i行的column列有棋子吗？
            if (result[i] == column) {
                return false;
            }
            // 考察左上对角线：第i行leftup列有棋子吗？
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            // 考察右上对角线：第i行rightup列有棋子吗？
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new EightQueensTest().cal8Queens(0);
    }
}
