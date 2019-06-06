package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

@SuppressWarnings({ "resource", "unchecked" })
public class Calculators {
	private static Stack<String> numberStack = new Stack<String>();
	private static String[] symbolArrray = { "+", "-", "*", "/", "sqrt" };
	private static Stack<Stack<String>> history = new Stack<Stack<String>>();
	private static List<String> symbolList = Arrays.asList(symbolArrray);

	// 把元素放入栈顶
	static void push(String a) {
		numberStack.push(a);
	}

	// 从栈顶删除并返回删除的元素
	static String pop() {
		System.out.print("pop -> ");
		if (numberStack.empty()) {
			System.out.println("Stack is empty.");
			return null;
		} else {
			return numberStack.pop();
		}
	}

	// 清除栈
	static void clear() {
		if (!numberStack.empty()) {
			numberStack.clear();
		}
	}

	// 计算
	static double calculate(double x, double y, String symbol) {
		double result = 0;
		switch (symbol) {
		case "+":
			result = x + y;
			break;
		case "-":
			result = x - y;
			break;
		case "*":
			result = x * y;
			break;
		case "/":
			result = x / y;
			break;
		case "sqrt":
			result = Math.sqrt(y);
			break;
		}
		return result;
	}

	/**
	 * 输入内容以" "分隔
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			String inputLine = sc.nextLine();
			String[] inputArray = inputLine.split(" ");
			String content = null;
			for (int i = 0; i < inputArray.length; i++) {
				content = inputArray[i];
				if ("".equals(content)) {
					continue;
				}

				if ("undo".equals(content)) {
					numberStack = history.pop();
					continue;
				}

				history.push((Stack<String>) numberStack.clone());

				if ("clear".equals(content)) {
					clear();
					continue;
				}
				try {
					if (symbolList.contains(content)) {
						String x = pop();
						String y = pop();
						double cal = calculate(Double.parseDouble(y), Double.parseDouble(x), content);
						push(String.valueOf(cal));
					} else {
						push(content);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println("stack: " + numberStack);
		}
	}
}
