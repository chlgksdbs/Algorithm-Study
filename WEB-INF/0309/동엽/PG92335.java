class Solution {
    public int solution(int n, int k) {
		String[] mystr = Integer.toString(n, k).split("0");
		int sum = 0;
		for(String s : mystr) {
			if(s.length()==0) continue;
			Long target = Long.parseLong(s);
			if(isPrime(target)) sum++;
		}
        return sum;
    }
	private static boolean isPrime(Long target) {
		if(target < 2) return false;
		for(int i = 2; i <= Math.sqrt(target);i++) {
			if(target % i == 0) return false;
		}
		
		return true;
	}


}