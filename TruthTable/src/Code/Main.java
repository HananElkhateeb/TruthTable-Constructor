package Code;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> alloutputs=new ArrayList<ArrayList<Integer>>();
		GetTruthTable t = new GetTruthTable();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter number of expressions:");
		String n = sc.nextLine();
		for (int h = 0; h < Integer.parseInt(n); h++) {
			System.out.println("Please Enter Equation (Use '+' for OR ,'*' for AND and '!' for NOT):");
			String equation = sc.nextLine();
			ArrayList<Character> variables = t.EvaluateVariables(equation);
			int[][] table = t.constructTruthTable(variables.size());
			int m = (int) Math.pow(2.0, (double) variables.size());
			ArrayList<Integer> output = t.getOutput();
			for (int k = 0; k < variables.size(); k++) {
				System.out.print("|" + variables.get(k));
			}
			System.out.print("|output");
			System.out.println();
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < variables.size(); j++) {
					System.out.print("|" + table[i][j]);
				}
				System.out.print("|" + output.get(i));
				System.out.println();
			}
			alloutputs.add(output);
		}
		System.out.println("Enter numbers of expressions to check eqvalience:");
		int n1=sc.nextInt();
		int n2=sc.nextInt();
		System.out.println(t.checkEquality(alloutputs.get(n1-1), alloutputs.get(n2-1)));
	}

}
