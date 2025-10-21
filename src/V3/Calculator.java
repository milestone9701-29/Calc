package V3;

import java.util.*;
import java.util.stream.Collectors;
// * : 하위 패키지까지 포함하지 않는다.

public class Calculator<T extends Number> {
    // ArrayList
    private final List<Double> results = new ArrayList<>();

    public double calculate(T a, T b, OperatorType op) {
        Objects.requireNonNull(a, "a는 Null일 수 없습니다."); // NULL
        Objects.requireNonNull(b, "b는 Null일 수 없습니다.");
        Objects.requireNonNull(op, "연산자를 지정해주십시오. (Help로 명령어 보기.)");
        if (Double.isNaN(a.doubleValue()) || Double.isNaN(b.doubleValue())) {
            throw new IllegalArgumentException("숫자를 입력하세요. (Help로 명령어 보기.)"); // Not a Number
        }
        if (Double.isInfinite(a.doubleValue()) || Double.isInfinite(b.doubleValue())) {
            throw new IllegalArgumentException("무한대는 허용되지 않습니다. (Help로 명령어 보기.)"); // infinite
        }
        double x = a.doubleValue();
        double y = b.doubleValue();
        double r = op.apply(x, y);
        if (Double.isNaN(r)) {
            throw new ArithmeticException("숫자가 아닙니다(Not a Number).");
        }
        if (Double.isInfinite(r)) {
            throw new ArithmeticException("결과가 무한(infinity)대입니다.");
        }
        results.add(r == 0.0 ? 0.0 : r); // 부호비트 끝자리가 1인 음의 0 보정
        return r;
    }


    public List<Double> getResults() {
        return Collections.unmodifiableList(results); // Read 전용 View : 외부에서 add, remove X
    }
    // 스냅샷 : return List.copyOf(results);


    public void clearAll() {
        results.clear();
    }

    public Double removeOldest() {
        return (results.isEmpty() ? null : results.remove(0));
    }

    public Double removeLatest() {
        return (results.isEmpty() ? null : results.remove(results.size() - 1));
    }

    public Double removeAt(int idx) {
        return ((idx < 0 || idx >= results.size()) ? null : results.remove(idx));
    }

    public List<Double> greaterThan(double threshold) { // 기준값 threshold
        return results.stream().filter(v -> v > threshold).collect(Collectors.toList());
        // Java 16 + : results.stream().toList() 사용 할 수 있으므로, Collectors.toList()가 필요 없다.
    }
}

    /* static String userAlertMessage(Throwable e) {
        if (e instanceof ArithmeticException) return "수학적 오류입니다.";
        if (e instanceof NumberFormatException) return "숫자 형식이 아닙니다(Not a Number). 예: 3, -1.9, 2.2e3(2.2*10^3)";
        if (e instanceof UnsupportedOperationException) return "Read Only 목록은 수정할 수 없습니다.";
        if (e instanceof IllegalArgumentException) return e.getMessage()!=null? e.getMessage(): "잘못된 입력.";
        if (e instanceof IndexOutOfBoundsException) return "인덱스 범위 초과.";
        if (e instanceof NoSuchElementException) return "입력이 더 이상 없습니다(EOF : -1).";
        return "예상치 못한 오류가 발생했습니다.";
    }*/
// euclideanMod
    /* public static double euclideanMod(double a, double b) {
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
        // 피연산자 a, b를 받는다 -> enum OperatorType
        // -> 일치하는 연산기호 찾은 후 연산 : %일 경우,
        // -> Return Calculator.euclideanMod(a, b);
    }*/
