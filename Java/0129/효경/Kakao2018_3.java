package day0126;

import java.util.Arrays;
import java.util.Comparator;

public class Kakao2018_3 {

//	public static void main(String[] args) {
//
//		String[] str = { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" };
//		
//		Arrays.sort(str, new FileSort());
//		
//		System.out.println(Arrays.toString(str));
//	}

	public String[] solution(String[] files) {


		Arrays.sort(files, new FileSort());

		return files;
	}

}

class FileSort implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {

		String[] f1 = fileNameSplit(o1);
		String[] f2 = fileNameSplit(o2);

		if (f1[0].equals(f2[0])) 
			return Integer.parseInt(f1[1]) - Integer.parseInt(f2[1]);

		 else 
			return f1[0].compareTo(f2[0]);

	}

	
	static String[] fileNameSplit(String str) {

		char[] arr = str.toCharArray();

		int numberIndex = -1;
		int numberLastIndex = -1;

		for (int i = 0; i < str.length(); i++) {

			if (Character.isDigit(arr[i])) {
				if (numberIndex == -1) {
					numberIndex = i;
					numberLastIndex = i;
					continue;
				}
				if (i == numberLastIndex + 1) {
					numberLastIndex = i;
				}
			}

			if (numberLastIndex >= 0 && !Character.isDigit(arr[i]))
				break;
		}

		String[] fileName = new String[2];
		fileName[0] = str.substring(0, numberIndex).toUpperCase();
		fileName[1] = str.substring(numberIndex, numberLastIndex + 1);

		return fileName;

	}

}
