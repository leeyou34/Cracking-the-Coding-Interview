/*
문제:
URLIfy: 문자열에 들어 있는 모든 공백을 '%20'으로 바꾸는 메서드를 작성하라. 최종적으로 모든 문자를 다 담을 수 있을 만큼 충분한 공간이 이미 확보 되어 있으며 문자열의 최종 길이가 함께 주어진다고 가정해도 된다(자바로 구현한다면 배열 안에서 작업할 수 있도록 문자 배열(character array)을 이용하라).

예제
입력: "Mr John Smith", 13
출력: "Mr%20John%20Smith"
*/

package crackingthecode;

import java.util.Scanner;

public class URLify {
	public static void replaceSpaces(char[] str, int trueLength) {
		int spaceCount = 0;
		int index;
		
		for(index =0; index <trueLength; index++) {
			if(str[index] == ' ') {
				spaceCount++;
			}
		}
		
		int newIndex = trueLength + spaceCount * 2;
		if(trueLength < str.length) {
			str[trueLength] ='\0'; // 문자열 끝 표시
		}
		for (index = trueLength -1; index >=0; index--) {
			if(str[index] == ' ') {
				str[newIndex - 1] ='0';
				str[newIndex - 2] ='2';
				str[newIndex - 3] ='%';
				newIndex -= 3;
			} else {
				str[newIndex -1] = str[index];
				newIndex--;
			}
		}
	}
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("문자열을 입력하세요: ");
		String input = sc.nextLine();
		int trueLength = input.length();
		char [] str = new char[trueLength + 3 * input.split(" ").length]; // 공백을 '%20'으로 바꾼 후의 문자열을 담을 배열

		for (int i = 0; i < trueLength; i++) {
			str[i] = input.charAt(i);
		}
		
		replaceSpaces(str, trueLength);
		System.out.println("결과: " + new String(str));
		/*			문자열이 주어졌을때.	
 *	
 * 		char [] str = "Mr John Smith    ".toCharArray();
		int trueLength = 13;
		replaceSpaces(str, trueLength);
		System.out.println(new String(str));
*/
	}
}
