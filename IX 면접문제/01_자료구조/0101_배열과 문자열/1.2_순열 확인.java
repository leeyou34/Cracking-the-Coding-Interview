/*
    문제:
    
    순열확인: 문자열 두 개가 주어졌을 때 이 둘이 서로 순열 관계에 있는지 확인하는 메서드를 작성하라.
*/
import java.util.Arrays;
import java.util.Scanner;

public class PermutationChecker {
// 풀이 #1: 정열하기 시작
	private String sort(String s) {
		char[] content = s.toCharArray();
		java.util.Arrays.parallelSort(content);
		return new String(content);
	}
	
	
	public boolean hasPermutation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		return sort(s).equals(sort(t));
	}
// comment: 깔끔하고 단순하며, 이해하기 쉬운 알고리즘이며 실용성에서 훌륭하다.
//			다만 BIG-O에서 시간복잡도는 O(n log n) 이며 풀이 #2 보다 처리속도가 느리다.
// 풀이 #1: 정열하기 끝.

// 풀이 #2: 문자열에 포함된 문자의 출현 횟수가 같은지 검색하라 시작
	public boolean hasPermutationUsingArray(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		
		int[] letters = new int[256]; // 128이 아닌 256개 문자를 기입함
		
		char[] s_array = s.toCharArray();
		for (char c : s_array) { // s 내에서 각 문자의 출현 횟수를 센다.
			letters[c]++;
		}
		for (int i =0; i<t.length(); i++) {
			int c= (int) t.charAt(i);
			letters[c]--;
			if (letters[c] <0) {
				return false;
			}
		}
		return true;
	}
// comment: 배열 두 개 사용하여 각 문자열 내의 문자 출현 횟수를 기록한 뒤 두 배열을 비교한다.
//			해당 알고리즘은 BIG-O 시간에서 시간복잡도  O(n)을 보이고 있다. 	
// 풀이 #2: 문자열에 포함된 문자의 출현 횟수가 같은지 검색하라 끝	
	
// 번외: BIG-O 시간 복잡도 O(log n)이 적용된 메소드. (제일 빠른 메소드) 시작
	// 문자열 정렬하는 메소드
	private String sortUsingBinarySearch(String s) {
		char[] content = s.toCharArray();
		Arrays.parallelSort(content);
		return new String(content);
	}
	
    private boolean isPermutationHelper(String s, String t, int lowS, int highS, int lowT, int highT) {
        if (s.length() != t.length()) {
            return false;
        }
        
        if (lowS == highS) {
            return s.charAt(lowS) == t.charAt(lowT);
        }

        int midS = (lowS + highS) / 2;
        int midT = (lowT + highT) / 2;

        boolean isPermutationLeft = isPermutationHelper(s, t, lowS, midS, lowT, midT);
        boolean isPermutationRight = isPermutationHelper(s, t, midS + 1, highS, midT + 1, highT);
        boolean isPermutationAcross = isPermutationHelper(s, t, lowS, midS, midT + 1, highT);

        return (isPermutationLeft && isPermutationRight) || isPermutationAcross;
    }
    
	public boolean hasPermutationUsingBinarySearch(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		
		String sortedS = sortUsingBinarySearch(s);
		String sortedT = sortUsingBinarySearch(t);
		return isPermutationHelper(sortedS, sortedT, 0, sortedS.length() - 1, 0, sortedT.length() - 1);		
	}
// comment: BIG-O 시간 복잡도 O log(n)을 준수하여 짜여진 코드이다.	
// 번외: 끝
	public static void main(String [] args) {
		PermutationChecker checker = new PermutationChecker();
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.println("순열 확인을 위해 첫번째 문자열을 입력하시요.");
			String input1 = sc.nextLine();
			if(input1.isEmpty()) {
				break;
			} 
			
			System.out.println("순열 확인을 위해 두번째 문자열을 입력하시요.");
			String input2 = sc.nextLine();
			if (input2.isEmpty()){
				break;
			} 
			
			long startTime = System.nanoTime();
            boolean hasPermutation = checker.hasPermutation(input1, input2);
            long endTime = System.nanoTime();
            System.out.println("풀이 #1 실행 결과: " + hasPermutation);
            System.out.println("풀이 #1 실행 시간: " + (endTime - startTime) + " ns");

            startTime = System.nanoTime();
            hasPermutation = checker.hasPermutationUsingArray(input1, input2);
            endTime = System.nanoTime();
            System.out.println("풀이 #2 실행 결과: " + hasPermutation);
            System.out.println("풀이 #2 실행 시간: " + (endTime - startTime) + " ns");

            startTime = System.nanoTime();
            hasPermutation = checker.hasPermutationUsingBinarySearch(input1, input2);
            endTime = System.nanoTime();
            System.out.println("풀이 #3 실행 결과: " + hasPermutation);
            System.out.println("풀이 #3 실행 시간: " + (endTime - startTime) + " ns");		}
	}
}
// 풀이#3 또는 번외라고 만든 메소드는 Big-O 시간 복잡도 O(log n)이 적용 되어있는 메소드이다.
// 해당 메소드의 속도가 빠른지 pneumonoultramicroscopicsilicovolcanoconiosis 이 문자를 넣어서 검토하면 확인이 가능하다.
