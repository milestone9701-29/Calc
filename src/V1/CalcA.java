package V1;

import java.util.*;

public class CalcA {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("a 입력 : exit 입력 시, 종료합니다. : ");
            String wa = s.next().trim(); // next() : 다음 토큰을 문자열로 반환. trim() : 공백 제거. 중앙은 내버려둠.
            if (wa.equalsIgnoreCase("exit")) break; // equals() : 대소문자 구분 비교. equalsIgnoreCase() : 대소문자 구분하지 않음.

            int a;
            try {
                a = Integer.parseInt(wa); // String을 정수로 변환한 값을 리턴.
            } catch (NumberFormatException e) { // catch 구문에 NumberFormatException e 넣고 입력받은 변수에 정수가 없다면 이후 블록 구문 수행.
                System.out.println("정수를 입력해주세요.");
                continue;
            }
            System.out.print("op (+, -, *, /) : ");
            String tops = s.next().trim();
            if (tops.equalsIgnoreCase("exit")) break;
            if (tops.length() != 1) { // length() : 문자열의 길이가 1이 아닐 때, 수행
                System.out.println("연산자 한 자만 입력해주세요.");
                continue;
            }
            char op = tops.charAt(0);
            if ("+-*/".indexOf(op) < 0) { // indexOf() : 문자열에서 특정 문자열(여기선 +-*/)을 찾고, 검색된 문자열이 첫번째로 나타나는 위치 index를 리턴 : 찾는 문자열이 없으면 -1을 리턴함 : 0보다 작으면 연산자 아니라고 안내. : 대소문자 구분한다.
                System.out.println("유효하지 않은 연산자입니다.");
                continue;
            }
            System.out.print("b 입력 : exit 입력 시 종료. ");
            String wb = s.next().trim();
            if (wb.equalsIgnoreCase("exit")) break;

            int b;
            try {
                b = Integer.parseInt(wb); // 똑같이 wb 문자열을 파싱해서 정수값으로 리턴한 값을 b에 넣음.
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력하세요. ");
                continue;
            }

            if (op == '/' && b == 0) { // 연산자가 나누기이고, 나누는 수(제수)가 0일 경우.
                System.out.println("0으로 나눌 수 없습니다.");
                continue;
            }
            // 분기 별로 바로 출력 : 초기화 안한 경우를 방지한다.
            switch (op) {
                case '+':
                    try {
                        int r = Math.addExact(a, b);
                        // int 또는 long : MAX 값 넘기면 ArithmeticException overflow 발생.
                        System.out.println("= " + r);
                    } catch (ArithmeticException e) { // 예외처리
                        System.out.println("덧셈 오버플로우 발생");
                    }
                    break;
                case '-':
                    try {
                        int r = Math.subtractExact(a, b);
                        // int 또는 long : MAX 값 넘기면 ArithmeticException overflow 발생.
                        System.out.println("= " + r);
                    } catch (ArithmeticException e) {
                        System.out.println("뺄셈 오버플로우 발생");
                    }
                    break;
                case '*':
                    try {
                        int r = Math.multiplyExact(a, b);
                        System.out.println("= " + r);
                    } catch (ArithmeticException e) {
                        System.out.println("곱셈 오버플로우 발생");
                    }
                    break;
                case '/':
                    int q = a / b, r = a % b;
                    System.out.println("= " + q + " (Remainder " + r + ")");
                    break;
                default:
                    System.out.println("연산자 오류");// loop 지속.
            }
        }
        System.out.println("종료합니다.");
        s.close(); // Scanner 사용 시 마무리.
    }
}

// int를 double로만 바꾸면 실수 결과물 지원.
// 입력은 그대로 정수로 두고 명시적 형변환으로 (double)a/b;