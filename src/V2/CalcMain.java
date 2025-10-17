package V2;

public class CalcMain {
	public static void main(String[] args) {
		// class 선언
		Calc compute = new Calc();
		InputHandle validator = new InputHandle(compute);

		while(true) {
			Double a = validator.readNumber("첫 번째 숫자 입력 : "); // readNumber() 메서드 사용 -> 매개변수 message에 literal 넣어서 출력하는 모습.

			if (a == null) break; // "exit" -> InputHandle() -> null -> CalcMain() -> break;
			Double b = validator.readNumber("두 번째 숫자 입력 : ");

			if (b == null) break;
			Character op = validator.readOperator(); // InputHandle()에 안내문 넣어둠. readNumber() 메서드 사용.

			if (op == null) break;
			try {
				double result=0; // 변수 초기화
				switch (op) {
					case '+' : result = compute.plus(a, b); break;
					case '-' : result = compute.minus(a, b); break;
					case '*' : result = compute.multiply(a, b); break;
					case '/' : result = compute.quotient(a, b); break;
					case '%' : result = compute.remainder(a, b); break;
					default: System.out.println("잘못된 연산자입니다."); continue;
				}
				System.out.println("= " + result);
			} catch (ArithmeticException e) {
				System.out.println(" ! " + e.getMessage()); // 입력값 -> main() : calc()에 저장된 메시지 출력 : getMessage()
			}
			System.out.println("현재 저장된 결과: " + compute.getResults());
          			System.out.println("-------------------------------------");
		}
		System.out.println("프로그램 종료.");
		validator.closeScanner();
	}
}