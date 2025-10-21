package V3;
public enum OperatorType { // enum : , 끝에 ; 잊지말 것.
    ADD('+') {
        @Override double apply(double a, double b) { return a+b; } },
    SUB('-') {
        @Override double apply(double a, double b) { return a-b; } },
    MUL('*') {
        @Override double apply(double a, double b) { return a*b; } },
    DIV('/') {
        @Override double apply(double a, double b) {
            if (b==0.0) throw new ArithmeticException("0으로 나눌 수 없어요.");
            return a/b;
        }
    },
    MOD('%') {
        @Override double apply(double a, double b) {
            double absB = Math.abs(b);
            if (absB==0.0) throw new ArithmeticException("0으로 나눌 수 없어요.");
            // 임의의 정수 a, b(b!=0)에 대하여, a=bq+r 에서 0 ≤ r< |b|
            // r=a-bq b > 0이라 가정하면, q= [a/b]
            // b의 부호 값 상관없이라 가정하면, q = [a/|b|]
            // [x] ≤ x < [x]+1 라 가정. x = [a/|b|]
            // [x] ≤ [a/|b|] < [x]+1 전체 식에 |b|를 곱하면,
            // [x]|b| ≤ [a] < ([x]+1)|b| 전체 식에 [x]|b|를 빼면,
            // 0 ≤ [a]-[x]|b| < |b| : 0 ≤ r < [b|
            double r = a-absB*Math.floor(a/absB); // r = a-absB*Math.floor(a/absB); 가우스
            r = (r==0.0?0.0:r); // 부호비트 마지막 1일 때의 0 정리.
            return r;
        }
    },
    POW('^') { // 비트 연산자(^) XOR과 다릅니다.. return a^b; (X)
        @Override double apply(double a, double b) {
            // 음의 밑이고 정수가 아닌 지수의 경우. 예 : -6^0.3 : 복소수 영역
            if (a < 0 && b != Math.rint(b)) throw new IllegalArgumentException("밑이 음수이자, 정수가 아닌 지수는 허용되지 않습니다."); // Math.rint() : 인자 값에 가장 가까운 정수를 double 형으로 return.
            // 0의 0승 : 0^0 = 1
            if (a==0.0&&b==0.0) return 1.0;
            // 0의 음수 지수 : 무한대 발산
            if (a==0.0&&b<0.0) {
                throw new ArithmeticException("0의 음수 지수는 허용하지 않습니다.");
            }
            double r = Math.pow(a,b); // a는 밑, b는 지수
            return (r==0.0?0.0:r); // // 부호비트 마지막 1일 때의 0 정리.
        }
    };

    private final char symbol;
    OperatorType(char symbol) { this.symbol = symbol; }
    // enum 생성자 : private. 상수 선언 시 호출.
    // 매개변수 symbol(지역 변수)을 필드 this.symbol에 대입. : add처럼 상수 만들 때 생성자 호출되어 symbol 설정.
    public char getSymbol() { return symbol; } // Getter
    abstract double apply(double a, double b); // 추상 메서드 선언 : 상수가 메서드 각자 구현. : op.apply(a,b)만 호출하면 연산이 알아서 실행.

    // 입력 문자가 유효한 연산자 기호인지 검사.
    public static boolean isValid(char c) { // values() : 컴파일러가 자동 생성하는 정적 메서드 : 모든 enum 상수의 배열 돌리기.
        for (OperatorType t : values()) { // for (Operator t : values()) : 모든 상수 순회.
            if (t.symbol == c) return true;
        }
        return false;
    }
    // 문자 기호 -> enum 상수로 변경
    // values()로 전부 돌면서 symbol이 같은 상수를 찾으면 즉시 반환.
    // 못찾는다? IllegalArgumentException
    public static OperatorType from(char c) {
        for (OperatorType t : values()) if (t.symbol==c) return t;
        throw new IllegalArgumentException("지원하지 않는 연산자 : " + c);
    }

}
