/*
    문제: 

    중복이 없는가: 문자열이 주어졌을 때, 이 문자열에 같은 문자가 중복되어 등장하는지 확인하는 알고리즘을 작성하라.
                   자료구조를 추가로 사용하지 않고 풀 수 있는 알고리즘 또한 고민하라.
*/

/*
    해법:
    
    이 문제를 풀려면, 문자 집합에서 i번째 문자가 배열 내에 존재하는지 표시하는 boolean 배열을 사용하는 것이다. 같은 원소에 두 번 접근하면, 바로 false를 반환한다.
   
*/

// 하기는 알고리즘을 구현한 코드

import java.util.Scanner;

public class DuplicateCharacterChecker {
    public static boolean hasDuplicateCharacters(String str) {
        int n = str.length();
        // 각 문자의 등장 여부를 확인하기 위한 boolean 배열
        boolean[] visited = new boolean[256];

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            // 이미 등장한 문자인 경우 중복이 있는 것으로 판단
            if (visited[ch]) {
                return true;
            }
            visited[ch] = true;
        }

        // 중복이 없는 경우
        return false;
    }
    
    public static boolean hasDuplicateCharactersNoDataStructure(String str) {// 자료 구조 없이 메소드 생성
    	int n = str.length();
    	
    	for(int i =0; i < n-1; i++) {
    		char ch = str.charAt(i);
    		for(int j=i+1; j<n; j++) {
    			if(ch == str.charAt(j)) {
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
    public static boolean hasDuplicateCharactersWithBitVector(String str) {
    	// 비트 벡터 선언 및 초기화
    	int [] bitVector = new int[8];
    	
    	for(int i = 0; i < str.length(); i++) {
    		char ch = str.charAt(i);
    		int bitIndex = ch/32; // 비트 벡터 인덱스 계산
    		int bitPosition = ch%32; // 비트 벡터 내 비트 위치 계산
    		
    		// 이미 해당 비트가 1인 경우 중복이 있다고 판단한다.
    		if ((bitVector[bitIndex] & (1<< bitPosition)) !=0) {
    			return true;
    		}
    		
    		// 해당 비트를 1로 설정
    		bitVector[bitIndex] |= (1 << bitPosition);
    	}
    	
    	return false;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
        System.out.println("문자열에 중복되는 문자를  확인하기 위해 입력하시요. ");
    	String input1 = sc.nextLine();

    	if (input1.isEmpty()) {
    		break;
    	} else {
        
    	System.out.println(hasDuplicateCharacters(input1)); // 자료구조가 들어간 method가 실행된다.
    	System.out.println(hasDuplicateCharactersNoDataStructure(input1)); // 자료구조가 들어가지 않은 메소드가 실행된다. 
    	System.out.println(hasDuplicateCharactersWithBitVector(input1)); // 비트 백터를 사용하여 필요 공간 1/8로 줄임
        }   
    }
    }
}

// 위의 코드를 IDE에 넣어 실행시 메인메소드가 문자열에서 중복된 문자를 확인한다.
