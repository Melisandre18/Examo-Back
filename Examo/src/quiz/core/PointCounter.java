package quiz.core;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointCounter {
	static Map<String, List<String>> giveninfo = new HashMap<>();
	public static String question;
	public static String answer;
	public static double pointer;

	public static double point() {
		pointer += pointCount();
		return pointer;
	}

	private static double pointCount() {

		double point = 0;
		if (giveninfo.get(question).size() == 1) {
			if (giveninfo.get(question).get(0).substring(0, giveninfo.get(question).get(0).indexOf("@")).equals(answer)) {
				point += 1;
			}
		} else {
			for (int i = 0; i < giveninfo.get(question).size(); i++) {
				String ans = giveninfo.get(question).get(i);
				if (ans.substring(0, ans.indexOf("@")).equals(answer)) {
					if (!ans.contains("=")) {
						point += Double.parseDouble(ans.substring(ans.indexOf("@")+1, ans.length()));

					} else {
						point += Double.parseDouble(ans.substring(ans.indexOf("@")+1, ans.lastIndexOf("@")));
						
					}
				}

			}
		}
		return point;
	}
}
