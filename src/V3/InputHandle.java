package V3;

import java.util.*;

public class InputHandle {
    private final Scanner s; // System.in을 닫으면 프로세스 전역 입력이 닫혀 이후 입력이 불가.
    private final Calculator<?> calc; // Generic Wild Card : 호출, 조회 용도. <Number> <Double> ~

    public OperatorType readOperator() { // 오버로딩
        return readOperator("연산자(+, -, *, /, %, ^) 입력 : ");
    }

    // 가짜 Calculator 만들어서 테스트해볼 것.

    public InputHandle(Calculator<?> calc) { // 생성자 체이닝 : 기본 Scanner(System.in)로 위임
        this(calc, new Scanner(System.in));
    }

    public InputHandle(Calculator<?> calc, Scanner sc) { // DI(의존성 주입), null 방어
        this.calc = Objects.requireNonNull(calc, "Calculator must not be null");
        this.s = Objects.requireNonNull(sc, "Scanner must not be null");
    }

    // Help
    public void showHelp() {
        System.out.println("명령 : List, Clear, Del Oldest, Del Latest, Del <index>, Help, Exit");
    }

    public Double readNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = s.nextLine().trim();
            if (line.isEmpty()) continue; // 빈 줄 무시.

            String[] tokens = line.split("\\s+"); // 하나 이상 공백 기준으로 안전하게 분리. 정규식 공백
            String cmd = tokens[0].toLowerCase(Locale.ROOT); // // 소문자 normalize.

            switch (cmd) {
                case "help": {
                    showHelp();
                    continue;
                }
                case "list": {
                    System.out.println("List : " + calc.getResults());
                    continue;
                }
                case "clear": {
                    calc.clearAll();
                    System.out.println("List Cleared");
                    continue;
                }
                case "exit": {
                    return null;
                }
                case "del": {
                    if (tokens.length == 1) { // del 하나인 경우.
                        System.out.println("사용법: Del Oldest | Del Latest | Del <index>");
                        continue;
                    }
                    String next = tokens[1].toLowerCase(Locale.ROOT); // 소문자 normalize
                    if (next.equals("oldest")) {
                        Double d = calc.removeOldest();
                        System.out.println(d == null ? "삭제할 값이 없습니다." : "삭제 완료 : " + d);
                    } else if (next.equals("latest")) {
                        Double d = calc.removeLatest();
                        System.out.println(d == null ? "삭제할 값이 없습니다." : "삭제 완료 : " + d);
                    } else {
                        try {
                            int idx = Integer.parseInt(next);
                            Double d = calc.removeAt(idx);
                            System.out.println(d == null ? "잘못된 인덱스입니다." : "삭제 완료 : " + d);
                        } catch (NumberFormatException e) {
                            System.out.println("Del 사용법 : Del Oldest, Del Latest, Del <index>");
                        }
                    }
                    continue;
                } default: {
                    try { // 숫자 parsing
                        return Double.parseDouble(line);
                    } catch (NumberFormatException e) {
                        System.out.println("숫자를 입력하세요. (도움말 : Help)");
                    }
                }
            }
        }
    }
    public OperatorType readOperator(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = s.nextLine().trim();
            if (line.isEmpty()) continue; // 빈 줄 건너뛰기.
            String cmd = line.toLowerCase(Locale.ROOT); // 소문자로 normalize.

            if (cmd.equals("exit")) {
                return null;
            }
            if (cmd.equals("help")) {
                showHelp();
                continue;
            }
            if (cmd.equals("list")) {
                System.out.println("List : " + calc.getResults());
                continue;
            }
            if (cmd.equals("clear")) {
                calc.clearAll();
                System.out.println("List Cleared");
                continue;
            }
            if (line.length()==1&&OperatorType.isValid(line.charAt(0))) { // line의 길이가 1이고 OperatorType.isValid(line의 charAt 인덱스 0번)
                return OperatorType.from(line.charAt(0));
            }
            System.out.println("유효하지 않은 연산자입니다. : (도움말 : Help)");
        }
    }
    public void queryGreaterThanOnce(){ // 소문자 보정 X
        System.out.print("기준 값(결과 조회, Skip 입력 시 건너뜁니다.) : ");
        String line = s.nextLine().trim();
        if (line.equalsIgnoreCase("skip")) {
            return;
        }
        try {
            double t = Double.parseDouble(line);
            System.out.println(t+"보다 큰 결과 : "+calc.greaterThan(t));
        } catch (NumberFormatException e){
            System.out.println("Skip");
        }
    }
}




    /* public Double readNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String str = s.nextLine().trim(); // 입력 값
            // Clear, List, Help, Exit
            if(str.equalsIgnoreCase("Help")) {
                showHelp();
                continue;
            }
            if(str.equalsIgnoreCase("List")){
                System.out.println("List : " + calc.getResults());
                continue;
            }
            if (str.equalsIgnoreCase("Clear")) {
                calc.clearAll();
                System.out.println("List Cleared");
                continue;
            }
            if (str.equalsIgnoreCase("Exit")) {
                return null;
            }
            if(str.equalsIgnoreCase("Del")) {
                String next = s.nextLine().trim(); // List 용
                if (next.equalsIgnoreCase("Oldest")) {
                    Double d = calc.removeOldest();
                    System.out.println(d==null?"삭제할 값이 없습니다.":"삭제 완료 : "+d);
                } else if (next.equalsIgnoreCase("Latest")) {
                    Double d = calc.removeLatest();
                    System.out.println(d==null?"삭제할 값이 없습니다.":"삭제 완료 : "+d);
                } else {
                    try {
                        int idx = Integer.parseInt(next);
                        Double d = calc.removeAt(idx);
                        System.out.println(d==null?"잘못된 인덱스입니다.":"삭제 완료 : "+d);
                    } catch (NumberFormatException e) {
                        System.out.println("Del 사용법 : Del Oldest, Del Latest, Del <index>");
                    }
                }
                continue;
            }
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요. (Help로 명령어 보기.)");
            }
        }
    } */

/*    public OperatorType readOperator() { // 참조형
        while (true) {
            System.out.print("연산자(+, -, *, /, %, ^) 입력 : ");
            String str = s.nextLine().trim();
            // Exit, Help
            if (str.equalsIgnoreCase("exit")) return null;
            if (str.equalsIgnoreCase("help")) {
                showHelp();
                continue;
            }
            if (str.length()==1&&OperatorType.isValid(str.charAt(0))) { // str의 길이가 1이고 OperatorType.isValid(str의 charAt 인덱스 0번)
                return OperatorType.from(str.charAt(0));
            }
            System.out.println("유효하지 않은 연산자입니다.");
        }
    }
    //Stream : threshold보다 큰 결과 조회.
    public void queryGreaterThanOnce() {
        System.out.print("기준값(결과 조회, Skip 입력 시 건너 뜁니다.) : ");
        String str = s.nextLine().trim();
        if (str.equalsIgnoreCase("Skip")) return;
        try {
            double t = Double.parseDouble(str);
            System.out.println(t+"보다 큰 결과 : "+calc.greaterThan(t));
        } catch (NumberFormatException e) {
            System.out.println("Skip");
        }
    }
} */