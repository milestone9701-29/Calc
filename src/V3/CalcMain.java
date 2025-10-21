package V3;
public class CalcMain {
    public static void main(String[] args) {
        Calculator<Number> calc = new Calculator<>();
        InputHandle input = new InputHandle(calc);
        System.out.println("Calc : Help, List, Del, Clear, Exit");
        while(true) {
            Double a = input.readNumber("첫 번째 숫자 입력 : (도움말 : Help) : ");
            if (a==null) break;
            Double b = input.readNumber("두 번째 숫자 입력 : (도움말 : Help) : ");
            if (b==null) break;
            OperatorType op = input.readOperator(); // calculator -> calculate -> Enum -> calculate (-> euclideanMod)
            if (op==null) break;
            try {
                double result = calc.calculate(a,b,op);
                System.out.println("= " + result);
            } catch (ArithmeticException | IllegalArgumentException e) {  // 멀티 캐치
                System.out.println("오류 : " + e.getMessage());
            }
            System.out.println("현재 저장된 결과 : " + calc.getResults());
            System.out.println("----------------------------------------");
            // Option : Stream 조회
            input.queryGreaterThanOnce();
        }
        System.out.println("프로그램 종료.");
    }
}

// Multi Catch.
            /* catch (RuntimeException e) {  // 두 예외의 공통 상위 타입
                   if (e instanceof ArithmeticException || e instanceof IllegalArgumentException) {
                    ...
             } else throw e;
        } */