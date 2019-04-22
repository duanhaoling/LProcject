package com.ldh.android.java.sort;


/**
 * desc:
 * <a herf ="https://blog.csdn.net/weixin_38594819/article/details/79234174">常用排序算法总结</a>
 * Created by ldh on 2019/2/14.
 */
public class SortMethods {


    /**
     * 插入排序，稳定排序，O(n2)
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        int n = array.length;
        int j, temp;
        for (int i = 0; i < n; i++) {  //整个数组从前向后排序，到第i个数字
            temp = array[i];
            for (j = i; j > 0 && array[j - 1] > temp; j--) { //已排好序列从后往前遍历，寻找大于待排序的数字索引+1
                array[j] = array[j - 1];
            }
            array[j] = temp; //插入到序列，只插入一次数据
        }
    }

    /**
     * 选择排序，不稳定排序?，O(n2)
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        int i, j, min, temp;
        int n = array.length;
        for (i = 0; i < n; i++) {
            min = i;//查找剩余序列中的最小值索引
            for (j = i + 1; j < n; j++) {
                if (array[min] > array[j]) {//遇到小于当前最小值的，改变最小索引
                    min = j;
                }
            }
            //将最小值与剩余序列第一位数字交换位置
            if (min != i) {
                temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }

    /**
     * 冒泡排序，稳定排序，时间负载度O(n2),空间复杂度O(1)
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null) return;
        for (int i = arr.length - 1; i > 0; i--) { //从最后一位开始排序，最后几位是排好的序列
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    /**
     * 快速排序，非稳定，平均复杂度O(N*lgN)
     * 过程：
     * <p>
     * 1，随机选出一个partition值，把大于partition值的放在它右边，小于它的放在它左边。
     * 2，从partition值的左右两边分割，调用自己，开始递归。
     * 3，这里有一点优化，因为partition值在数组中可能不止一个，因此返回一个长度为2的数组，代表partition的左右边界，从边界两端进行递归，更加快速。
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);

    }

    /**
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        //随机产生partition值，防止出现最坏情况
        swap(arr, right, (int) (Math.random() * (right - left + 1)) + left);
        //返回的数组p为partition的左右边界
        int[] p = partition(arr, left, right);
        quickSort(arr, left, p[0] - 1);
        quickSort(arr, p[1] + 1, right);
    }

    /**
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int[] partition(int[] arr, int left, int right) {
        int less = left - 1, more = right;
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        swap(arr, more, right);
        return new int[]{less + 1, more};

    }


    /**
     * 合并排序，数据库，大数据分析的基础
     * <p>
     * 1，把数组分成两部分，分别比较大小，最后合并。
     * 2，递归调用自己。
     *
     * @param arr
     */
    public static void mergeSoft(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSoft(arr, 0, arr.length - 1);
    }

    private static void mergeSoft(int[] arr, int l, int r) {
        if (l == r) return;
        int mid = l + ((r - l) >> 1); //找到中间位置
        mergeSoft(arr, l, mid);//对左边合并排序
        mergeSoft(arr, mid + 1, r);//对右边合并排序
        merge(arr, l, mid, r);//合并左右两边
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1]; //每次申请额外的空间可以容纳两个待合并数组
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 < mid && p2 <= r) { //两边都还有值，进行比较，较小的放入help
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {         //左边序列还有值
            help[i++] = arr[p1++];
        }

        while ((p2 <= r)) {     //右边序列还有值
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

    }


    //交换位置
    public static void swap(int[] arr, int i, int j) {

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
                /* 不能用于浮点数和交换自身
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        */
    }
}
