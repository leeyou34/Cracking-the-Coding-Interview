/*
  문제:
  
  하나빼기: 문자열을 편집하는 방법에는 세 가지 종류가 있다. 문자 삽입, 문자 삭제, 문자 교체이다. 
            문자열 두개가 주어졌을 때, 문자열을 같게 만들기 위한 편집 횟수가 1회 이내인지 확인하는 함수를 작성하라.

*/

import java.util.Scanner;

public class oneEditAway {
	
	static boolean oneEditAway(String first, String second) {
		if(first.length() == second.length()) {
			return oneEditReplace(first, second);
		} else if (first.length() + 1 == second.length()) {
			return oneEditInsert(first, second);
		} else if(first.length() - 1 == second.length()) {
			return oneEditInsert(second, first);
		}
		return false;
	}
	
	static boolean oneEditReplace(String s1, String s2) {
		boolean foundDifference = false;
		for (int i =0; i < s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				if(foundDifference) {
					return false;
				}
				foundDifference = true;
			}
		}
		return true;
	}
	
	/* s1에 문자 하나를 삽입해서 s2를 만들 수 있는지 확인 */
	static boolean oneEditInsert(String s1, String s2) {
		int index1 = 0;
		int index2 = 0;
		while (index2 <s2.length() && index1 < s1.length()) {
			if(s1.charAt(index1) != s2.charAt(index2)) {
				if(index1 != index2) {
					return false;
				}
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			String str1 = sc.nextLine();
			String str2 = sc.nextLine();
		
			long startTime1 = System.nanoTime();
			boolean result1 = oneEditAway(str1, str2);
			long endTime1 = System.nanoTime();
			long executionTime1 = endTime1 - startTime1;
			System.out.println("oneEditAway: " + result1);
			System.out.println("Execution Time: " + executionTime1);
		
			if(str1.contentEquals("exit") || str2.contentEquals("exit")) {
				System.out.println("exit을 입력하셨음으로 프로그램을 종료합니다.");
				break;
			}
		}
	}
}
