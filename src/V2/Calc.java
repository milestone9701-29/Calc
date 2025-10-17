package V2;

import java.util.*;
public class Calc {
	// 여러 계산 결과를 저장할 메모리 공간
	private ArrayList<Double> results = new ArrayList<>(); // results 배열 이름에 ArrayList double 자료형으로 선언. : 동적 배열.
	double plus(double a, double b){
		double r = a+b; // double r 변수 선언 후, 
		results.add(r); // result 배열에 r을 추가한다.
		return r;
	}
	double minus(double a, double b) {
		double r = a-b;
		results.add(r);
		return r;
	}
	double multiply(double a, double b){
		double r = a*b;
		results.add(r);
		return r;
	}
	double quotient(double a, double b) {
		if (b==0) throw new ArithmeticException("0으로 나눌 수 없어요."); // 예외처리 !
		double r = a/b;
		results.add(r);
		return r;
	}
	double remainder(double a, double b) {
		if (b==0) throw new ArithmeticException("0으로 나눌 수 없어요."); // 예외처리 !
        // a=bq+r에서 a%b 연산 시 피제수 a가 음수라면 나머지 또한 음수로 출력.
        double aB = Math.abs(b); // 절댓값(범위 : (-aB,+aB)) a= -3 b=5 -3 = 5(-1)+c
        double r = a%aB;
        if (r < 0) r += aB; // r=r+aB;
        if (r >= aB) r -= aB; // r=r-aB;
        if (r==0.0) r=0.0; // -0.0, +0.0 : IEEE 754 : '마지막 부호 비트에 1 찍힘 -> 부호 있는 0'
        // boolean isNegZero = Double.doubleToRawLongBits(r) == Double.doubleToRawLongBits(-0.0);

		results.add(r);
		return r;
	}

	//RW
	public ArrayList<Double> getResults() { // read
		return results; // 읽기
	}
	public void setResults(ArrayList<Double> newResults) { // write
		this.results = newResults; //  this : 객체의 instance
	}

	//Old, Latest 결과 삭제
	public void removeOldestResult() {  // isEmpty() ArrayList 비어있는지 체크 : results 배열이 비어있지 않으면, results를 result.remove(0)으로 비운다.
		if (!results.isEmpty()) {
			double removed = results.remove(0);
		} else {
			noListDataAlert();
		}
	}
	public void removeLatestResult() {
		if (!results.isEmpty()) {
			double removed = results.remove(results.size()-1); // results.size()-1 : 가장 최근의 기록 삭제.
		} else {
			noListDataAlert();
		} 
	}
	public void removeAtResult(int index) {
		if (index<0||index>=results.size()) {
			System.out.println("잘못된 인덱스입니다."); 
			return;
		}else if(results.isEmpty()) {
			noListDataAlert();
		}
		double removed = results.remove(index);
        index+=1; // 배열 입력 보정
		System.out.println("Index " + index + " 의 결과가 : " + removed + " 삭제 됨.");
	}
	public void noListDataAlert() { // 클래스 아예 통째로 하나 만들어서 예외처리 안내문용으로 달아야 하는거 아닐까..
		System.out.println("삭제할 결과가 없습니다.");
	}
	public void clearAllResults() {
		results.clear();
		System.out.println("List 초기회");
	}
}

// read : getter : 읽기
// write : setter : 쓰기
// execute : 메서드 호출 : 실행

/* class calc<D extends Number> {
	private ArrayList<Double> results > results = new ArrayList<>();
	double plus(D a,D b) {
		double r = a.doubleValue() + b.doubleValue();
		results.add(r);
		return r;
	}
	double minus(D a,D b) {
		double r = a.doubleValue() - b.doubleValue();
		results.add(r);
		return r;
	}
	double multiple(D a,D b) {
		double r = a.doubleValue() * b.doubleValue();
		results.add(r);
		return r;
	}
	double quotient(D a, D b) {
		if (b.doubleValue()==0) throw new ArithmeticException("0으로 나눌 수 없어요."); // 예외처리 !
		double r = a.doubleValue() / b.doubleValue();
		results.add(r);
		return r;
	}
	double remainder(D a, D b) {
		if (b.doubleValue()==0) throw new ArithmeticException("0으로 나눌 수 없어요."); // 예외처리 !
		double r = a.doubleValue() % b.doubleValue();
		results.add(r);
		return r;
	}
	public ArrayList<Double> getResults() { // read
		return results; // 읽기
	}
	public void setResults(ArrayList<Double> newResults) { // write
		this.results = newResults; //  this : 객체의 instance
	}
	public void removeOldestResult() {
		if (!results.isEmpty()) results.remove(0); // isEmpty() ArrayList 비어있는지 체크 : results 배열이 비어있지 않으면, results를 result.remove(0)으로 비운다.
	}
	public void removeLatestResult() {
		if (!results.isEmpty()) results.remove(results.size()-1); // isEmpty() ArrayList 비어있는지 체크 : results 배열이 비어있지 않으면, results를 result.remove(0)으로 비운다.
	}
}


*/

