package string_exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
 * ------------------------------------------------------------------------------
 * ----------------------��ϰJuly�顰���֮�����е�һ���ַ�������--------------------------
 * ------------------------------------------------------------------------------
 * �������¼������֣�
 * ��1���ַ�������ת
 * ��2�����ʷ�ת
 * ��3���ַ����İ�����3�ַ�����
 * ��4���ַ����ı�λ��
 * ��5���ַ�����ȫ����
 * ��6���ַ����������ַ������
 * ��7�������ж�
 * ��8��������ľ͵ط���
 * ��9��������Ļ����ж�
 * ��10��������Ӵ�
 * ��11�����������Ӵ�
 */
public class Test {
	/**
	 * ��1���ַ����ķ�ת
	 * ��Ŀ������
	 * 	��"abcdef"��תΪ"defabc"
	 * ˼·��
	 * 	ʹ��������ת������
	 * @param ori
	 * @param index
	 * @return
	 */
	private static String stringRotate(String ori, int index){
		//��ori�ֳ�������
		String front = ori.substring(0, index);
		String back = ori.substring(index, ori.length());
		//������ת
		String frontR = new StringBuilder(front).reverse().toString();
		String backR = new StringBuilder(back).reverse().toString();
		StringBuilder all = new StringBuilder();
		all.append(frontR);
		all.append(backR);
		return all.reverse().toString();
		
	}
	
	/**
	 * ��2���ַ�����ת�ı��Σ����ʷ�ת
	 * ��Ŀ������
	 * 	��תӢ�ľ��ӣ������Կո�ֿ���������ͨ��ĸһ����������:"i am student. hi"��תΪ"hi student. am i"
	 * ˼·��
	 * 	�ͣ�1���෴���ȷ�ת����ٷֿ���תС��
	 * @param words
	 * @return
	 */
	
	private static String wordsRotate(String words){
		//��ת�ܵ�words
		StringBuilder wordsR = new StringBuilder(words).reverse();
		//�ָȻ��תÿ������
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
	 * ��3���ַ����İ���
	 * ��Ŀ������
	 * 	���Զ��ַ���b�Ƿ�����ڳ��ַ���a��
	 * ˼·��
	 * 	��1�����������ѯ��O(mlgm + nlgn)
	 * 	��2��λ���㷨��O(m + n)
	 * 	��3��Hash Table��O(m + n)
	 * @param longStr
	 * @param shortStr
	 * @param choice 0��ʾʹ��Hash Table������1��ʾʹ��λ���㷨��2��ʾʹ�����������ѯ�ķ���
	 * @return
	 */
	private static boolean stringContain(String longStr, String shortStr, int choice){
		boolean res = true;
		if(choice == 0){
			//hash table�������Կռ任ʱ��
			System.out.println("ʹ��Hash Tableʵ��...");
			HashMap<String, Integer> longMap = new HashMap<String, Integer>();
			for(int i = 0; i < longStr.length(); i++){
				String temp = longStr.substring(i, i+1);
				if(!longMap.keySet().contains(temp)){
					longMap.put(temp, 1);
				}else{
					longMap.put(temp, longMap.get(temp)+1);					
				}
			}
			//���̵��ַ����Ƿ���hash table����
			for(int j = 0; j < shortStr.length(); j++){
				String shortIndex = shortStr.substring(j, j+1);
				if(!longMap.keySet().contains(shortIndex)){
					res = false;
					break;
				}
			}
		}
		else if(choice == 1){
			//ʹ��λ���㷨
			System.out.println("ʹ��λ���㷨ʵ��...");
			int hash = 0;
			//���㳤��str��hashֵ
			for(int i = 0; i < longStr.length(); i++){
				char temp = longStr.charAt(i);
				hash |= (1 << (int)temp - (int)'A');
			}
			//�ж϶̵�str�Ƿ��ڳ�������
			for(int j = 0; j < shortStr.length(); j++){
				char t = shortStr.charAt(j);
				if((hash & (1 << (int)t - (int)'A')) == 0){
					res = false;
					break;
				}
			}
		}
		else{
			//ʹ����������ѯ�ķ�ʽʵ��
			System.out.println("ʹ����������ѯ����ʵ��...");
			//����
			char[] longArr = longStr.toCharArray();
			char[] shortArr = shortStr.toCharArray();
			Arrays.sort(longArr);
			Arrays.sort(shortArr);
			//����
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
	 * ��4����λ��
	 * ��Ŀ������
	 * 	�����ַ����е��ַ�һ������λ�ò�һ������Ϊ��λ��
	 * ˼·��
	 * 	����ʹ��ȫ���У�������ڳ��Ⱥܴ���ַ�������ʱ����ã�
	 * 	ʹ��ǩ����˼�룬�����ڣ�3������hashֵ
	 * @param word
	 * @param comp
	 * @return
	 */
	private static boolean positionChangedWord(String word, String comp){
		//����word��hashֵ
		int wordHash = 0;
		for(int i = 0; i < word.length(); i++){
			char temp = word.charAt(i);
			wordHash |= (1 << ((int)temp - (int)'A'));
		}
		//����comp��hashֵ
		int wordComp = 0;
		for(int j = 0 ; j < comp.length(); j++){
			char tempComp = comp.charAt(j);
			wordComp |= (1 << ((int)(tempComp) - (int)'A'));
		}
		return wordHash == wordComp? true:false;
		
	}
	
	/**
	 * ��5���ַ�����ȫ����
	 * ��Ŀ����:
	 * 	�ַ�����ȫ���У�abc,acb,bac,...)
	 * ˼·��
	 * 	ʹ�õݹ�ķ���
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
				//����
				char temp = ori[j];
				ori[j] = ori[start];
				ori[start] = temp;
				//�ݹ�
				permutation(ori, start+1, to);
				//����
				char temp1 = ori[j];
				ori[j] = ori[start];
				ori[start] = temp1;
			}
		}
	}
	
	/**
	 * ��6���ַ����������ַ������
	 * ��Ŀ������
	 * 	��һ���ַ����������ַ�����ϣ������"abc"��˵�����е������"a","b","c","ab","ac",'bc","abc"
	 * ˼·��
	 * 	ʹ�õݹ�ķ���
	 * 	���һ���ַ�������������������ڳ���Ϊn���ַ�����ѡ�񳤶�Ϊm������ַ���
	 * 	��һ��ѡ�񳤶�Ϊn���ַ����еĵ�һ���ַ�����ôҪ������ĳ���n-1���ַ�����ѡ��m-1���ַ�
	 * 	�ڶ��ǲ�ѡ�񳤶�Ϊn���ַ����еĵ�һ���ַ�����ôҪ������ĳ���n-1���ַ�����ѡ��m���ַ�
	 * 	�ݹ�������������ǣ���mΪ0�������ַ����в���ѡ���ַ���ʱ�����ʱ���Ѿ��ҵ���m���ַ������
	 * @param ori
	 * @return
	 */
	private static void combination(String ori){
		ArrayList<String> res = new ArrayList<String>();
		//����ܳ���Ϊl���ַ���������ѡ�񳤶�Ϊ1,2,3,...,l
		for(int i = 1; i <= ori.length(); i++){
			combAss(ori, i, res);
		}
	}
	
	private static void combAss(String ori, int i, ArrayList<String> res) {
		/**
		 * �ַ����������ַ���ϵĸ�������
		 * ���ַ���oriѡ�񳤶�Ϊi���ַ�
		 */
		//�ݹ������i=0
		if(i == 0){
			for(String s: res){
				System.out.print(s);
			}
			System.out.println();
			return;
		}
		if(ori.length() != 0){
			//�ַ����ĵ�һ���ַ���ѡ��
			res.add(ori.charAt(0)+"");
			combAss(ori.substring(1, ori.length()), i-1, res);
			res.remove(res.size()-1);
			//�ַ����ĵ�һ���ַ�����ѡ��
			combAss(ori.substring(1,  ori.length()), i, res);
		}
		
	}
	
	/**
	 * ��7�������ж�
	 * ��Ŀ������
	 * 	�ж�һ���ַ����Ƿ��ǻ��Ĵ�
	 * ˼·��
	 * 	ʹ������ָ�룬�б���ʽ�ж�
	 * @param ori
	 * @return
	 */
	private static boolean palindrome(String ori){
		int front = 0;
		int back = ori.length() - 1;
		while(front < back){
			//�ж�ͷβָ����ָ���ַ��Ƿ����
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
	 * ��8��������͵ط���
	 * ��Ŀ������
	 * 	��һ��������͵�O(1)��������
	 * ˼·��
	 * 	��1��ͷ�巨
	 * 	��2������ָ�뷨
	 * @param head
	 * @return
	 */
	private static ListNode reverseList(ListNode head){
		//����ָ��ʵ��
		if(head == null || head.next == null){
			return head;
		}
		ListNode p= head;
		ListNode q = p.next;
		ListNode r;
		//����
		while(q != null){
			r = q.next;
			q.next = p;
			p = q;
			q = r;
		}
		//����ͷָ��
		head.next = null;
		head = p;
		return head;
		
	}
	/**
	 * ��9��������Ļ����ж�
	 * ��Ŀ������
	 * 	�ж�һ���������ǲ��ǻ���
	 * ˼·��
	 * 	���ÿ���ָ�뷨�ҵ������е㣬Ȼ�󽫺�벿�־͵ط�ת���ֱ��ٴ�ͷ���е�����ж��Ƿ����
	 * @param ori
	 * @return
	 */
	private static boolean linkedListPalindrome(ListNode head){
		if(head == null || head.next == null){
            return true;
        }
		//ʹ�ÿ���ָ��ķ����ҵ�������е�
		ListNode middle = findMiddle(head);
		//����벿�ֵ�������
		middle.next = reverseList(middle.next);
		//��ͷ���е���б���
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
		ListNode p = head; //��ָ��
		ListNode q = head.next; //��ָ��
		while(q.next != null && q.next.next != null){
			p = p.next;
			q = q.next.next;
		}
		//��Ҫ�жϴ�ʱ��������������ż��
		if(q.next == null){
			//ż��
			middle = p;
		}else{
			//����
			middle = p.next;
		}
		return middle;
	}
	
	/**
	 * ��10��������Ӵ�
	 * ��Ŀ������
	 * 	����һ���ַ�������������Ӵ��ĳ���
	 * ˼·��
	 * 	������չ����ö�ٻ��Ĵ�������λ��
	 * @param ori
	 * @return
	 */
	private static int longestPalindromeStr(String ori){
		if(ori == null || ori.length() == 0){
			return 0;
		}
		int max = 0; //������Ӵ��ĳ���
		int maxTemp = 0;
		for(int i = 0; i < ori.length(); i++){
			//i��ʾ���Ĵ�������λ��
			//���Ĵ������֣�������ż��
			//���������
			for(int j = 0;(((i+j)<ori.length()) && (i-j>=0)) ;j++){
				//j��ʾһ����Ĵ��ĳ���
				if(ori.charAt(i-j) != ori.charAt(i+j)){
					break;
				}
				maxTemp = 2 * j + 1;
			}
			if(maxTemp > max){
				max = maxTemp;
			}
			//ż�������
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
	 * ��11�����������Ӵ�
	 * ��Ŀ������
	 * 	��һ���ַ����и��һ��һ�ε��Ӵ���Ҫ���Ӵ����ǻ��Ĵ�������ǻ��Ĵ������Ļ��Ĵ��������ַ��������
	 * ˼·��
	 * 	�ݹ�ķ���ʵ��
	 * 	��ԭ�ַ�����������Ӵ�����ԭ�����ַ�����Ϊ�������֣�A,B,C)���ֱ��A��C�ݹ����
	 * 	�ݹ����������ĳ���ֲ����ڻ����Ӵ����򵥸��ַ����
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
		//�ݹ��������
		if(longestIndex == 0 && longestLength == 1){
			//û�л����ִ�,����ַ���ӡ
			for(int i = 0; i < ori.length();i++){
				System.out.print(ori.charAt(i) + ",");
			}
			return;
		}
		//�ҵ������ִ�
		int leftIndex = 0;
		int rightIndex = 0;
		if(longestLength % 2 == 0){
			//ż��
			leftIndex = longestIndex - ((longestLength - 2) / 2);
			rightIndex = longestIndex + ((longestLength - 2) / 2) + 1;
		}else{
			//����
			leftIndex = longestIndex - ((longestLength - 1) / 2);
			rightIndex = longestIndex + ((longestLength - 1) / 2);
		}
		System.out.print(ori.substring(leftIndex, rightIndex+1) + ",");
		//�ݹ�
		printLongestPalidrome(ori.substring(0, leftIndex));
		printLongestPalidrome(ori.substring(rightIndex+1, ori.length()));
	}
	private static ArrayList<Integer> longestPalidomIndex(String ori) {
		/**
		 * �ҵ�������ִ�������λ�úͳ���
		 * res[0]-->������ִ�������λ��(0��ʼ)
		 * res[1]-->������ִ��ĳ���
		 */
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(ori == null || ori.length() == 0){
			return null;
		}
		int max = 0; //������Ӵ��ĳ���
		int maxTemp = 0;
		int index = 0; //�����ִ�������λ��
		for(int i = 0; i < ori.length(); i++){
			//i��ʾ���Ĵ�������λ��
			//���Ĵ������֣�������ż��
			//���������
			for(int j = 0;(((i+j)<ori.length()) && (i-j>=0)) ;j++){
				//j��ʾһ����Ĵ��ĳ���
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
			//ż�������
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
	 * ����������
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		//�����ַ�����ת
		System.out.println(Test.stringRotate("abcdef", 3));
		//���Ե��ʷ�ת
		System.out.println(Test.wordsRotate("i am student. hi"));
		//�����ַ�������
		System.out.println(Test.stringContain("ABCDE", "EF", 2));
		//���Ա�λ��
		System.out.println(Test.positionChangedWord("abcdg", "bdgac"));
		//����ȫ����
		Test.permutation("abcd".toCharArray(), 0, 3);
		//�����ַ����������ַ������
		Test.combination("abcd");
		//���Ի����ж�
		System.out.println(Test.palindrome("acbbca"));
		//����������Ӵ�
		System.out.println(Test.longestPalidomIndex("aaf"));
		**/
		//�������������Ӵ�
		Test.printLongestPalidrome("hhabbahffghh");
	}

}
