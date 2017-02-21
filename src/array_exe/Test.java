package array_exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * ------------------------------------------------------------------------------
 * ----------------------练习July书“编程之法”中第二章数组部分--------------------------
 * ------------------------------------------------------------------------------
 * 包含如下几个部分：
 * （1）寻找最小的k个数
 * （2）寻找和为定值的两个数
 * （3）寻找和为定值得三个数
 * （4）寻找和为定值的四个数
 * （5）寻找和为定值的多个数（非2Sum,3Sum,4Sum问题）
 * （6）最大连续子数组和
 * （7）连续子数组的最大乘积
 * （8）最大子矩阵和
 * （9）最大连续子数组的增强版（允许交换位置）(暂未实现)
 * （10）长度最短的连续子序列
 * （11）跳台阶问题
 * （12）换硬币问题（没搞明白）
 * （13）奇偶数排序
 */
public class Test {
	/**
	 * （1）寻找最小的k个数
	 * 题目描述：
	 * 	有n个整数，找出其中最小的k个数
	 * 思路：
	 * 	（1）部分排序（数组实现）,O(nk)
	 * 	（2）部分排序（堆实现）,O(nlogk)
	 * 	（3）递归形式的快速选择算法,O(n)
	 * @param ori
	 * @param k
	 * @param select
	 * @return
	 */
	private static ArrayList<Integer> findKMin(ArrayList<Integer> ori, int k, int select){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(select == 0){
			//使用部分排序的方法
			System.out.println("使用部分排序方法（数组实现）...");
			//将前k个数字假设为最小的k个数
			if(ori.size() < k){
				System.out.println("长度小于"+k);
				return null;
			}
			res.addAll(ori.subList(0, k));
			//找到k个数字中的最大值
			int kMax = Collections.max(res);
			//遍历剩下的n-k个数
			for(int i = k; i < res.size(); i++){
				if(ori.get(i) < kMax){
					ori.remove(kMax);
					res.add(ori.get(i));
					kMax = Collections.max(res);
				}
			}
		}
		if(select == 1){
			//使用递归形式的快速选择算法
			System.out.println("使用部分排序方法（堆实现）...");
			//TODO 实现O(n)级别的快速选择
		}
		if(select == 2){
			//使用
		}
		return res;
	}
	
	/**
	 * （2）寻找和为定值的两个数
	 * 题目描述：
	 * 	在一组整数中查找所有和为定值的两个数
	 * 思路：
	 * 	双指针的思想
	 * @param ori
	 * @param s
	 * @return
	 */
	private static ArrayList<ArrayList<Integer>> twoSum(int[] ori, int s){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		//排序
		Arrays.sort(ori);
		int begin = 0;
		int end = ori.length - 1;
		while(begin < end){
			int left = ori[begin];
			int right = ori[end];
			//判断
			if((left + right) == s){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(left);
				temp.add(right);
				res.add(temp);
				begin++;
				end--;
			}else if((left + right) < s){
				begin++;
			}else{
				end--;
			}
		}
		return res;
	}
	
	/**
	 * （3）寻找和为定值的三个数
	 * 题目描述：
	 * 	一组整数中找到所有和为定值的三个数
	 * 思路：
	 * 	类似2Sum的概念，先固定住一个，寻找两数之和等于target-num[i]的数
	 * @param ori
	 * @param s
	 * @return
	 */
	private static ArrayList<ArrayList<Integer>> threeSum(int[] ori, int s){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		//排序
		Arrays.sort(ori);
		//固定住一个，找另外的两个，将3Sum转化为2Sum
		for(int i = 0; i < ori.length - 2; i++){//排序之后固定的位置只需要到length-2即可
			//此时注意重复的问题
			if(ori[i] == ori[i+1]){
				continue;
			}
			findTwoSum(ori, i+1, ori.length-1, s-ori[i], res);
		}
		return res;
	}
	private static void findTwoSum(int[] ori, int i, int j, int k, ArrayList<ArrayList<Integer>> res) {
		/*
		 * 寻找两个数之和为k, i表示起点，j表示终点
		 */
		int begin = i;
		int end = j;
		while(begin < end){
			if((ori[begin] + ori[end]) == k){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(ori[begin]);
				temp.add(ori[end]);
				temp.add(ori[i-1]);
				res.add(temp);
				//此时会存在重复的现象，需要跳过重复的
				while((ori[begin] == ori[begin+1]) && (begin < end)){
					begin++;
				}
				while((ori[end] == ori[end-1]) && (begin < end)){
					end--;
				}
				begin++;
				end--;
			}else if((ori[begin] + ori[end]) < k){
				begin++;
			}else{
				end--;
			}
		}
	}
	
	/**
	 * （4）寻找和为定值的四个数
	 * 题目描述：
	 * 	一组整数中找到所有和为定值的四个数
	 * 思路：
	 * 	同3Sum，固定住两个将其退化为2Sum问题
	 * @param ori
	 * @param s
	 * @return
	 */
	private static ArrayList<ArrayList<Integer>> fourSum(int[] ori, int s){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		//排序
		Arrays.sort(ori);
		//固定两个
		for(int i = 0; i < ori.length - 3; i++){
			//去重
			if(ori[i] == ori[i+1]){
				continue;
			}
			for(int j = i + 1; j < ori.length - 2; j++){
				//去重
				if(ori[j] == ori[j+1]){
					continue;
				}
				findTwoSum2(ori, j+1, ori.length - 1, i, s-ori[i]-ori[j], res);
			}
		}
		return res;
	}
	private static void findTwoSum2(int[] ori, int i, int j, int first, int k, ArrayList<ArrayList<Integer>> res) {
		/**
		 * 针对4Sum的两数之和等于定值
		 */
		int begin = i;
		int end = j;
		while(begin < end){
			int left = ori[begin];
			int right = ori[end];
			if((left + right) == k){
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(left);
				temp.add(right);
				temp.add(ori[i-1]);
				temp.add(ori[first]);
				res.add(temp);
				//去重
				while((ori[begin] == ori[begin+1]) && (begin < end)){
					begin++;
				}
				while((ori[end] == ori[end-1]) && (begin < end)){
					end--;
				}
				begin++;
				end--;
			}else if((left + right) < k){
				begin++;
			}else{
				end--;
			}
		}
	}
	
	/**
	 * （5）寻找和为定值的多个数
	 * 题目描述：
	 * 	一组整数中找到和为定值的多个数
	 * 思路：
	 * 	n问题转化为n-1的问题，使用递归思想解决
	 * 	考虑第n个数是否取，将问题转化为前n-1个数和为sum-a[n-1]的问题，也可以转化为后n-1个数的求和问题
	 * @param ori
	 * @param start
	 * @param s
	 * @param res
	 * @return
	 */
	private static void findNSum(int[] ori, int start, int s, ArrayList<Integer> res) {
		//递归结束条件
		if(start >= ori.length || s <= 0){
			return;
		}
		
		if(s == ori[start] && (start < ori.length)){
			for(int t: res){
				System.out.print(t+",");
			}
			System.out.println(ori[start]); //注意此时并没有加入到res
		}
		//两种情况，加入或者不加入
		//加入
		res.add(ori[start]);
		findNSum(ori, start+1, s-ori[start], res);
		//不加入
		res.remove(res.size()-1);
		findNSum(ori, start+1, s, res);
		
	}
	
	/**
	 * （6）最大连续子数组和
	 * 题目描述：
	 * 	从整数数组中（正数，负数，0），找出其中和最大的连续子数组
	 * 思路：
	 * 	DP思想，使用两个变量当作全局最大和局部最大
	 * 	currentSum[j] = max{0, currentSum[j-1] + a[j]}
	 * 	if currentSum > maxSum:
	 * 		maxSum = currentSum
	 * @param ori
	 * @return
	 */
	private static int continuousSumSubList(int[] ori){
		int currentSum = 0;
		int maxSum = 0;
		for(int i = 0; i < ori.length; i++){
			if(currentSum >= 0){
				currentSum += ori[i];
			}else{
				currentSum = ori[i]; //相当于0-(i-1)的最大和是负数，此时丢弃前面的，直接从i开始
			}
			if(currentSum > maxSum){
				maxSum = currentSum;
			}
		}
		return maxSum;
		
	}
	
	/**
	 * （7）最大连续子数组的乘积
	 * 题目描述：
	 * 	从整数数组中（正数，负数），找出其中乘积最大的连续子数组
	 * 思路：
	 * 	(1) DP，使用当前最大和全局最大两个变量，但是注意到负数乘以负数是个正数，所以还需要记录当前最小值（因为当前最小的乘以某个数，可能会变成最大的）
	 * 	(2) 非DP，从前往后扫描一遍记录此时的全局最大max1和局部最大，从后往前扫描一遍记录此时的全局max2和局部最大，总的最大值是max{max1,max2}
	 * @param ori
	 * @param select 
	 * @return
	 */
	private static int continuousMultiSubList(int[] ori, int select){
		int allMax = 0;
		if(select == 1){
			//使用第一种DP方法
			System.out.println("使用DP方法实现...");
			int currentMax = ori[0]; //当前最大
			int currentMin = ori[0]; //当前最小
			int maxAll = ori[0]; //全局最大
			for(int i = 1; i < ori.length; i++){
				int temp1 = currentMax * ori[i];
				int temp2 = currentMin * ori[i];
				currentMax = Math.max(ori[i], Math.max(temp1, temp2)); //加入ori[i]为了包含可以0
				currentMin = Math.min(ori[i], Math.min(temp1, temp2));
				maxAll = Math.max(currentMax, currentMin);
			}
			allMax = maxAll;
		}else{
			//使用第二种方法
			System.out.println("使用非DP方法求解...");
			//从前往后遍历
			int firstCurrentMul = 1;
			int firstMaxMul = -99999;
			for(int i = 0; i < ori.length; i++){
				if((firstCurrentMul * ori[i]) > firstMaxMul){
					firstMaxMul = firstCurrentMul * ori[i];
				}
				firstCurrentMul *= ori[i];
			}
			//从后往前遍历
			int lastCurrentMul = 1;
			int lastMaxMul = -99999;
			for(int i = (ori.length - 1); i >= 0; i--){
				if((lastCurrentMul * ori[i]) > lastMaxMul){
					lastMaxMul = lastCurrentMul * ori[i];
				}
				lastCurrentMul *= ori[i];
			}
			allMax = (firstMaxMul > lastMaxMul? firstMaxMul:lastMaxMul);
		}
		return allMax;
	}
	
	/**
	 * （8）最大子矩阵和
	 * 题目描述：
	 * 	在一个m*n的矩阵中找出某个子矩阵使其合最大
	 * 思路：
	 * 	类似最大子数组的和，先把二维的矩阵转换为一维的数组，然后求最大和
	 * 	将第i-j行之间的子矩阵进行行上的加和，大小变为[j-i,n]，然后求最大和
	 * @param ori
	 * @return
	 */
	private static int maxSubMatrixSum(int[][] ori){
		int m = ori.length; //行
		int n = ori[0].length; //列
		int maxSum = Integer.MIN_VALUE;
		//选择i-j行之间的子矩阵
		for(int i = 0; i < m; i++){
			for(int j = i; j < m; j++){
				//将i-j行之间的子矩阵逐行相加
				int[] tempMatrixSum = cumLineSum(ori, i, j);
				int tempMaxSum = continuousSumSubList(tempMatrixSum);
				if(maxSum < tempMaxSum){
					maxSum = tempMaxSum;
				}
			}
		}
		return maxSum;
	}
	private static int[] cumLineSum(int[][] ori, int i, int j) {
		/**
		 * 对子矩阵进行逐行求和
		 * i--起始的行索引
		 * j--结束的行索引
		 */
		int[] res = new int[ori[0].length];
		for(int col = 0; col < ori[0].length; col++){
			for(int row = i; row <= j; row++){
				res[col] += ori[row][col];
			}
		}
		return res;
	}

	/**
	 * （9）最大连续子数组的增强版
	 * 题目描述：
	 * 	允许交换数组中任意两个数的位置，求最大子数组和
	 * 思路：
	 * 	网上未找到相关资料，暂时未解决时间复杂度为O(n)的解法
	 * @param ori
	 * @return
	 */
	private static int continuousSumSubListEnhance(int[] ori){
		return 0;
		// TODO 实现
	}
	
	/**
	 * （10）长度最短的连续子序列
	 * 题目描述：
	 * 	给定一个正整数组成的序列和一个正整数s，求出序列中长度最短的一个连续序列，要求和大于等于s
	 * 思路：
	 * 	向前加的话和会一直变大，此时采用类似抽积木的方法实现
	 * @param ori
	 * @param s
	 * @return
	 */
	private static int minLengthSubList(int[] ori, int s){
		int currentSum = 0;
		int currentLength = 0;
		int index = 0; //记录下次抽积木的起始位置
		ArrayList<Integer> list = new ArrayList<Integer>(); //记录在i位置符合和大于等于s的子序列的长度
		for(int i = 0; i < ori.length; i++){
			currentSum += ori[i];
			currentLength++;
			if(currentSum >= s){
				//看是否可以抽调一部分
				for(int j = index; j < i; j++){
					if((currentSum - ori[j]) >= s){
						currentSum -= ori[j];
						currentLength--;
					}else{
						index = j;
						list.add(currentLength);
						break;
					}
				}
			}
		}
		return Collections.min(list);
	}
	
	/**
	 * （11）跳台阶问题
	 * 题目描述：
	 * 	一个台阶总共有allSteps级，一次可以跳1级，2级，...,maxJump级，求总共有多少种跳法
	 * 思路：
	 * 	递推关系,假设一次最大可以跳3级台阶,n>3时，f(n)=f(n-1)+f(n-2)+f(n-3)
	 * @param allSteps
	 * @param maxJump
	 * @return
	 */
	private static int jumpStep(int allSteps){
		//假定最大可以跳3级台阶
		if(allSteps <= 0){
			return 0;
		}
		//1,2,3级台阶的跳法
		int[] ini = {1,2,4,0};
		//n>3时，f(n)=f(n-1)+f(n-2)+f(n-3)
		if(allSteps < 3){
			return ini[allSteps];
		}else{
			//使用递推的办法，不使用递归，递归存在太多的重复计算
			for(int i = 3; i < allSteps; i++){
				ini[3] = ini[0] + ini[1] + ini[2];
				ini[0] = ini[1];
				ini[1] = ini[2];
				ini[2] = ini[3];
			}
		}
		return ini[3];
	}
	
	/**
	 * （12）换硬币问题
	 * 题目描述：
	 * 	想兑换一定面值的零钱，有1,2,5,10四种面值，求总可能的兑换方式
	 * 思路：
	 * 	不能用四个for循环，时间复杂度太高，考虑DP的算法
	 * 	假设dp[i][j]表示使用i种硬币，兑换j元钱的总兑换数,假设Xi表示第i种硬币使用的个数,Vi表示第i中硬币的面值
	 * 	初始条件: dp[0][j]=0,dp[i][0]=1
	 * 	递推条件: dp[i][j]=dp[i-1][j]+dp[i][j-Vi]+dp[i][j-2*Vi]+...+dp[i][j-(j/Vi)*Vi]
	 * 	最终要求出:dp[m][sum],m表示面值的种类数,sum表示需要兑换的钱的数量
	 * @param coinValue
	 * @return
	 */
	private static int coinExchange(int coinValue){
		//假设是四种面值
		int[] value = {1,2,5,10};
		int m = 4;
		int[][] kinds = new int[m+1][coinValue+1];
		
		kinds[0][0] = 1;
		//递推关系
		for(int i = 1; i <= m; i++){
			for(int j = 0; j <= coinValue; j++){
				if(j == 0){
					kinds[i][j] = 1;
				}else{
					kinds[i][j] = 0;
					for(int index = 0; index <= (j/value[i-1]); index++){
						kinds[i][j] += kinds[i-1][j-index*value[i-1]];
					}
				}
			}
		}
		return kinds[m][coinValue];
	}
	
	/**
	 * （13）奇偶数排序
	 * 题目描述：
	 * 	用O(n)的时间复杂度将整数数组排序，要求奇数在前半部分，偶数在后半部分
	 * 思路：
	 * 	头尾指针，类似于2Sum问题
	 * @param ori
	 * @return
	 */
	private static void oddEvenSort(int[] ori){
		if(ori.length == 0 || ori == null){
			return;
		}
		int start = 0; 
		int end = ori.length - 1;
		while(start < end){
			if(ori[start] % 2 != 0){
				//前面是奇数
				start++;
			}else if(ori[end] % 2 == 0){
				//后面是偶数
				end--;
			}else{
				//前面是偶数，后面是奇数
				//前后交换
				int t = ori[start];
				ori[start] = ori[end];
				ori[end] = t;
			}
		}
	}
	/**
	 * 测试的主函数
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		//测试寻找最小的k个数
		ArrayList<Integer> testArr = new ArrayList<Integer>();
		testArr.add(0);
		testArr.add(100);
		testArr.add(10);
		testArr.add(110);
		testArr.add(4);
		testArr.add(100);
		testArr.add(109);
		System.out.println(Test.findKMin(testArr, 5, 0));
		//测试2Sum
		int[] testArr = {1,2,5,8,4,6};
		System.out.println(Test.twoSum(testArr, 10));
		//测试3Sum
		int[] test = {1,3,3,6,2,7,7,5,9};
		System.out.println(Test.threeSum(test, 10));
		//测试4Sum
		int[] test = {1,4,4,5,5,2,3,2,6};
		System.out.println(Test.fourSum(test, 12));
		//测试nSum
		int[] test = {1,3,2,4,6,7,5};
		Test.findNSum(test, 0, 6, new ArrayList<Integer>());
		//测试最大子数组和
		int[] test = {1,2,-3,4,5,-6,7,0,-2,4};
		System.out.println(Test.continuousSumSubList(test));
		//测试最大连续子数组的乘积
		int[] test = {2,-3,4,0,-2,-3,4};
		System.out.println(Test.continuousMultiSubList(test, 1));
		//测试最大子矩阵的和
		int[][] test = {{4,-2,9},{-1,3,8},{-6,7,6},{0,9,-5}};
		System.out.println(Test.maxSubMatrixSum(test));
		//测试长度最短的连续子序列
		int[] test = {1,2,1,3,2,3,2,3};
		System.out.println(Test.minLengthSubList(test, 6));
		//测试跳台阶问题
		System.out.println(Test.jumpStep(6));
		//测试换硬币问题
		System.out.println(Test.coinExchange(100));
		*/
		//测试奇偶排序
		int[] test = {1,3,4,6,2,8,5};
		Test.oddEvenSort(test);
		for(int t : test){
			System.out.print(t+":");
		}
	}

}
