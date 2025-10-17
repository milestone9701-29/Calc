package V1;

import java.util.*;

public class CalcB {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.print("a 입력 : exit 입력 시, 종료합니다. : ");
			String wa = s.next().trim(); // next() : 다음 토큰을 문자열로 반환. trim() : 공백 제거. 중앙은 내버려둠.
			if(wa.equalsIgnoreCase("exit")) break; // equals() : 대소문자 구분 비교. equalsIgnoreCase() : 대소문자 구분하지 않음.

			int a;
			try {
				a = Integer.parseInt(wa); // String을 정수로 변환한 값을 리턴.
			} catch (NumberFormatException e) { // catch 구문에 NumberFormatException e 넣고 입력받은 변수에 정수가 없다면 이후 블록 구문 수행.
				System.out.println("정수를 입력해주세요."); continue;
			}
			System.out.print("op (+, -, *, /) : ");
			String tops = s.next().trim();
			if(tops.equalsIgnoreCase("exit")) break;
			if(tops.length()!=1) { // length() : 문자열의 길이가 1이 아닐 때, 수행
				System.out.println("연산자 한 자만 입력해주세요."); continue;
			}
			char op = tops.charAt(0);
			if ("+-*/".indexOf(op)<0) { // indexOf() : 문자열에서 특정 문자열(여기선 +-*/)을 찾고, 검색된 문자열이 첫번째로 나타나는 위치 index를 리턴 : 찾는 문자열이 없으면 -1을 리턴함 : 0보다 작으면 연산자 아니라고 안내. : 대소문자 구분한다.
				System.out.println("유효하지 않은 연산자입니다."); continue;
			}
			System.out.print("b 입력 : exit 입력 시 종료. ");
			String wb = s.next().trim();
			if(wb.equalsIgnoreCase("exit")) break;

			int b;
			try {
				b=Integer.parseInt(wb); // 똑같이 wb 문자열을 파싱해서 정수값으로 리턴한 값을 b에 넣음.
			} catch (NumberFormatException e) {
				System.out.println("정수를 입력하세요. "); continue;
			}

			if (op=='/' && b==0) { // 연산자가 나누기이고, 나누는 수(제수)가 0일 경우.
				System.out.println("0으로 나눌 수 없습니다."); continue;
			}
			// 분기 별로 바로 출력 : 초기화 안한 경우를 방지한다.
			switch (op) {
				case '+' : if((b>0&&a>Integer.MAX_VALUE - b) || (b<0 && a < Integer.MIN_VALUE - b)){ 
					// (b가 양의 정수이고, Integer.MAX_VALUE(정수 최댓값) - b의 값이 a보다 작거나) || 또는 (b가 음의 정수이고, Integer.MIN_VALUE(정수 최솟값) - b의 값이 a보다 크다면) 
					System.out.println("덧셈 오버플로우 발생"); // float, double : underflow 까지 상정할 것.
					} else {
						System.out.println("= " + (a+b)); 
					} break;
				case '-' : if((b>0&&a>Integer.MAX_VALUE + b) || (b<0&&a<Integer.MIN_VALUE+b)){ 
						System.out.println("뺄셈 오버플로우 발생");
					} else {
						System.out.println("= " + (a-b));
					} break;
				case '*' : 
					long toobig = (long)a*(long)b; // 명시적 형변환
					if((toobig>Integer.MAX_VALUE) || (toobig<Integer.MIN_VALUE)){ 
					// toobig이 정수 최댓값보다 크거나, 정수 최댓값보다 작으면
						System.out.println("곱셈 오버플로우 발생");
					} else {
						System.out.println("= " + (int)toobig);
					} break;
				case '/' :
					int q= a/b;
					int r = a%b;
					System.out.println("= " + q + " (Remainder " + r + ")");
					break; 
				default : System.out.println("연산자 오류"); continue; // loop 지속.
			}
		}
		System.out.println("종료합니다.");
		s.close(); // Scanner 사용 시 마무리.
	}
}

// int를 double로만 바꾸면 실수 결과물 지원.
// 입력은 그대로 정수로 두고 명시적 형변환으로 (double)a/b;