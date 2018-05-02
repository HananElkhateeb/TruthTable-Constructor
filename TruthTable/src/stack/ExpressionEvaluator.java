package stack;

import stack.IExpressionEvaluator;

/**
 * @author HANAN SAMIR
 *
 */
public class ExpressionEvaluator implements IExpressionEvaluator {
	/**
	 *  @param c to insert
	 *  @return true if stack empty
	 */
	private boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
			}
		return false;
	}
	/**
	* Takes a symbolic/numeric infix expression as input and converts it to.
	* postfix notation. There is no assumption on spaces between terms
	* length of the term (e.g., two digits symbolic or numeric term)
	* @param expression infix expression
	* @return postfix expression
	*/
	public String infixToPostfix(String expression) {
		if (expression == null || expression.length() == 0) {
			throw new RuntimeException();
		}
		if (expression.charAt(0) == '+' 
		   || expression.charAt(0) == '-' 
		   || expression.charAt(0) == '*'
		   || expression.charAt(0) == '/'
		   || expression.charAt(0) == ')') {
			throw new RuntimeException();
		}
		if (expression.charAt(expression.length() - 1) == '+' 
		  || expression.charAt(expression.length() - 1) == '-'
		  || expression.charAt(expression.length() - 1) == '*'
		  || expression.charAt(expression.length() - 1) == '/') {
			throw new RuntimeException();
		}
		for (int i = 0; i < expression.length() - 1; i++) {
			char char1 = expression.charAt(i);
			char char2 = expression.charAt(i + 1);
			if (isOperator(char1) && isOperator(char2)) {
				throw null;
			}
		}
		for (int i = 0; i < expression.length() - 2; i++) {
			char char1 = expression.charAt(i);
			char char2 = expression.charAt(i + 1);
			char char3 = expression.charAt(i + 2);
if (isOperator(char1) && char2 == ' ' && isOperator(char3)) {
				throw new RuntimeException();
			}
		}
		Stack check = new Stack();
		for (int i = 0; i < expression.length(); i++) {
			char curchar = expression.charAt(i);
			if (curchar == '(') {
				check.push('(');
			} else if (curchar == ')') {
				check.pop();
			}
		}
		if (check.size() == 0) {
			Stack s = new Stack();
			String output = "";
			expression = expression.replaceAll("\\s", "");
			for (int i = 0; i < expression.length(); i++) {
				char curchar = expression.charAt(i);
				if (curchar == '(') {
					s.push('(');
				} else if (curchar == ')') {
					while (!(s.isEmpty())) {
						char oper = (char) s.pop();
						if (oper == '(') {
							break;
						} else {
							output = output + oper;
						}
					}
				} else if (curchar == '+' || curchar == '-') {
					if (s.isEmpty()) {
						s.push(curchar);
					} else {
						Character oper = (Character) s.peek();
						while (!(s.isEmpty() 
								|| oper.equals(('(')) 
								|| oper.equals((')')))) {
							oper = (Character) s.pop();
							output += oper.charValue();
						}
						s.push(curchar);
					}
				} else if (curchar == '*' || curchar == '/') {
					if (s.isEmpty()) {
						s.push(curchar);
					} else {
						Character oper = (Character) s.peek();
						while (!oper.equals(('(')) 
							  && !oper.equals(('+'))
							  && !oper.equals(('-'))
							  && !s.isEmpty()) {
							oper = (Character) s.pop();
							output += oper.charValue();
						}
						s.push(curchar);
					}
				} else {
					output += curchar;
				}
			}
			while (!s.isEmpty()) {
				Character oper = (Character) s.peek();
				if (!oper.equals(('('))) {
					s.pop();
					output += oper.charValue();
				}
			}
			output = output.replace("(", "");
			output = output.replace("", " ").trim();
			return output;
		} else {
			throw new RuntimeException();
		}
	}
	/**
	* Evaluate a postfix numeric expression, with a single space separator.
	* @param expression postfix expressio
	* @return the expression evaluated value
	*/
	public int evaluate(String expression) {
		if (expression == null || expression.length() == 0) {
			throw new RuntimeException();
		}
		Stack h = new Stack();
		char[] chars = expression.toCharArray();
		int size = chars.length;
		for (int i = 0; i < size; i++) {
			char ch = chars[i];
			if (ch == '*' || ch == '+' || ch == '-' || ch == '/') {
				if (h.isEmpty() || h.size() < 2) {
					throw new RuntimeException();
				}
				int temp1 = (Integer) h.pop();
				int temp2 = (Integer) h.pop();
				switch (ch) {
				case '+':
					h.push(temp2 + temp1);
					break;
				case '-':
					h.push(temp2 - temp1);
					break;
				case '*':
					h.push(temp2 * temp1);
					break;
				case '/':
					h.push(temp2 / temp1);
					break;
				default:throw new RuntimeException();	
				}
			} else if (Character.isDigit(ch)) {
				h.push(Character.getNumericValue(ch));
				while (Character.isDigit(chars[i + 1])) {
					h.push(10 * (Integer) h.pop() + (chars[++i] - '0'));
				}
			}
		}
		if (h.size() == 0) {
			throw new RuntimeException();
		}
		else if (h.size() > 1) {
			return 0; }
		if (!h.isEmpty()){
			return (Integer) h.pop();}
		else{
			return 0;}
	}
}