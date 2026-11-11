import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_000L;

    public int solution(int[] T) {
        int n = T.length;
        
        // Store (work_time, original_index)
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(T[i], i);
        }
        
        // Sort items primarily by work time, secondarily by index
        Arrays.sort(items, (a, b) -> {
            if (a.time != b.time) return Integer.compare(a.time, b.time);
            return Integer.compare(a.index, b.index);
        });
        
        // Fenwick tree to track active items (initially all 1s)
        FenwickTree activeTree = new FenwickTree(n);
        for (int i = 0; i < n; i++) {
            activeTree.add(i + 1, 1);
        }
        
        long totalWaitTime = 0;
        long timeSumFinished = 0; // Sum of T[j] for finished items
        int finishedCount = 0;
        
        int ptr = 0;
        while (ptr < n) {
            // Process a group of items with identical T[k] values together
            int groupStart = ptr;
            int currentTime = items[ptr].time;
            
            while (ptr < n && items[ptr].time == currentTime) {
                ptr++;
            }
            
            // Remove items of this group from activeTree after calculating their completion times
            for (int k = groupStart; k < ptr; k++) {
                int idx = items[k].index;
                
                // Count active items before idx and after idx
                long activeBefore = activeTree.query(idx); // active items in [0, idx-1]
                long activeAfter = (n - finishedCount) - activeBefore - 1; // active items in [idx+1, n-1]
                
                // Calculate completion time for this item:
                // 1. Time spent on already finished items
                // 2. Time spent on remaining active items in rounds 1 to (currentTime - 1)
                // 3. Time spent in the current round up to index idx
                long fullRounds = currentTime - 1;
                long activeTime = fullRounds * (n - finishedCount);
                long currentRoundTime = activeBefore + 1;
                
                long completionTime = timeSumFinished + activeTime + currentRoundTime;
                totalWaitTime = (totalWaitTime + completionTime) % MOD;
            }
            
            // Mark all items in this group as finished
            for (int k = groupStart; k < ptr; k++) {
                int idx = items[k].index;
                activeTree.add(idx + 1, -1);
                timeSumFinished += currentTime;
                finishedCount++;
            }
        }
        
        return (int) (totalWaitTime % MOD);
    }
    
    private static class Item {
        int time;
        int index;
        
        Item(int time, int index) {
            this.time = time;
            this.index = index;
        }
    }
    
    private static class FenwickTree {
        int[] tree;
        int size;
        
        FenwickTree(int n) {
            this.size = n;
            this.tree = new int[n + 1];
        }
        
        void add(int idx, int val) {
            while (idx <= size) {
                tree[idx] += val;
                idx += idx & (-idx);
            }
        }
        
        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & (-idx);
            }
            return sum;
        }
    }
}
