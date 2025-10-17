# Calc

### 간단한 콘솔 계산기.
#### 1. V1
- 입력 - 연산 - 출력 - 반복
- 예외 처리, 입력 검증.
- exit로 종료.

#### 2. V2
- 구획 나누기
입력 검증 : InputHandle()
계산, 메모리 저장 : Calc()
입력, 흐름 제어. : Main()
- 연산 결과를 저장, 삭제, 출력. : ArrayList<Double>
- 캡슐화 : Getter, Setter 적용.

### 사용법
#### 1. CLI

```
javac Calc.java InputHandle.java CalcMain.java
java CalcMain
```
#### 2. IntelliJ
```
File -> New -> Project from Existing Sources... -> 폴더 열기.
JDK 17 설정 후 CalcMain
```

### 기능
1. 사칙연산(+, -, *, /, %)
2. 연산 결과 History : 저장, 조회, 삭제 : 가장 오래된, 가장 최근, 인덱스 지정 삭제
3. 입력 검증 : 숫자/연산자, exit로 종료.
4. 예외 처리: 0으로 나누기(ArithmeticException e), 형식 오류(NumberFormatException e) : catch 구문.

### 분류
```
src/{
	V1/ {
		CalcA.java
		CalcB.java
	} 
	V2/ {
		Calc.java
		InputHandle.java
		CalcMain.java
	}
}
```
### 동작 예시
```
첫 번째 숫자 입력: 12
두 번째 숫자 입력: 5
연산자(+ - * / %): /
= 2.4
현재 저장된 결과: [2.4]
```

-------------------------------------
