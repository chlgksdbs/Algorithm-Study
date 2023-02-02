import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        ArrayList<File> myfiles = new ArrayList<>();
		int totalfiles = 0;
		for (String s : files) {
			myfiles.add(new File(s,totalfiles++));
		}
		Collections.sort(myfiles);
		
        for(int i = 0;i<files.length;i++){
            answer[i] = myfiles.get(i).oldstring;
        }

        return answer;
    }
}

class File implements Comparable<File> {
	String head="";
	int number;
	String oldstring;
	int idx;
	File(String mystr,int idx) {
		this.idx = idx;
		this.oldstring = mystr;
		boolean isnum = false;
		String numbertmp = "";	
		for (int i = 0; i < mystr.length(); i++) {
			int index = mystr.charAt(i);
			if (!isnum) {
				if (48 <= index && index <= 57) {
					isnum = true;
					numbertmp += mystr.charAt(i);
				}
				else {
					this.head += mystr.charAt(i);
				}
			}
			else {
				if(48 <= index && index <= 57) {
					numbertmp += mystr.charAt(i);
				}
				else {
					break;
				}
			}
		}
		
		this.number = Integer.parseInt(numbertmp);
		this.head = this.head.toUpperCase();
	}

	@Override
	public int compareTo(File o) {
		
		int comp = this.head.compareTo(o.head);
		if(comp != 0) return comp;
		int comp2 = this.number - o.number;
		if(comp2 != 0) return comp2;
		return this.idx - o.idx;
	}
}