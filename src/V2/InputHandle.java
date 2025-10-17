package V2;

import java.util.*;

public class InputHandle{
	private Scanner s = new Scanner(System.in); // 스캐너 호출
	private Calc cal; // 다른 클래스의 객체 참조
	

	// 생성자에서 Calc 객체를 받는다.
    public InputHandle(Calc cal) {
        this.cal = cal;
    }
	public void noticeFeature() { System.out.println("(list : 조회 및 삭제, exit : 종료)");	}
	public Double readNumber(String message) { // 숫자 검증
		while(true) { // 반복 : return 전까지.
            noticeFeature();
			System.out.print(message); // 메시지 출력
			String input = s.next().trim(); // 문자열 반환. next()는 %n 무시, nextLine()은 %n 포함한다.
			
			if (input.equalsIgnoreCase("exit")) return null; // 대소문자 구분없이 exit면 잘 가.

			if (input.equalsIgnoreCase("list")) {
				System.out.println("저장된 결과 목록 : " + cal.getResults()); // Calc Read 메서드
				System.out.println("삭제할 List를 입력하세요."); 
				System.out.print("(삭제할 번호 입력, 오래된 연산 : L, 최근 : R, 취소 : Q, 전부 삭제 : Clear) : ");
				String indexInput = s.next().trim();
				if(indexInput.equalsIgnoreCase("Q")) { System.out.println("리스트 삭제 취소 완료."); continue; }
				if(indexInput.equalsIgnoreCase("L")) { 
					cal.removeOldestResult();
					System.out.println("가장 오래된 연산을 삭제 했습니다."); 
					continue;
				}
				if(indexInput.equalsIgnoreCase("R")) { 
					cal.removeLatestResult();
					System.out.println("가장 최근 연산을 삭제 했습니다."); 
					continue;
				}
				if(indexInput.equalsIgnoreCase("clear")) { 
					cal.clearAllResults();
					System.out.println("리스트 전부 삭제 완료."); 
					continue;
				} else { 
						try{
							int index = Integer.parseInt(indexInput);
                            index-=1; // 사용자 입력 보정. 이거 없으면 배열 세는 규칙대로 입력하는 것을 상정해야 한다.
                            cal.removeAtResult(index);
						} catch(NumberFormatException e) {
								System.out.println("올바른 숫자를 입력하렴.");
						} 
					}
					continue; // 삭제 처리 후, 숫자 입력으로 복귀
			}

			try {
				return Double.parseDouble(input); // 입력받은 문자열을 double로 parsing
			}catch (NumberFormatException e) {
				System.out.println("올바른 숫자를 입력하렴."); // 메소드 형태로 무한 loop -> continue; 가 없어도 상관없다.
			}
		}
	}
	public Character readOperator() { // 연산기호 읽어내기
		while (true) {
            noticeFeature();
			System.out.print("연산자(+ - * / %) 입력 : ");
			String input = s.next().trim(); // Scanner 호출 -> String input에 공백 제거.
			if (input.equalsIgnoreCase("exit")) return null;
			if (input.length()==1&&"+-*/%".indexOf(input.charAt(0))>=0) return input.charAt(0); // 단순하게 input.length()==1&&"+-*/%".indexOf(input)!=-1 쓰면 안되려나?
			// String type의 길이(length)가 하나이고(&&), "+-*/%" 대상과 input.charAt(0) = 0번째 인덱스 참조하여 비교한 값이 0보다 크거나 같으면, input.charAt(0);을 리턴한다.
			else System.out.println("유효하지 않은 연산자.");
		}
	}
	public void closeScanner(){
		s.close();
	}
}
// indexOf(input.charAt(0))