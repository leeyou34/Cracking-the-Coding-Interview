/*
회문순열: 주어진 문자열이 회문(palindrome)의 순열인지 아닌지 확인하는 함수를 작성하라. 회문이란 앞으로 읽으나 뒤로 읽으나 같은 단어 혹은 구절을 의미하며, 순열이란 문자열을 재배치하는 것을 뜻한다. 회문이 꼭 사전에 등장하는 단어로 제한될 필요는 없다.
예제
입력: tact coa
출력: True(순열: "taco cat", "atco cta" 등등)
*/
package crackingthecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PalindromePermutation {
	 public static boolean isPalindromePermutation(String str) {
	        // 문자의 등장 횟수를 저장할 HashMap
	        Map<Character, Integer> charCountMap = new HashMap<>();

	        // 문자열을 순회하며 각 문자의 등장 횟수를 센다
	        for (char ch : str.toCharArray()) {
	            if (ch != ' ') { // 공백은 제외한다
	                charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
	            }
	        }

	        // 등장 횟수가 홀수인 문자의 개수를 센다
	        int oddCount = 0;
	        for (int count : charCountMap.values()) {
	            if (count % 2 != 0) {
	                oddCount++;
	            }
	        }

	        // 등장 횟수가 홀수인 문자의 개수가 1개 이하여야 회문의 순열이 성립한다
	        return oddCount <= 1;
	    }

	 // 해법 # 1
	 static boolean isPermutationOfPalindrome1(String phrase) {
		 int [] table = buildCharFrequencyTable(phrase);
		 return checkMaxOneOdd(table);
	 }
	 
	 /* 홀수 문자가 한 개 이상 존재하는 지 확인한다.*/
	 static boolean checkMaxOneOdd(int[] table) {
		 boolean foundOdd = false;
		 for (int count : table) {
			 if (count %2 ==1) {
				 if (foundOdd) {
					 return false;
				 }
				 foundOdd = true;
			 }
		 }
		 return true;
	 }
	 
	 /* 각 문자에 숫자를 대응시킨다. a -> 0, b -> -1, c -> -2, 등등 
	  * 대소문자 구분이 없고, 문자가 아닌 경우에는 -1로 대응시킨다.
	  * */
	 static int getCharNumber(Character c) {
		 int a = Character.getNumericValue('a');
		 int z = Character.getNumericValue('z');
		 int val = Character.getNumericValue(c);
		 if ( a <= val && val <= z) {
			 return val -a;
		 }
		 return -1;
	 }
	 
	 /* 각 문자가 몇 번 등장했는지 센다. */
	 static int [] buildCharFrequencyTable(String phrase) {
		 int[] table = new int [Character.getNumericValue('z') - Character.getNumericValue('a')+1];
		 for (char c : phrase.toCharArray()) {
			 int x = getCharNumber(c);
			 if ( x!= -1) {
				 table[x]++;
			 }
		 }
		 return table;
	 }
	 
	 /*해법 2: BIG-O 시간에서 조금은 더 개선한 코드이다. */
	 static boolean isPermutationOfPalindrome2(String phrase) {
		 int countOdd =0;
		 int[] table = new int[Character.getNumericValue('z')-Character.getNumericValue('a')+1];
		 for (char c : phrase.toCharArray()) {
			 int x = getCharNumber(c);
			 if (x != -1) {
				 table[x]++;
				 if(table[x] %2 ==1) {
					 countOdd++;
				 } else {
					 countOdd--;
				 }
			 }
		 }
		 return countOdd <= 1;
	 }
	 // 해법 3
	 
	 static boolean isPermutationOfPalindrome3(String phrase) {
		 int bitVector = createBitVector(phrase);
		 return bitVector == 0 || checkExactlyOneBitSet(bitVector);
	 }
	 
	 /* 문자열에 대한 비트 벡터를 만든다. 값이 i인 문자가 등장하면 i 번째 비트 값을 바꾼다. */
	 static int createBitVector(String phrase) {
		 int bitVector =0;
		 for(char c : phrase.toCharArray()) {
			 int x = getCharNumber(c);
			 bitVector = toggle(bitVector, x);
		 }
		 return bitVector;
	 }
	 /* 정수의 1번째 비트를 바꾼다. */
	 static int toggle(int bitVector, int index) {
		 if(index <0) return bitVector;
		 int mask = 1 << index;
		 if((bitVector & mask)==0) {
			 bitVector |= mask;
		 } else {
			 bitVector &= ~mask;
		 }
		 return bitVector;
	 }
	 
	 /* 정확히 비트 한 개만 1로 세팅됐는지 확인하기 위해 주어진 정수값에서 1을 뺀 뒤 원래 값과 AND 연산을 한다.
	  * 
	  */
	 
	 static boolean checkExactlyOneBitSet(int bitVector) {
		 return (bitVector & (bitVector -1))==0;
	 }
	 
	 
	 public static void main(String[] args) {
	  	Scanner sc = new Scanner(System.in);
	  	while (true) {
	 		System.out.println("주어진 문자열이 회문의 순열인지 아닌지 입력하세요. 맞으면 True 틀리면 False가 출력됩니다.");
	 		String input = sc.nextLine();
/*	 		// chatgpt
	 		boolean result = isPalindromePermutation(input);
	 		System.out.println(result);
	 		// 해답1
	 		boolean result1 = isPermutationOfPalindrome1(input);
	 		System.out.println(result1);
	 		// 해답2
	 		boolean result2 = isPermutationOfPalindrome2(input);
	 		System.out.println(result2);
*/	 		
	 		//chatGPT
	 		long startTime1 = System.nanoTime();
            boolean result1 = isPalindromePermutation(input);
            long endTime1 = System.nanoTime();
            long executionTime1 = endTime1 - startTime1;
            System.out.println("isPalindromePermutation: " + result1);
            System.out.println("Execution Time: " + executionTime1 + " nanoseconds");
            // 해답 1
            long startTime2 = System.nanoTime();
            boolean result2 = isPermutationOfPalindrome1(input);
            long endTime2 = System.nanoTime();
            long executionTime2 = endTime2 - startTime2;
            System.out.println("isPermutationOfPalindrome1: " + result2);
            System.out.println("Execution Time: " + executionTime2 + " nanoseconds");
            // 해답 2
            long startTime3 = System.nanoTime();
            boolean result3 = isPermutationOfPalindrome2(input);
            long endTime3 = System.nanoTime();
            long executionTime3 = endTime3 - startTime3;
            System.out.println("isPermutationOfPalindrome2: " + result3);
            System.out.println("Execution Time: " + executionTime3 + " nanoseconds");
            // 해답 3
            long startTime4 = System.nanoTime();
            boolean result4 = isPermutationOfPalindrome3(input);
            long endTime4 = System.nanoTime();
            long executionTime4 = endTime4 - startTime4;
            System.out.println("isPermutationOfPalindrome3: " + result4);
            System.out.println("Execution Time: " + executionTime4 + "nanoseconds");
            
	 		if(input.contentEquals("exit")) {
				System.out.println("exit을 입력 하셨음으로 프로그램이 종료 됩니다.");
				break;	
			}

	 	}
 }

}
