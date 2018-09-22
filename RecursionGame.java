package mini;

/**
*@Author Andrei Baechle
**/

import java.util.ArrayList;

public class RecursionGame extends java.lang.Object {

	private int intVal;

	private String numList;

	public RecursionGame(int v) {
		intVal = v;
		numList = "" + v;

	}

	public RecursionGame(RecursionGame e1, RecursionGame e2, char op) {
		if (op == '+') {
			intVal = e1.intValue() + e2.intValue();
		} else if (op == '-') {
			intVal = e1.intValue() - e2.intValue();
		} else if (op == '*') {
			intVal = e1.intValue() * e2.intValue();
		} else if (op == '/' && e2.intValue() != 0 && e1.intValue() % e2.intValue() == 0) {
			intVal = e1.intValue() / e2.intValue();
		} else if (op == '^') {
			intVal = (int) Math.pow(e1.intValue(), e2.intValue());
		}
		else {
		numList = "(" + e1 + " " + op + " " + e2 + ")";
		}

	}

	public static void findCombinations(java.util.ArrayList<RecursionGame> list, int target,
			java.util.ArrayList<java.lang.String> results) {

		if (list.size() == 1) {
			if (list.get(0).intValue() == target) {
				results.add(list.get(0).toString());
			}
		}

		else {
			for (RecursionGame x : list) {
				ArrayList<RecursionGame> c = new ArrayList<>();
				c.addAll(list);
				c.remove(x);
				findCombinations(c, target, results);
			}
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = i + 1; j < list.size(); j++) {
					ArrayList<RecursionGame> c = new ArrayList<RecursionGame>();
					c.addAll(list);
					ArrayList<RecursionGame> c1 = new ArrayList<RecursionGame>();
					c1.add(new RecursionGame(list.get(i), list.get(j), '+'));
					c1.add(new RecursionGame(list.get(j), list.get(i), '+'));
					c1.add(new RecursionGame(list.get(i), list.get(j), '-'));
					c1.add(new RecursionGame(list.get(j), list.get(i), '-'));
					c1.add(new RecursionGame(list.get(i), list.get(j), '*'));
					c1.add(new RecursionGame(list.get(j), list.get(i), '*'));
					c1.add(new RecursionGame(list.get(i), list.get(j), '/'));
					c1.add(new RecursionGame(list.get(j), list.get(i), '/'));
					c1.add(new RecursionGame(list.get(i), list.get(j), '^'));
					c1.add(new RecursionGame(list.get(j), list.get(i), '^'));
					for (RecursionGame a : c1) {
						ArrayList<RecursionGame> arr = new ArrayList<RecursionGame>();
						for (int k = 0; k < c.size(); k++) {
							if (k != i && k != j) {
								arr.add(c.get(k));
							}
						}
						arr.add(a);
						findCombinations(arr, target, results);
					}
				}
			}
		}
	}

	public int intValue() {
		return intVal;
	}

	public java.lang.String toString() {
		return numList;
	}

}
