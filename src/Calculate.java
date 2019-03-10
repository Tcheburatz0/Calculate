import java.util.Stack;

public class Calculate {
    public static String ExpressionToRpn(String Expr){
        String current = "";
        Stack<Character> stack = new Stack<>();
        int priority;
        for (int i =0; i < Expr.length();i++){
            priority = getPriority(Expr.charAt(i));
            if(priority == 0)current+=Expr.charAt(i);
            if(priority == 1)stack.push(Expr.charAt(i));
            if(priority > 1){
                current+=' ';
                while(!stack.isEmpty()) {
                    if (getPriority(stack.peek()) >= priority) current += stack.pop();
                    else break;
                }
                    stack.push(Expr.charAt(i));
            }
            if (priority == -1){
                current+=' ';
                while(getPriority(stack.peek()) != 1)current+=stack.pop();
                stack.pop();
            }
        }
        while(!stack.empty())current+=stack.pop();
        return current;
    }
    public static double RpnToAnswer(String rpn){
        String operand = new String();
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < rpn.length();i++){
            if(rpn.charAt(i) == ' ') continue;
            if(getPriority(rpn.charAt(i)) == 0){
                while(rpn.charAt(i)!=' ' && getPriority(rpn.charAt(i)) == 0){
                    operand+=rpn.charAt(i++);
                if(i == rpn.length())break;}
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }
            if(getPriority(rpn.charAt(i))>1){
                double a = stack.pop(), b = stack.pop();
                if(rpn.charAt(i) == '+')stack.push(b+a);
                if(rpn.charAt(i) == '-')stack.push(b-a);
                if(rpn.charAt(i) == '*')stack.push(b*a);
                if(rpn.charAt(i) == '/')stack.push(b/a);
                if(rpn.charAt(i) == '^')stack.push(Math.pow(b,a));

            }
        }
        return stack.pop();
    }
    private static int getPriority(char token){
        if (token == '^') return 4;
        else if(token == '*' || token=='/') return 3;
        else if(token == '+' || token == '-') return 2;
        else if(token == '(') return 1;
        else if(token == ')') return -1;
        else return 0;
    }
    public void Print() {
        System.out.println(toString());
    }

}
