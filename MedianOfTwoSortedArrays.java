/**
 * 4. Median of Two Sorted Arrays
 * Solved
 * Hard
 * 
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 * 
 * Example 1:
 * Input: nums1 = [1, 3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1, 2, 3] and median is 2.
 * 
 * Example 2:
 * Input: nums1 = [1, 2], nums2 = [3, 4]
 * Output: 2.50000
 * Explanation: merged array = [1, 2, 3, 4] and median is (2 + 3) / 2 = 2.5.
 * 
 * Constraints:
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */

 import java.util.*;

 public class MedianOfTwoSortedArrays {
     /**
      * Finds the median of two sorted arrays.
      */
     public double findMedianSortedArrays(int[] nums1, int[] nums2) {
         // Ensure nums1 is the smaller array
         if (nums1.length > nums2.length) {
             return findMedianSortedArrays(nums2, nums1);
         }
 
         int n1 = nums1.length;
         int n2 = nums2.length;
         int N = n1 + n2;
         int start = 0;
         int end = n1;
 
         while (start <= end) {
             // Calculate partitions
             int cut1 = start + (end - start) / 2;
             int cut2 = N / 2 - cut1;
 
             // Calculate left and right elements around the partitions
             int l1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
             int l2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
             int r1 = (cut1 == n1) ? Integer.MAX_VALUE : nums1[cut1];
             int r2 = (cut2 == n2) ? Integer.MAX_VALUE : nums2[cut2];
 
             // Check if partitions are correct
             if (l1 <= r2 && l2 <= r1) {
                 if (N % 2 != 0) {
                     // If odd, return the smaller of the right elements
                     return (double) Math.min(r1, r2);
                 } else {
                     // If even, return the average of the largest left element and the smallest right element
                     return ((Math.max(l1, l2) + Math.min(r1, r2)) / 2.0);
                 }
             } else if (l1 > r2) {
                 // Adjust binary search range
                 end = cut1 - 1;
             } else {
                 start = cut1 + 1;
             }
         }
         return 0.0;
     }
 
     public static void main(String[] args) {
         MedianOfTwoSortedArrays solution = new MedianOfTwoSortedArrays();
         
         // Example 1
         int[] nums1 = {1, 3};
         int[] nums2 = {2};
         System.out.println("Median: " + solution.findMedianSortedArrays(nums1, nums2)); // Output: 2.0
 
         // Example 2
         nums1 = new int[]{1, 2};
         nums2 = new int[]{3, 4};
         System.out.println("Median: " + solution.findMedianSortedArrays(nums1, nums2)); // Output: 2.5
     }
 }
 
