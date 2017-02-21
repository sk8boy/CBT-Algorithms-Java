package array_exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * ------------------------------------------------------------------------------
 * ----------------------��ϰJuly�顰���֮�����еڶ������鲿��--------------------------
 * ------------------------------------------------------------------------------
 * �������¼������֣�
 * ��1��Ѱ����С��k����
 * ��2��Ѱ�Һ�Ϊ��ֵ��������
 * ��3��Ѱ�Һ�Ϊ��ֵ��������
 * ��4��Ѱ�Һ�Ϊ��ֵ���ĸ���
 * ��5��Ѱ�Һ�Ϊ��ֵ�Ķ��������2Sum,3Sum,4Sum���⣩
 * ��6����������������
 * ��7����������������˻�
 * ��8������Ӿ����
 * ��9������������������ǿ�棨������λ�ã�(��δʵ��)
 * ��10��������̵�����������
 * ��11����̨������
 * ��12����Ӳ�����⣨û�����ף�
 * ��13����ż������
 */
public class Test {
	/**
	 * ��1��Ѱ����С��k����
	 * ��Ŀ������
	 * 	��n���������ҳ�������С��k����
	 * ˼·��
	 * 	��1��������������ʵ�֣�,O(nk)
	 * 	��2���������򣨶�ʵ�֣�,O(nlogk)
	 * 	��3���ݹ���ʽ�Ŀ���ѡ���㷨,O(n)
	 * @param ori
	 * @param k
	 * @param select
	 * @return
	 */
	private static ArrayList<Integer> findKMin(ArrayList<Integer> ori, int k, int select){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(select == 0){
			//ʹ�ò�������ķ���
			System.out.println("ʹ�ò������򷽷�������ʵ�֣�...");
			//��ǰk�����ּ���Ϊ��С��k����
			if(ori.size() < k){
				System.out.println("����С��"+k);
				return null;
			}
			res.addAll(ori.subList(0, k));
			//�ҵ�k�������е����ֵ
			int kMax = Collections.max(res);
			//����ʣ�µ�n-k����
			for(int i = k; i < res.size(); i++){
				if(ori.get(i) < kMax){
					ori.remove(kMax);
					res.add(ori.get(i));
					kMax = Collections.max(res);
				}
			}
		}
		if(select == 1){
			//ʹ�õݹ���ʽ�Ŀ���ѡ���㷨
			System.out.println("ʹ�ò������򷽷�����ʵ�֣�...");
			//TODO ʵ��O(n)����Ŀ���ѡ��
		}
		if(select == 2){
			//ʹ��
		}
		return res;
	}
	
	/**
	 * ��2��Ѱ�Һ�Ϊ��ֵ��������
	 * ��Ŀ������
	 * 	��һ�������в������к�Ϊ��ֵ��������
	 * ˼·��
	 * 	˫ָ���˼��
	 * @param ori
	 * @param s
	 * @return
	 */
	private static ArrayList<ArrayList<Integer>> twoSum(int[] ori, int s){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		//����
		Arrays.sort(ori);
		int begin = 0;
		int end = ori.length - 1;
		while(begin < end){
			int left = ori[begin];
			int right = ori[end];
			//�ж�
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
	 * ��3��Ѱ�Һ�Ϊ��ֵ��������
	 * ��Ŀ������
	 * 	һ���������ҵ����к�Ϊ��ֵ��������
	 * ˼·��
	 * 	����2Sum�ĸ���ȹ̶�סһ����Ѱ������֮�͵���target-num[i]����
	 * @param ori
	 * @param s
	 * @return
	 */
	private static ArrayList<ArrayList<Integer>> threeSum(int[] ori, int s){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		//����
		Arrays.sort(ori);
		//�̶�סһ�������������������3Sumת��Ϊ2Sum
		for(int i = 0; i < ori.length - 2; i++){//����֮��̶���λ��ֻ��Ҫ��length-2����
			//��ʱע���ظ�������
			if(ori[i] == ori[i+1]){
				continue;
			}
			findTwoSum(ori, i+1, ori.length-1, s-ori[i], res);
		}
		return res;
	}
	private static void findTwoSum(int[] ori, int i, int j, int k, ArrayList<ArrayList<Integer>> res) {
		/*
		 * Ѱ��������֮��Ϊk, i��ʾ��㣬j��ʾ�յ�
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
				//��ʱ������ظ���������Ҫ�����ظ���
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
	 * ��4��Ѱ�Һ�Ϊ��ֵ���ĸ���
	 * ��Ŀ������
	 * 	һ���������ҵ����к�Ϊ��ֵ���ĸ���
	 * ˼·��
	 * 	ͬ3Sum���̶�ס���������˻�Ϊ2Sum����
	 * @param ori
	 * @param s
	 * @return
	 */
	private static ArrayList<ArrayList<Integer>> fourSum(int[] ori, int s){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		//����
		Arrays.sort(ori);
		//�̶�����
		for(int i = 0; i < ori.length - 3; i++){
			//ȥ��
			if(ori[i] == ori[i+1]){
				continue;
			}
			for(int j = i + 1; j < ori.length - 2; j++){
				//ȥ��
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
		 * ���4Sum������֮�͵��ڶ�ֵ
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
				//ȥ��
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
	 * ��5��Ѱ�Һ�Ϊ��ֵ�Ķ����
	 * ��Ŀ������
	 * 	һ���������ҵ���Ϊ��ֵ�Ķ����
	 * ˼·��
	 * 	n����ת��Ϊn-1�����⣬ʹ�õݹ�˼����
	 * 	���ǵ�n�����Ƿ�ȡ��������ת��Ϊǰn-1������Ϊsum-a[n-1]�����⣬Ҳ����ת��Ϊ��n-1�������������
	 * @param ori
	 * @param start
	 * @param s
	 * @param res
	 * @return
	 */
	private static void findNSum(int[] ori, int start, int s, ArrayList<Integer> res) {
		//�ݹ��������
		if(start >= ori.length || s <= 0){
			return;
		}
		
		if(s == ori[start] && (start < ori.length)){
			for(int t: res){
				System.out.print(t+",");
			}
			System.out.println(ori[start]); //ע���ʱ��û�м��뵽res
		}
		//���������������߲�����
		//����
		res.add(ori[start]);
		findNSum(ori, start+1, s-ori[start], res);
		//������
		res.remove(res.size()-1);
		findNSum(ori, start+1, s, res);
		
	}
	
	/**
	 * ��6����������������
	 * ��Ŀ������
	 * 	�����������У�������������0�����ҳ����к���������������
	 * ˼·��
	 * 	DP˼�룬ʹ��������������ȫ�����;ֲ����
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
				currentSum = ori[i]; //�൱��0-(i-1)�������Ǹ�������ʱ����ǰ��ģ�ֱ�Ӵ�i��ʼ
			}
			if(currentSum > maxSum){
				maxSum = currentSum;
			}
		}
		return maxSum;
		
	}
	
	/**
	 * ��7���������������ĳ˻�
	 * ��Ŀ������
	 * 	�����������У����������������ҳ����г˻���������������
	 * ˼·��
	 * 	(1) DP��ʹ�õ�ǰ����ȫ�������������������ע�⵽�������Ը����Ǹ����������Ի���Ҫ��¼��ǰ��Сֵ����Ϊ��ǰ��С�ĳ���ĳ���������ܻ������ģ�
	 * 	(2) ��DP����ǰ����ɨ��һ���¼��ʱ��ȫ�����max1�;ֲ���󣬴Ӻ���ǰɨ��һ���¼��ʱ��ȫ��max2�;ֲ�����ܵ����ֵ��max{max1,max2}
	 * @param ori
	 * @param select 
	 * @return
	 */
	private static int continuousMultiSubList(int[] ori, int select){
		int allMax = 0;
		if(select == 1){
			//ʹ�õ�һ��DP����
			System.out.println("ʹ��DP����ʵ��...");
			int currentMax = ori[0]; //��ǰ���
			int currentMin = ori[0]; //��ǰ��С
			int maxAll = ori[0]; //ȫ�����
			for(int i = 1; i < ori.length; i++){
				int temp1 = currentMax * ori[i];
				int temp2 = currentMin * ori[i];
				currentMax = Math.max(ori[i], Math.max(temp1, temp2)); //����ori[i]Ϊ�˰�������0
				currentMin = Math.min(ori[i], Math.min(temp1, temp2));
				maxAll = Math.max(currentMax, currentMin);
			}
			allMax = maxAll;
		}else{
			//ʹ�õڶ��ַ���
			System.out.println("ʹ�÷�DP�������...");
			//��ǰ�������
			int firstCurrentMul = 1;
			int firstMaxMul = -99999;
			for(int i = 0; i < ori.length; i++){
				if((firstCurrentMul * ori[i]) > firstMaxMul){
					firstMaxMul = firstCurrentMul * ori[i];
				}
				firstCurrentMul *= ori[i];
			}
			//�Ӻ���ǰ����
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
	 * ��8������Ӿ����
	 * ��Ŀ������
	 * 	��һ��m*n�ľ������ҳ�ĳ���Ӿ���ʹ������
	 * ˼·��
	 * 	�������������ĺͣ��ȰѶ�ά�ľ���ת��Ϊһά�����飬Ȼ��������
	 * 	����i-j��֮����Ӿ���������ϵļӺͣ���С��Ϊ[j-i,n]��Ȼ��������
	 * @param ori
	 * @return
	 */
	private static int maxSubMatrixSum(int[][] ori){
		int m = ori.length; //��
		int n = ori[0].length; //��
		int maxSum = Integer.MIN_VALUE;
		//ѡ��i-j��֮����Ӿ���
		for(int i = 0; i < m; i++){
			for(int j = i; j < m; j++){
				//��i-j��֮����Ӿ����������
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
		 * ���Ӿ�������������
		 * i--��ʼ��������
		 * j--������������
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
	 * ��9������������������ǿ��
	 * ��Ŀ������
	 * 	������������������������λ�ã�������������
	 * ˼·��
	 * 	����δ�ҵ�������ϣ���ʱδ���ʱ�临�Ӷ�ΪO(n)�Ľⷨ
	 * @param ori
	 * @return
	 */
	private static int continuousSumSubListEnhance(int[] ori){
		return 0;
		// TODO ʵ��
	}
	
	/**
	 * ��10��������̵�����������
	 * ��Ŀ������
	 * 	����һ����������ɵ����к�һ��������s����������г�����̵�һ���������У�Ҫ��ʹ��ڵ���s
	 * ˼·��
	 * 	��ǰ�ӵĻ��ͻ�һֱ��󣬴�ʱ�������Ƴ��ľ�ķ���ʵ��
	 * @param ori
	 * @param s
	 * @return
	 */
	private static int minLengthSubList(int[] ori, int s){
		int currentSum = 0;
		int currentLength = 0;
		int index = 0; //��¼�´γ��ľ����ʼλ��
		ArrayList<Integer> list = new ArrayList<Integer>(); //��¼��iλ�÷��Ϻʹ��ڵ���s�������еĳ���
		for(int i = 0; i < ori.length; i++){
			currentSum += ori[i];
			currentLength++;
			if(currentSum >= s){
				//���Ƿ���Գ��һ����
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
	 * ��11����̨������
	 * ��Ŀ������
	 * 	һ��̨���ܹ���allSteps����һ�ο�����1����2����...,maxJump�������ܹ��ж���������
	 * ˼·��
	 * 	���ƹ�ϵ,����һ����������3��̨��,n>3ʱ��f(n)=f(n-1)+f(n-2)+f(n-3)
	 * @param allSteps
	 * @param maxJump
	 * @return
	 */
	private static int jumpStep(int allSteps){
		//�ٶ���������3��̨��
		if(allSteps <= 0){
			return 0;
		}
		//1,2,3��̨�׵�����
		int[] ini = {1,2,4,0};
		//n>3ʱ��f(n)=f(n-1)+f(n-2)+f(n-3)
		if(allSteps < 3){
			return ini[allSteps];
		}else{
			//ʹ�õ��Ƶİ취����ʹ�õݹ飬�ݹ����̫����ظ�����
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
	 * ��12����Ӳ������
	 * ��Ŀ������
	 * 	��һ�һ����ֵ����Ǯ����1,2,5,10������ֵ�����ܿ��ܵĶһ���ʽ
	 * ˼·��
	 * 	�������ĸ�forѭ����ʱ�临�Ӷ�̫�ߣ�����DP���㷨
	 * 	����dp[i][j]��ʾʹ��i��Ӳ�ң��һ�jԪǮ���ܶһ���,����Xi��ʾ��i��Ӳ��ʹ�õĸ���,Vi��ʾ��i��Ӳ�ҵ���ֵ
	 * 	��ʼ����: dp[0][j]=0,dp[i][0]=1
	 * 	��������: dp[i][j]=dp[i-1][j]+dp[i][j-Vi]+dp[i][j-2*Vi]+...+dp[i][j-(j/Vi)*Vi]
	 * 	����Ҫ���:dp[m][sum],m��ʾ��ֵ��������,sum��ʾ��Ҫ�һ���Ǯ������
	 * @param coinValue
	 * @return
	 */
	private static int coinExchange(int coinValue){
		//������������ֵ
		int[] value = {1,2,5,10};
		int m = 4;
		int[][] kinds = new int[m+1][coinValue+1];
		
		kinds[0][0] = 1;
		//���ƹ�ϵ
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
	 * ��13����ż������
	 * ��Ŀ������
	 * 	��O(n)��ʱ�临�ӶȽ�������������Ҫ��������ǰ�벿�֣�ż���ں�벿��
	 * ˼·��
	 * 	ͷβָ�룬������2Sum����
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
				//ǰ��������
				start++;
			}else if(ori[end] % 2 == 0){
				//������ż��
				end--;
			}else{
				//ǰ����ż��������������
				//ǰ�󽻻�
				int t = ori[start];
				ori[start] = ori[end];
				ori[end] = t;
			}
		}
	}
	/**
	 * ���Ե�������
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		//����Ѱ����С��k����
		ArrayList<Integer> testArr = new ArrayList<Integer>();
		testArr.add(0);
		testArr.add(100);
		testArr.add(10);
		testArr.add(110);
		testArr.add(4);
		testArr.add(100);
		testArr.add(109);
		System.out.println(Test.findKMin(testArr, 5, 0));
		//����2Sum
		int[] testArr = {1,2,5,8,4,6};
		System.out.println(Test.twoSum(testArr, 10));
		//����3Sum
		int[] test = {1,3,3,6,2,7,7,5,9};
		System.out.println(Test.threeSum(test, 10));
		//����4Sum
		int[] test = {1,4,4,5,5,2,3,2,6};
		System.out.println(Test.fourSum(test, 12));
		//����nSum
		int[] test = {1,3,2,4,6,7,5};
		Test.findNSum(test, 0, 6, new ArrayList<Integer>());
		//��������������
		int[] test = {1,2,-3,4,5,-6,7,0,-2,4};
		System.out.println(Test.continuousSumSubList(test));
		//�����������������ĳ˻�
		int[] test = {2,-3,4,0,-2,-3,4};
		System.out.println(Test.continuousMultiSubList(test, 1));
		//��������Ӿ���ĺ�
		int[][] test = {{4,-2,9},{-1,3,8},{-6,7,6},{0,9,-5}};
		System.out.println(Test.maxSubMatrixSum(test));
		//���Գ�����̵�����������
		int[] test = {1,2,1,3,2,3,2,3};
		System.out.println(Test.minLengthSubList(test, 6));
		//������̨������
		System.out.println(Test.jumpStep(6));
		//���Ի�Ӳ������
		System.out.println(Test.coinExchange(100));
		*/
		//������ż����
		int[] test = {1,3,4,6,2,8,5};
		Test.oddEvenSort(test);
		for(int t : test){
			System.out.print(t+":");
		}
	}

}
