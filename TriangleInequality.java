import java.util.ArrayList;
import java.util.List;

class Solution {
    public String solution(String[] words) {
        int n = words.length;
        int k = words[0].length();
        
        String w0 = words[0];
        String w1 = words[1];
        
        // Find all differing indices between the first two words
        List<Integer> diffs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (w0.charAt(i) != w1.charAt(i)) {
                diffs.add(i);
            }
        }
        
        List<String> candidates = new ArrayList<>();
        
        // If they differ in 3 or more positions, no valid string can be within distance 1 of both
        if (diffs.size() > 2) {
            return "";
        } else if (diffs.size() == 2) {
            int i1 = diffs.get(0);
            int i2 = diffs.get(1);
            
            char[] c1 = w0.toCharArray();
            c1[i1] = w1.charAt(i1);
            candidates.add(new String(c1));
            
            char[] c2 = w0.toCharArray();
            c2[i2] = w1.charAt(i2);
            candidates.add(new String(c2));
        } else if (diffs.size() == 1) {
            int idx = diffs.get(0);
            candidates.add(w0);
            candidates.add(w1);
            
            char origChar = w0.charAt(idx);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != origChar && c != w1.charAt(idx)) {
                    char[] arr = w0.toCharArray();
                    arr[idx] = c;
                    candidates.add(new String(arr));
                }
            }
        } else {
            // w0 and w1 are identical
            candidates.add(w0);
            for (int i = 0; i < k; i++) {
                char origChar = w0.charAt(i);
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c != origChar) {
                        char[] arr = w0.toCharArray();
                        arr[i] = c;
                        candidates.add(new String(arr));
                    }
                }
            }
        }
        
        // Test candidates against all words
        for (String cand : candidates) {
            if (isValid(cand, words)) {
                return cand;
            }
        }
        
        return "";
    }
    
    private boolean isValid(String cand, String[] words) {
        for (String w : words) {
            int diffCount = 0;
            int len = w.length();
            for (int i = 0; i < len; i++) {
                if (cand.charAt(i) != w.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) return false;
                }
            }
        }
        return true;
    }
}
