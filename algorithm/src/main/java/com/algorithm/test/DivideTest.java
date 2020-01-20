package com.algorithm.test;

/**
 * Created by zhangyaping on 2019/7/26.
 * 分治法求数组中的逆序对数量
 */
public class DivideTest {

    private int num = 0;

    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r) {
        System.out.println(p + " " + r);
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {
        System.out.println(p + " " + q + " " + r);
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += (q - i + 1);
                // 统计p-q之间，比a[j]大的元素个数
                tmp[k++] = a[j++];
            }
        }
        while (i <= q) {
            // 处理剩下的
            tmp[k++] = a[i++];
        }
        while (j <= r) {
            // 处理剩下的
            tmp[k++] = a[j++];
        }
        for (i = 0; i <= r - p; ++i) {
            // 从tmp拷贝回a
            a[p + i] = tmp[i];
        }

        System.out.println(arrayToString(tmp));
    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 3, 2, 7, 4};
        int count = new DivideTest().count(arr, arr.length);
        System.out.println(count);
    }

    String arrayToString(int[] a) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i] + " ");
        }
        sb.append("]");
        return sb.toString();
    }
}
