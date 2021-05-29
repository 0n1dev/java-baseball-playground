import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Calculation {

    private String input;
    private LinkedList<Integer> operands = new LinkedList<Integer>();
    private LinkedList<String> operators = new LinkedList<String>();
    private int result;

    public int getResult() {
        this.calculate();
        return result;
    }

    public void input(String input) {
        this.input = input;

        String[] str = input.split(" ");

        for (String e : str) {
            this.add(e);
        }
    }

    private void add(String target) {
        if (this.isNum(target)) {
            operands.add(Integer.parseInt(target));
        } else {
            operators.add(target);
        }
    }

    private boolean isNum(String str) {
        if (str.equals("")) {
            return false;
        }

        return str.matches("-?\\d+");
    }

    private void calculate() {
        result = operands.poll();

        while (operands.size() > 0) {
            Operator operator = Operator.of(operators.poll());

            result = operator.caculate(result, operands.poll());
        }
    }

    private enum Operator {
        P("+", (e1, e2)->e1 + e2),
        M("-", (e1, e2)->e1 - e2),
        MUL("*", (e1, e2)->e1 * e2),
        DIV("/", (e1, e2)->e1 / e2);

        private String operator;
        private BinaryOperator<Integer> operating;
        
        Operator (String operator, BinaryOperator<Integer> operating) {
            this.operator = operator;
            this.operating = operating;
        }
        
        private static Operator of(String operator) {
            return Arrays.stream(Operator.values())
                    .filter(v->v.operator.equals(operator))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("연산자가 잘못됨"));
        }

        private Integer caculate(Integer e1, Integer e2) {
            return operating.apply(e1, e2);
        }
    }
}
