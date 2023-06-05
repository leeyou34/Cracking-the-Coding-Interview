/*
1.6 문자열 압축: 반복되는 문자의 개수를 세는 방식의 기본적인 문자열 압축 메서드를 작성하라.
예를 들어 문자열 aabccccaaa를 압축하면 a2b1c4a3이 된다.
만약 '압축된' 문자열의 길이가 기존 문자열의 길이보다 길다면 기존 문자열을 반환해야 한다.
문자열은 대소문자 알파벳(a~z)으로만 이뤄져 있다.


*/


package crackingthecode;

import java.util.Scanner;

public class compressBad {
    public static String compressString(String str) {
        // 기존 문자열의 길이가 압축된 문자열보다 짧을 경우 기존 문자열 반환
        if (str.length() <= getCompressedLength(str)) {
            return str;
        }
        
        StringBuilder compressed = new StringBuilder();
        int count = 1;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                // 현재 문자와 이전 문자가 같으면 count 증가
                count++;
            } else {
                // 현재 문자와 이전 문자가 다르면 압축된 문자열에 추가
                compressed.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        
        // 마지막 문자 처리
        compressed.append(str.charAt(str.length() - 1)).append(count);
        
        return compressed.toString();
    }
    
    private static int getCompressedLength(String str) {
        int compressedLength = 0;
        int count = 1;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                // 현재 문자와 이전 문자가 같으면 count 증가
                count++;
            } else {
                // 현재 문자와 이전 문자가 다르면 압축된 문자열 길이에 추가
                compressedLength += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        
        // 마지막 문자 처리
        compressedLength += 1 + String.valueOf(count).length();
        
        return compressedLength;
    }
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	while(true) {
    		System.out.println("문자열을 압축하고자 하오니 원하는 문자를 입력하세요.");
    		String input1 = sc.nextLine();
    		System.out.println(compressString(input1));
    		
    		if (input1.isEmpty()) {
    			break;
    		} else {
    			
    		}
    	}
    		
 //       String str = "aabccccaaa";
 //       System.out.println(compressString(str)); // 결과: a2b1c4a3
    }
}	
