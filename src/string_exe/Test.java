package string_exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * ------------------------------------------------------------------------------
 * ----------------------练习July书“编程之法”中第一章字符串部分--------------------------
 * ------------------------------------------------------------------------------
 * 包含如下几个部分：
 * （1）字符串的旋转
 * （2）单词反转
 * （3）字符串的包含（3种方法）
 * （4）字符串的变位词
 * （5）字符串的全排列
 * （6）字符串中所有字符的组合
 * （7）回文判段
 * （8）单链表的就地反置
 * （9）单链表的回文判断
 * （10）最长回文子串
 * （11）输出最长回文子串
 */
public class Test {
	/**
	 * （1）字符串的反转
	 * 题目描述：
	 * 	将"abcdef"旋转为"defabc"
	 * 思路：
	 * 	使用三部反转法即可
	 * @param ori
	 * @param index
	 * @return
	 */
	private static String stringRotate(String ori, int index){
		//将ori分成两部分
		String front = ori.substring(0, index);
		String back = ori.substring(index, ori.length());
		//三步反转
		String frontR = new StringBuilder(front).reverse().toString();
		String backR = new StringBuilder(back).reverse().toString();
		StringBuilder all = new StringBuilder();
		all.append(frontR);
		all.append(backR);
		return all.reverse().toString();
		
	}
	
	/**
	 * （2）字符串反转的变形，单词反转
	 * 题目描述：
	 * 	反转英文句子，句子以空格分开，标点和普通字母一样处理，比如:"i am student. hi"反转为"hi student. am i"
	 * 思路：
	 * 	和（1）相反，先反转大的再分开反转小的
	 * @param words
	 * @return
	 */
	
	private static String wordsRotate(String words){
		//反转总的words
		StringBuilder wordsR = new StringBuilder(words).reverse();
		//分割，然后反转每个单词
		String[] rWords = wordsR.toString().split(" ");
		String res = "";
		for(String s : rWords){
			String sR = new StringBuilder(s).reverse().toString();
			res += sR;
			res += " ";
		}
		return res.substring(0, res.length()-1);
		
	}
	
	/**
	 * （3）字符串的包含
	 * 题目描述：
	 * 	测试短字符串b是否包含在长字符串a中
	 * 思路：
	 * 	（1）先排序后轮询，O(mlgm + nlgn)
	 * 	（2）位运算法，O(m + n)
	 * 	（3）Hash Table，O(m + n)
	 * @param longStr
	 * @param shortStr
	 * @param choice 0表示使用Hash Table方法，1表示使用位运算法，2表示使用先排序后轮询的方法
	 * @return
	 */
	private static boolean stringContain(String longStr, String shortStr, int choice){
		boolean res = true;
		if(choice == 0){
			//hash table方法，以空间换时间
			System.out.println("使用Hash Table实现...");
			HashMap<String, Integer> longMap = new HashMap<String, Integer>();
			for(int i = 0; i < longStr.length(); i++){
				String temp = longStr.substring(i, i+1);
				if(!longMap.keySet().contains(temp)){
					longMap.put(temp, 1);
				}else{
					longMap.put(temp, longMap.get(temp)+1);					
				}
			}
			//检查短的字符串是否在hash table里面
			for(int j = 0; j < shortStr.length(); j++){
				String shortIndex = shortStr.substring(j, j+1);
				if(!longMap.keySet().contains(shortIndex)){
					res = false;
					break;
				}
			}
		}
		else if(choice == 1){
			//使用位运算法
			System.out.println("使用位运算法实现...");
			int hash = 0;
			//计算长的str的hash值
			for(int i = 0; i < longStr.length(); i++){
				char temp = longStr.charAt(i);
				hash |= (1 << (int)temp - (int)'A');
			}
			//判断短的str是否在长的里面
			for(int j = 0; j < shortStr.length(); j++){
				char t = shortStr.charAt(j);
				if((hash & (1 << (int)t - (int)'A')) == 0){
					res = false;
					break;
				}
			}
		}
		else{
			//使用先排序后查询的方式实现
			System.out.println("使用先排序后查询方法实现...");
			//排序
			char[] longArr = longStr.toCharArray();
			char[] shortArr = shortStr.toCharArray();
			Arrays.sort(longArr);
			Arrays.sort(shortArr);
			//查找
			for(int i = 0, j = 0; i < shortArr.length;){
				while((j < longArr.length) && (longArr[j] < shortArr[i])){
					j++;
				}
				if((j >= longArr.length) || (longArr[j] > shortArr[i])){
					res = false;
				}
				i++;
			}
		}
		return res;
		
	}
	
	/**
	 * （4）变位词
	 * 题目描述：
	 * 	两个字符串中的字符一样但是位置不一样，则互为变位词
	 * 思路：
	 * 	不能使用全排列，否则对于长度很大的字符串运行时间过久；
	 * 	使用签名的思想，类似于（3）计算hash值
	 * @param word
	 * @param comp
	 * @return
	 */
	private static boolean positionChangedWord(String word, String comp){
		//计算word的hash值
		int wordHash = 0;
		for(int i = 0; i < word.length(); i++){
			char temp = word.charAt(i);
			wordHash |= (1 << ((int)temp - (int)'A'));
		}
		//计算comp的hash值
		int wordComp = 0;
		for(int j = 0 ; j < comp.length(); j++){
			char tempComp = comp.charAt(j);
			wordComp |= (1 << ((int)(tempComp) - (int)'A'));
		}
		return wordHash == wordComp? true:false;
		
	}
	
	/**
	 * （5）字符串的全排列
	 * 题目描述:
	 * 	字符串的全排列（abc,acb,bac,...)
	 * 思路：
	 * 	使用递归的方法
	 * @param ori
	 * @param start
	 * @param to
	 */
	private static void permutation(char[] ori, int start, int to){
		if(to <= 1){
			return;
		}
		if(start == to){
			System.out.println(ori);
		}
		else{
			for(int j = start; j <= to; j++){
				//交换
				char temp = ori[j];
				ori[j] = ori[start];
				ori[start] = temp;
				//递归
				permutation(ori, start+1, to);
				//交换
				char temp1 = ori[j];
				ori[j] = ori[start];
				ori[start] = temp1;
			}
		}
	}
	
	/**
	 * （6）字符串中所有字符的组合
	 * 题目描述：
	 * 	求一个字符串中所有字符的组合，比如对"abc"来说，所有的组合是"a","b","c","ab","ac",'bc","abc"
	 * 思路：
	 * 	使用递归的方法
	 * 	针对一个字符，有两种情况，假设在长度为n的字符串中选择长度为m的组合字符串
	 * 	第一是选择长度为n的字符串中的第一个字符，那么要在其余的长度n-1的字符串中选择m-1个字符
	 * 	第二是不选择长度为n的字符串中的第一个字符，那么要在其余的长度n-1的字符串中选择m个字符
	 * 	递归结束的条件就是，当m为0，即从字符串中不再选出字符的时候，这个时候已经找到了m个字符的组合
	 * @param ori
	 * @return
	 */
	private static void combination(String ori){
		ArrayList<String> res = new ArrayList<String>();
		//针对总长度为l的字符串，可以选择长度为1,2,3,...,l
		for(int i = 1; i <= ori.length(); i++){
			combAss(ori, i, res);
		}
	}
	
	private static void combAss(String ori, int i, ArrayList<String> res) {
		/**
		 * 字符串中所有字符组合的辅助函数
		 * 从字符串ori选择长度为i的字符
		 */
		//递归结束，i=0
		if(i == 0){
			for(String s: res){
				System.out.print(s);
			}
			System.out.println();
			return;
		}
		if(ori.length() != 0){
			//字符串的第一个字符被选择
			res.add(ori.charAt(0)+"");
			combAss(ori.substring(1, ori.length()), i-1, res);
			res.remove(res.size()-1);
			//字符串的第一个字符不被选择
			combAss(ori.substring(1,  ori.length()), i, res);
		}
		
	}
	
	/**
	 * （7）回文判断
	 * 题目描述：
	 * 	判断一個字符串是否是回文串
	 * 思路：
	 * 	使用两个指针，夹逼形式判断
	 * @param ori
	 * @return
	 */
	private static boolean palindrome(String ori){
		int front = 0;
		int back = ori.length() - 1;
		while(front < back){
			//判断头尾指针所指的字符是否相等
			if(ori.charAt(front) != ori.charAt(back)){
				return false;
			}
			front++;
			back--;
		}
		return true;
		
	}
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
		}
	}
	
	/**
	 * （8）单链表就地反置
	 * 题目描述：
	 * 	将一个单链表就地O(1)进行逆置
	 * 思路：
	 * 	（1）头插法
	 * 	（2）相邻指针法
	 * @param head
	 * @return
	 */
	private static ListNode reverseList(ListNode head){
		//相邻指针实现
		if(head == null || head.next == null){
			return head;
		}
		ListNode p= head;
		ListNode q = p.next;
		ListNode r;
		//逆序
		while(q != null){
			r = q.next;
			q.next = p;
			p = q;
			q = r;
		}
		//处理头指针
		head.next = null;
		head = p;
		return head;
		
	}
	/**
	 * （9）单链表的回文判断
	 * 题目描述：
	 * 	判断一条单链表是不是回文
	 * 思路：
	 * 	利用快慢指针法找到链表中点，然后将后半部分就地反转，分别再从头、中点遍历判断是否相等
	 * @param ori
	 * @return
	 */
	private static boolean linkedListPalindrome(ListNode head){
		if(head == null || head.next == null){
            return true;
        }
		//使用快慢指针的方法找到链表的中点
		ListNode middle = findMiddle(head);
		//将后半部分的链表反置
		middle.next = reverseList(middle.next);
		//从头和中点进行遍历
		ListNode front = head;
		ListNode back = middle.next;
		while(front != null && back != null && front.val == back.val){
			front = front.next;
			back = back.next;
		}
		return back == null;
		
	}
	private static ListNode findMiddle(ListNode head) {
		if(head == null || head.next == null){
			return head;
		}
		ListNode middle = null;
		ListNode p = head; //慢指针
		ListNode q = head.next; //快指针
		while(q.next != null && q.next.next != null){
			p = p.next;
			q = q.next.next;
		}
		//需要判断此时链表是奇数还是偶数
		if(q.next == null){
			//偶数
			middle = p;
		}else{
			//奇数
			middle = p.next;
		}
		return middle;
	}
	
	/**
	 * （10）最长回文子串
	 * 题目描述：
	 * 	给定一个字符串，求最长回文子串的长度
	 * 思路：
	 * 	中心扩展法，枚举回文串的中心位置
	 * @param ori
	 * @return
	 */
	private static int longestPalindromeStr(String ori){
		if(ori == null || ori.length() == 0){
			return 0;
		}
		int max = 0; //最长回文子串的长度
		int maxTemp = 0;
		for(int i = 0; i < ori.length(); i++){
			//i表示回文串的中心位置
			//回文串有两种，奇数和偶数
			//奇数的情况
			for(int j = 0;(((i+j)<ori.length()) && (i-j>=0)) ;j++){
				//j表示一半回文串的长度
				if(ori.charAt(i-j) != ori.charAt(i+j)){
					break;
				}
				maxTemp = 2 * j + 1;
			}
			if(maxTemp > max){
				max = maxTemp;
			}
			//偶数的情况
			for(int j = 0; ((i-j>=0) && ((i+j+1)<ori.length())); j++){
				if(ori.charAt(i-j) != ori.charAt(i+1+j)){
					break;
				}
				maxTemp = 2 * j + 2;
			}
			if(maxTemp > max){
				max = maxTemp;
			}
		}
		return max;
		
	}
	
	/**
	 * （11）输出最长回文子串
	 * 题目描述：
	 * 	将一个字符串切割成一段一段的子串，要求子串都是回文串，如果是回文串输出最长的回文串，否则将字符单个输出
	 * 思路：
	 * 	递归的方法实现
	 * 	找原字符串的最长回文子串，将原来的字符串分为三个部分（A,B,C)，分别对A和C递归查找
	 * 	递归结束条件是某部分不存在回文子串，则单个字符输出
	 * @param ori
	 * @return
	 */
	private static void printLongestPalidrome(String ori){
		if(ori.length() == 0 || ori == null){
			return;
		}
		ArrayList<Integer> index = longestPalidomIndex(ori);
		int longestIndex = index.get(0);
		int longestLength = index.get(1);
		//递归结束条件
		if(longestIndex == 0 && longestLength == 1){
			//没有回文字串,逐个字符打印
			for(int i = 0; i < ori.length();i++){
				System.out.print(ori.charAt(i) + ",");
			}
			return;
		}
		//找到回文字串
		int leftIndex = 0;
		int rightIndex = 0;
		if(longestLength % 2 == 0){
			//偶数
			leftIndex = longestIndex - ((longestLength - 2) / 2);
			rightIndex = longestIndex + ((longestLength - 2) / 2) + 1;
		}else{
			//奇数
			leftIndex = longestIndex - ((longestLength - 1) / 2);
			rightIndex = longestIndex + ((longestLength - 1) / 2);
		}
		System.out.print(ori.substring(leftIndex, rightIndex+1) + ",");
		//递归
		printLongestPalidrome(ori.substring(0, leftIndex));
		printLongestPalidrome(ori.substring(rightIndex+1, ori.length()));
	}
	private static ArrayList<Integer> longestPalidomIndex(String ori) {
		/**
		 * 找到最长回文字串的中心位置和长度
		 * res[0]-->最长回文字串的中心位置(0开始)
		 * res[1]-->最长回文字串的长度
		 */
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(ori == null || ori.length() == 0){
			return null;
		}
		int max = 0; //最长回文子串的长度
		int maxTemp = 0;
		int index = 0; //回文字串的中心位置
		for(int i = 0; i < ori.length(); i++){
			//i表示回文串的中心位置
			//回文串有两种，奇数和偶数
			//奇数的情况
			for(int j = 0;(((i+j)<ori.length()) && (i-j>=0)) ;j++){
				//j表示一半回文串的长度
				if(ori.charAt(i-j) != ori.charAt(i+j)){
					break;
				}
				maxTemp = 2 * j + 1;
			}
			if(maxTemp > max){
				index = i;
				max = maxTemp;
				res.clear();
				res.add(i);
				res.add(max);
			}
			//偶数的情况
			for(int j = 0; ((i-j>=0) && ((i+j+1)<ori.length())); j++){
				if(ori.charAt(i-j) != ori.charAt(i+1+j)){
					break;
				}
				maxTemp = 2 * j + 2;
			}
			if(maxTemp > max){
				index = i;
				max = maxTemp;
				res.clear();
				res.add(i);
				res.add(max);
			}
		}
		return res;
	}

	/**
	 * 测试主函数
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		//测试字符串反转
		System.out.println(Test.stringRotate("abcdef", 3));
		//测试单词反转
		System.out.println(Test.wordsRotate("i am student. hi"));
		//测试字符串包含
		System.out.println(Test.stringContain("ABCDE", "EF", 2));
		//测试变位词
		System.out.println(Test.positionChangedWord("abcdg", "bdgac"));
		//测试全排列
		Test.permutation("abcd".toCharArray(), 0, 3);
		//测试字符串中所有字符的组合
		Test.combination("abcd");
		//测试回文判断
		System.out.println(Test.palindrome("acbbca"));
		//测试最长回文子串
		System.out.println(Test.longestPalidomIndex("aaf"));
		**/
		//测试输出最长回文子串
		Test.printLongestPalidrome("hhabbahffghh");
	}

}
