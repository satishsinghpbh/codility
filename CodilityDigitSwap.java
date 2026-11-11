class Solution {
    public int solution(String S, String T) {
        int n = S.length();
        int firstDiff = -1;
        
        // Find the first position where the digits differ
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) != T.charAt(i)) {
                firstDiff = i;
                break;
            }
        }
        
        // If strings are already identical, 0 swaps are needed
        if (firstDiff == -1) {
            return 0;
        }
        
        char charS = S.charAt(firstDiff);
        char charT = T.charAt(firstDiff);
        
        // Strategy A: Do NOT swap the first differing pair
        // Strategy B: SWAP the first differing pair (which flips which number is larger)
        int swapsWithoutFirst = 0;
        int swapsWithFirst = 1; 
        
        for (int i = firstDiff + 1; i < n; i++) {
            char sChar = S.charAt(i);
            char tChar = T.charAt(i);
            
            if (charS > charT) {
                // S is initially the larger number
                if (sChar > tChar) {
                    swapsWithoutFirst++;
                } else if (sChar < tChar) {
                    swapsWithFirst++;
                }
            } else {
                // T is initially the larger number
                if (tChar > sChar) {
                    swapsWithoutFirst++;
                } else if (tChar < sChar) {
                    swapsWithFirst++;
                }
            }
        }
        
        return Math.min(swapsWithoutFirst, swapsWithFirst);
    }
}
