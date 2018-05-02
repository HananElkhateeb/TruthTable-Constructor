package Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import stack.ExpressionEvaluator;

public class GetTruthTable {
	private static ArrayList<Character> equationVariables ;
	private static ArrayList<Object> equationArray;
	private static ArrayList<Integer> output;
	ExpressionEvaluator h= new ExpressionEvaluator();
	public GetTruthTable (){
		
	}
	public ArrayList<Character> EvaluateVariables(String equation){
		equationVariables = new ArrayList<>();
		equationArray = new ArrayList<>();
		equation = equation.replaceAll(" ", "");
		equation = equation.toUpperCase();
		for (int i = 0; i < equation.length();i++){
			if (equation.charAt(i)>='A' && equation.charAt(i)<='Z') {
				boolean alreadyExists = false;	
				char temp=equation.charAt(i);
				for (char v : equationVariables){
					if (v==temp){
						alreadyExists = true;
						temp = v;
					}
				}
				if (!alreadyExists){
					equationVariables.add(temp); 
				}
				equationArray.add(temp);
			}else{
				equationArray.add(equation.charAt(i));
			}
		}
      return equationVariables;
	}
	public int[][] constructTruthTable (int colmns) {
		int rows = (int) Math.pow(2.0, (double)colmns);
		int table[][] = new int[rows][colmns];
		int count = 0;
		int repeating = 1;
		for (int i = colmns-1; i >=0; i--) {
			count = 0;
			while(count < rows) {
				int repeatingIndex = 0;
				while ( repeatingIndex < repeating) {
					table[count][i] = 0;
					count++;
					repeatingIndex++;
				}
				repeatingIndex = 0;
				while (repeatingIndex < repeating) {
					table[count][i] = 1;
					count++;
					repeatingIndex++;
				}
			}
			repeating*=2;
		}
		return table;
	}
	public ArrayList<Integer> getOutput(){
		output = new ArrayList<>();
		Map<Character, Integer> varsAndvalues= new HashMap<>();
		int table[][]=constructTruthTable (equationVariables.size());
		int n= equationVariables.size();
		int m = (int) Math.pow(2.0, (double)equationVariables.size());
		String expression = null;
		int result=-1;
		for(int i=0;i<m;i++){
			ArrayList<Object> temp = new ArrayList<>(equationArray);
			for(int j=0;j<n;j++){			
				varsAndvalues.put(equationVariables.get(j), table[i][j]);
			}
			for(int k=0;k<temp.size();k++){
				if(varsAndvalues.containsKey(temp.get(k))){
					expression=expression+String.valueOf(varsAndvalues.get(temp.get(k)));
				}else{
					expression=expression+String.valueOf(temp.get(k));
				}
			}
			for(int s=0;s<expression.length();s++){
				if(expression.charAt(s)=='!'&&expression.charAt(s+1)=='0'){
					expression=expression.replaceAll("!0", "1");
				}else if(expression.charAt(s)=='!'&&expression.charAt(s+1)=='1'){
					expression=expression.replaceAll("!1", "0");
				}
			}
			expression=expression.replaceAll("null", "");
			result=h.evaluate(h.infixToPostfix(expression));
			if(result==0){
				output.add(0);
			}else{
				output.add(1);
			}
			varsAndvalues.clear();
			expression=null;
		}
		return output;
	}
	public boolean isTautology () {
		int flag=output.get(0);
		for(int i=1;i<output.size();i++){
			flag=flag & output.get(i);
		}
		if(flag==0){
			return false;
		}
		return true;
	}
	
	public boolean isContradiction () {
		int flag=output.get(0);
		for (int i = 1; i < output.size(); i++) {
			flag = flag | output.get(i);
		}
        if(flag==0){
        	return true;
        }
       return false;
	}
	public boolean checkEquality(ArrayList<Integer> a,ArrayList<Integer> b){
		return a.equals(b);
	}
}
