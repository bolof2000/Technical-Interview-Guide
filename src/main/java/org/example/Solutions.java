package org.example;

import java.util.*;

public class Solutions {

    public static ListNode mergeTwoList(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode output = dummy;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                dummy.next = l1;
                l1 = l1.next;
            }else{
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }

        if(l1 != null){
            dummy.next = l1;
            l1 = l1.next;
        }
        if(l2 != null){
            dummy.next = l2;
            l2 = l2.next;
        }

        return output.next;
    }


    public static String mostCommonWord01(String paragraph,String[] banned){

        Set<String> set = new HashSet<>();

        for(String ch:banned){
            set.add(ch);
        }

        System.out.println(set);
        String[] parah = paragraph.toLowerCase().replaceAll("[^a-zA-Z]"," ").split(" ");

        Map<String, Integer> map = new HashMap<>();
        for(String ch:parah){

            if(!set.contains(ch)){

                map.put(ch,map.getOrDefault(ch,0)+1);
                }
            }
        int val =0;
        String result = "";
        for(String key:map.keySet()){

            if(map.get(key)>val){
                val = map.get(key);

                result = key;

            }

        }

        return result;
    }

    public static boolean isAnagram02(String s,String t){
        if(s.length() != t.length()){
            return false;
        }

        Set<Character> s1 = new HashSet<>();
        Set<Character> s2 = new HashSet<>();

        for(Character ch1:s.toCharArray()){
            s1.add(ch1);
        }

        for(Character ch2:t.toCharArray()){
            s2.add(ch2);
        }

        if(s1.size() != s2.size()){
            return false;
        }else{
            return true;
        }

    }

    public static String reverseOnlyLetters03(String s){
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();

        for(Character ch:s.toCharArray()){
            if(Character.isLetter(ch)){
                stack.push(ch);
            }
        }

        for(Character ch:s.toCharArray()){
            if(Character.isLetter(ch)){
                str.append(stack.pop());
            }else{
                str.append(ch);
            }
        }

        return str.toString();
    }

    public static int numUniqueEmails04(String[] emails){

        Set<String> set = new HashSet<>();
        for(String str:emails){
           int index = str.indexOf('@');

           String name = str.substring(0,index);
           String domain = str.substring(index);
           int indexPlus = name.indexOf('+');
           String finalName = name.substring(0,indexPlus);
          // String username = name.replaceAll("[^a-zA-Z]"," ");
            String cleanName = finalName.replaceAll("[^a-zA-Z]","");

            String email = cleanName+domain;

           set.add(email);
        }

        return set.size();
    }

    public static int singleNumber05(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();

        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for(int key:map.keySet()){
            if(map.get(key) ==1){
                return key;
            }
        }

        return  -1;
    }

    public ListNode addTwoNumber06(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode result = dummy;

        int carry = 0;
        while(l1 != null || l2 != null || carry !=0){

            if(l1 != null){
                carry += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                carry += l2.val;
                l2 = l2.next;
            }
            dummy.next = new ListNode(carry%10);
            carry = carry/10;
        }

        return result.next;
    }

    public static boolean validPalindrome07(String s){
        Stack<Character> stack = new Stack<>();

        for(Character c:s.toCharArray()){
            if(!stack.contains(c)){
                stack.push(c);
            }else{
                if(!stack.isEmpty()){
                    stack.remove(c);
                }
            }
        }

        if(stack.size() <=2){
            return true;
        }

        return false;
    }

    public int maxProfit08(int[] prices){

        int maxProfit =0;
        int minPrice = prices[0];

        for(int price:prices){
           if(price < minPrice){
               minPrice = price;

               int currentProfit = price-minPrice;

               maxProfit = Math.max(currentProfit,maxProfit);

           }
        }

        return maxProfit;
    }

    public int[][] transpose09(int[][] grid){
        int row = grid.length;
        int col = grid[0].length;

        int[][] output = new int[col][row];

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){

                output[j][i] = grid[i][j];
            }
        }

        return output;
    }

    public static boolean isPalindrome11(String s){

        String sh = s.replaceAll("[^a-zA-Z]","");
        System.out.println(sh);

        System.out.println(sh);

        int left =0;
        int right = sh.length()-1;

        while (left<right){
            if(sh.charAt(left) != sh.charAt(right)){
                return false;
            }
            left +=1;
            right -=1;
        }

        return true;
    }

    public static String longestPalindromSub12(String s){

        String res = "";
        for(int i=0;i<s.length();i++){

            String btw = expandAround(s,i-1,i+1);
            String middle = expandAround(s,i,i+1);

            if(btw.length() > middle.length()){
                res = btw;
            }else{
                res = middle;
            }
        }
        return res;
    }

    public static String expandAround(String s,int left, int right){
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left -=1;
            right +=1;
        }
        String result = s.substring(left+1,right);

        return result;
    }

    public static String longestPalindrome13(String s){

        String res = "";

        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String subString = s.substring(i,j+1);
                if(subString.length() > res.length() && isPalindrome11(subString)){
                    res = subString;
                }
            }
        }

        return res;
    }

    public static int longestSubWithouRepeatingChar14(String s){

        int maxLen =0;
        Map<Character,Integer> map = new HashMap<>();
        int windowsLen =0;

        for(int i=0;i<s.length();i++){
            char endChar = s.charAt(i);

            if(map.containsKey(endChar) && map.get(endChar) >= windowsLen){
                windowsLen = map.get(endChar)+1;
            }
            map.put(endChar,i);

            maxLen = Math.max(maxLen,i-windowsLen+1);
        }

        System.out.println(map);

        return maxLen;
    }

    public static List<List<String>> groupAnagram15(String[] strs){
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        for(String s:strs){
            String key = sortString(s);

            if(map.containsKey(key)){
                map.get(key).add(s);
            }else{
                map.put(key,new ArrayList<>());
            }
        }

        result.addAll(map.values());
        return result;
    }

    public static String sortString(String s){
        Arrays.sort(s.toCharArray());
        System.out.println(s);
        return s;

    }

    public static int[] twoSum16(int[] nums,int target){

        int[] results = new int[2];

        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            int temp = target-num;

            if(map.containsKey(temp)){
                results[0] = i;
                results[1] = map.get(temp);
            }
            map.put(num,i);
        }

        return results;
    }

    public static  List<List<Integer>> threeSum17(int[] nums){
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i==0 || i >0 && nums[i] != nums[i-1]){

                int left = i+1;
                int right = nums.length-1;

                while(left < right){

                    int temp = nums[left]+nums[right]+nums[i];
                    if(temp ==0){
                        results.add(Arrays.asList(nums[i],nums[left],nums[right]));

                        while(left<right && nums[left]== nums[left+1])
                            left++;

                        while (left<right && nums[right] == nums[right-1])
                            right--;
                        left +=1;
                        right -=1;
                    }else if(temp > 0){
                        right -=1;
                    }else{
                        left -=1;
                    }

                }
            }
        }
        return results;
    }

    public static int maxSubArray18(int[] nums) {

        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);
        int result = nums[0];
        for(int i=1;i<nums.length;i++){
            int num= nums[i];

            dp.add(Math.max(num,num+dp.get(i-1)));

            result = Math.max(result,dp.get(i));
        }

        return result;

    }

    public static boolean canAttendMeetings19(int[][] intervals) {

        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        for(int[] sublists:intervals){
            starts.add(sublists[0]);
            ends.add(sublists[1]);
        };
        Collections.sort(starts);
        Collections.sort(ends);
        System.out.println(starts);
        System.out.println(ends);

        for(int i=0;i<starts.size();i++){
            if(starts.get(i+1) < ends.get(i)){
                return false;
            }
        }

        return true;

    }

    public static ListNode reverseLinkedList20(ListNode head){

        ListNode prev = null;
        ListNode current = head;

        while(current != null){
            ListNode temp = current;
            current.next = prev;
            prev = current;
            current = head;

        }

        return prev;
    }

    public static boolean hasCycle21(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null  && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }

        return false;
    }

    public static int numIslands22(char[][] grid){

        int count =0;
        for(int row=0;row<grid.length;row++){
            for(int col=0;col<grid[0].length;col++){
                if(grid[row][col] == '1'){
                    count +=1;

                    dfs(grid,row,col);
                }
            }
        }
        return count;
    }

    public static void dfs(char[][] grid,int row,int col){
        if(row< 0 || row >= grid.length || col <0 || col >=grid[0].length || grid[row][col]=='0'){

            return ;
        }

        grid[row][col] = '0';
        dfs(grid,row+1,col);
        dfs(grid,row-1,col);
        dfs(grid,row,col+1);
        dfs(grid,row+1,col-1);
    }

    public static boolean validParethesis23(String s){
        Stack<Character> stack = new Stack<>();

        for(Character ch:s.toCharArray()){
            if(ch == '{' || ch == '[' || ch=='('){
                stack.push(ch);
            }else if(ch == '}' && !stack.isEmpty() && stack.peek() == '{'){
                stack.pop();
            }
            else if(ch == ']' && !stack.isEmpty() && stack.peek() == '['){
                stack.pop();
            }
            else if(ch == ')' && !stack.isEmpty() && stack.peek() == '('){
                stack.pop();
            }
            else{
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static List<String> fizzBuzz24(int n){

        List<String> result = new ArrayList<>();

        for(int i=0;i<=n;i++){

            if(i%3==0 && i%5==0){
                result.add("FizzBuzz");
            }else if(i%3==0){
                result.add("Fizz");
            }
            else if(i%5 ==0){
                result.add("Buzz");

            }
            else{
                result.add(Integer.toString(i));
            }
        }

        return result;
    }

    public static int firstUniq25(String s){
        Map<Character,Integer> map = new HashMap<>();
        for(Character ch:s.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        for(int i=0;i<s.length();i++){
            char sh = s.charAt(i);
            if(map.get(sh)==1){
                return i;
            }
        }

        return -1;
    }

    public static String reverseString26(String s){
        StringBuilder result= new StringBuilder();

        for(int i=s.length()-1;i>=0;i--){
            result.append(s.charAt(i));
        }

        return result.toString();
    }

    public static boolean backspace27(String s,String t){
        Stack<Character> s1 = new Stack<>();
        Stack<Character> t1 = new Stack<>();

        for(Character ch:s.toCharArray()){
            if(ch != '#'){
                s1.push(ch);
            }else if(!s1.isEmpty()){
                s1.pop();
            }
        }
        for(Character ch:t.toCharArray()){
            if(ch != '#'){
                t1.push(ch);
            }else if(!t1.isEmpty()){
                t1.pop();
            }
        }

        return s1.isEmpty() && t1.isEmpty();
    }

    public static void moveZeros28(int[] nums){

        int index =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] !=0) {
                nums[index++] = nums[i];
            }
        }

        for(int i=index;i<nums.length;i++){
            nums[i] = 0;
        }
    }

    public int[] intersection29(int[] nums1,int[] nums2){
        Set<Integer> set = new HashSet<>();
        for(int num:nums1){
            set.add(num);
        }

        Set<Integer> intersect = new HashSet<>();

        for(int num:nums2){
            if(set.contains(num)){
                intersect.add(num);
            }
        }

        int[] results = new int[intersect.size()];
        int index =0;
        for(int num:intersect){
            results[index++] = num;
        }

        return results;
    }

public static int removeDuplicatesFromSortedArray30(int[] nums){
        int index=1;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] != nums[i+1]){
                nums[index++] = nums[i+1];
            }
        }

        return index;
}

public static int findKthLargestElement31(int[]nums,int k){
        // min heap since  we need only biggest numbers we kick out small numbers

    Queue<Integer> queue = new PriorityQueue<>();

    for(int i:nums){
        queue.add(i);
        if(queue.size() > k){
            queue.remove();
        }
    }

    return queue.remove();
}

public static String addBinary32(String a,String b){

        StringBuilder str = new StringBuilder();
        int i = a.length()-1;
        int j = b.length()-1;
        int carry =0;

        while(i >=0 || j >=0) {
            int sum = carry;

            if(i>=0){
                sum += a.charAt(i) - '0';
                i--;
            }
            if(j>=0){
                sum += b.charAt(j)-'0';
                j--;
            }
            str.insert(0,sum%2);
            carry = sum/2;
        }
        if(carry >0){
            str.insert(0,1);
        }

        return str.toString();
}

public static String addString33(String num1,String num2) {
    int i = num1.length() - 1;
    int j = num2.length() - 1;
    StringBuilder str = new StringBuilder();
    int carry = 0;
    while (i >= 0 || j >= 0) {
        int sum = carry;

        if (i >= 0) {
            sum += num1.charAt(i)-'0';
            i--;
        }
        if (j >= 0) {
            sum += num2.charAt(j)-'0' ;
            System.out.println(num2.charAt(j));
            j--;
        }
        str.append(sum % 10);
        carry = sum / 10;
    }
    if (carry != 0) {
        str.append(carry);
    }

    return str.reverse().toString();
}

public static ListNode reverseList34(ListNode head){
        Stack<ListNode> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.next;
        }
        ListNode dummy = new ListNode(-1);
        head = dummy;
        while(!stack.isEmpty()){
            ListNode current = stack.pop();
            head.next = new ListNode(current.val);
            head = head.next;
        }
        return dummy.next;
}

public static String frequencySort35(String s){

        Map<Character, Integer> map = new HashMap<>();
        for(Character c:s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        Queue<Character> queue = new PriorityQueue<>((a,b)->map.get(b)-map.get(a));

        queue.addAll(map.keySet());
    System.out.println(queue);
    StringBuilder str = new StringBuilder();
    while(!queue.isEmpty()){
        char current = queue.remove();
        for(int i=0;i<map.get(current);i++){
            str.append(current);
        }
    }
    return str.toString();
}

public static int[] sortArrayByParity36(int[] nums){

        int index=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] %2 ==0){
                int temp= nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }
        }

        return nums;
}

public static void reverseArrayInPlace37(int[] nums){

        int left =0;
        int right = nums.length-1;
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left +=1;
            right -=1;
        }

    System.out.println(Arrays.toString(nums));


}

public static int[] twoSumSortedArray38(int[] nums,int target){
        int left =0;
        int[] results = new int[2];
        int right = nums.length-1;
        while(left < right){
            int sum = nums[left]+nums[right];

            if(sum == target){
                results[0] = left;
                results[1] = right;
            }
            left +=1;
            right -=1;
        }
        return results;
}

public ListNode mergeTwoLists39(ListNode l1,ListNode l2){

        ListNode dummy = new ListNode(0);
        ListNode result = dummy;

        while(l1 != null && l2 != null){

            if(l1.val < l2.val){
                dummy.next = l1;
                l1 = l1.next;
            }else{
                dummy.next = l2;
                l2 = l2.next;
            }

            dummy = dummy.next;
        }

        if(l1 != null){
            dummy.next = l1;
            l1 = l1.next;
        }

    if(l2 != null){
        dummy.next = l2;
        l2 = l2.next;
    }

    return result.next;
}

public static int subArraySum40(int[] nums,int k){

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0,1);
        int sum =0;
        int result =0;

        for(int i=0;i<nums.length;i++){

            sum +=nums[i];

            if(map.containsKey(sum-k)){
                result += map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }

        return result;

}

public static void rotate41(int[] nums,int k){

        k = k%nums.length;

        reverse42(nums,0,nums.length-1);
        reverse42(nums,0,k-1);
        reverse42(nums,k,nums.length-1);
}

public static void reverse42(int[]nums,int left,int right){

        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left +=1;
            right -=1;
        }
}

public static int[] searchRange43(int[]nums,int target){

        int[] results = new int[2];

        results[0] = firstIndex(nums,target);
        results[1] = secondIndex(nums,target);

        return results;
}

public static int firstIndex(int[]nums, int target){

        int index = -1;

        int left =0;
        int right = nums.length-1;

        while(left<=right){
            int mid = left + (right-left)/2;

            if(nums[mid] >= target){
                left = mid-1;
            }else{
                right = mid+1;
            }

            if(nums[mid] == target){
                mid = index;
            }
        }
        return index;
}
    public static int secondIndex(int[]nums, int target){

        int index = -1;

        int left =0;
        int right = nums.length-1;

        while(left<=right){
            int mid = left + (right-left)/2;

            if(nums[mid] <= target){
                right = mid+1;
            }else{
                left = mid-1;
            }

            if(nums[mid] == target){
                mid = index;
            }
        }
        return index;
    }

    public ListNode deleteDuplicates44(ListNode head){
        ListNode currentNode = head;
        while(currentNode != null && currentNode.next != null){
            if(currentNode.val == currentNode.next.val){
                currentNode.next = currentNode.next.next;
            }
            else{
                currentNode = currentNode.next;
            }
        }
        return head;
    }

    public static String longestCommonPrefix45(String[] strs){
        String prefix = strs[0];

        for(int i=0;i<strs.length;i++){
            while(strs[i].indexOf(prefix) != 0){
                prefix = prefix.substring(0,prefix.length()-1);
            }
        }

        return prefix;
    }
    public int minAddToMakeValid46(String s){
        Stack<Character> stack = new Stack<>();

        for(Character ch:s.toCharArray()){
            if(ch== '('){
                stack.push(ch);
            }else if(ch == ')' && !stack.isEmpty()){
                stack.pop();
            }else{
                stack.push(ch);
            }
        }
        return stack.size();
    }

    public ListNode removeElements47(ListNode head,int val){
        ListNode dummy = new ListNode(0);
        ListNode output = dummy;
        dummy.next = head;

        while(head != null){
            if(head.val == val){
                head = head.next;
                dummy.next = head;
            }
            else{
                head = head.next;
                dummy = dummy.next;
            }
        }

        return output.next;
    }

    public static int pivotIndex48(int[] nums){
        int totalSum =0;
        for(int num:nums){
            totalSum +=num;
        }

        int accumSum = 0;
        for(int i=0;i<nums.length;i++){
            if(totalSum-nums[i]-accumSum == accumSum) return i;

            accumSum += nums[i];
        }
        return -1;
    }

    public static  int fib49(int n){
        if(n <=1){
            return n;
        }
        else {
            return fib49(n-1)+fib49(n-2);
        }

    }

    public static int fiboOptimized50(int n, int[] memo){
        if(memo[n] != 0){
            return memo[n];
        }

        if(n==0 || n==1) return n;

        int result = fiboOptimized50(n-1,memo)+ fiboOptimized50(n-2,memo);

        memo[n] = result;

        return result;
    }

    public ListNode addTwoNumbersSolution51(ListNode l1,ListNode l2){
        ListNode dummy= new ListNode(0);
        ListNode result = dummy;

        int carry =0;

        while (l1 != null || l2 != null || carry !=0){
            if(l1 != null){
                carry += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                carry += l2.val;
                l2 = l2.next;
            }

            dummy.next = new ListNode(carry%10);
            carry = carry/10;

            dummy = dummy.next;
        }

        return result.next;
    }

    public static List<String> fizzBuzz52(int n){

        List<String> results = new ArrayList<>();
        for(int i=1;i<=n;i++){

            if(i%3== 0 && i%5 ==0){
                results.add("FizzBuzz");
            }
            else if(i%3 ==0){
                results.add("Fizz");
            }
            else if(i%5 ==0){
                results.add("Buzz");
            }
            else{
                results.add(i+"");
            }
        }
        return results;
    }

    public static int maxConsecutivesOnes53(int[] nums){

        int max =0;
        int window_start =0;

        for(int i=0;i<nums.length;i++){
            if(nums[i] ==1){
                max = Math.max(max,i-window_start+1);
            }
            else{
                window_start = i+1;
            }
        }
        return max;
    }

    public static boolean isPalindromeLinkedList54(ListNode head){

        List<Integer> list = new ArrayList<>();

        ListNode current = head;

        while(current != null){
            list.add(current.val);
            current = current.next;
        }

        int left =0;
        int right = list.size()-1;

        while(left < right){
            if(!list.get(left).equals(list.get(right))){
                return false;
            }
            left +=1;
            right -=1;

        }
        return true;
    }
    public static int[] productArrayExceptSelf55(int[] nums){
        int[] output = new int[nums.length];
        Arrays.fill(output,1);

        int mul = 1;

        for(int i=0;i<nums.length;i++){

            output[i] = mul*output[i];
            mul = nums[i]*mul;
        }
        mul =1;

        for(int i=nums.length-1;i>=0;i--){

            output[i] = output[i]*mul;
            mul = nums[i]*mul;
        }
        return output;
    }

    public int romanToInt56(String s){

        int result = 0;
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        for(int i=0;i<s.length();i++){

            // IV
            if(i<s.length()-1 && map.get(s.charAt(i)) < map.get(s.charAt(i+1))){
                result -=map.get(s.charAt(i));
            }else{
                result  += map.get(s.charAt(i));
            }
        }
        return result;
    }

    public static int romanToInt67(String s){

        int result =0;
        Map<String,Integer> map = new HashMap<>();

        for(int i=0;i< s.length();i++){
            if(i<s.length()-1 && map.get(s.charAt(i)) < map.get(s.charAt(i+1))){
                result -=map.get(s.charAt(i));
            }else{
                result += map.get(s.charAt(i));
            }
        }

        return result;
    }

    public static int reverse68(int x) {
        int y = Math.abs(x);
        StringBuilder str = new StringBuilder();
        String s = Integer.toString(y);
        for(int i=s.length()-1;i>=0;i--){
            str.append(s.charAt(i));
        }
        System.out.println(str);
        int rs = Integer.parseInt(str.toString());
        if(x <0){
            return rs*(-1);
        }
        return rs;

    }

    public List<List<Character>> permute(char[] letters){
        List<List<Character>> res = new ArrayList<>();
        dfs2(new ArrayList<>(),new boolean[letters.length],res,letters);

        return res;
    }

    public static void dfs2(List<Character> path, boolean[] used,List<List<Character>> res,char[] letters){

        // base case
        if(path.size()== used.length){
            // deep copy
            res.add(new ArrayList<Character>(path));
            return;
        }
        // recursive case
        for(int i=0;i<used.length;i++){
            if(used[i]) continue;

            path.add(letters[i]);
            used[i] = true;
            dfs2(path,used,res,letters);

            path.remove(path.size()-1);
            used[i] = false;
        }

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode output = dummy;

        while(l1 != null && l2 != null){

            if(l1.val < l2.val){
                dummy.next = l1;
                l1 = l1.next;
            }else{
                dummy.next = l2;
                l2 = l2.next;
            }

            dummy = dummy.next;
        }

        if(l1 != null){
            dummy.next = l1;
        }
        if(l2 != null){
            dummy.next = l2;
        }

        return output.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        //Input: lists = [[1,4,5],[1,3,4],[2,6]]
    //Output: [1,1,2,3,4,4,5,6]

      //todo create dummy and output nodes

        ListNode dummy = new ListNode(0);
        ListNode output = dummy;

      //todo loop through the lists and dump each node values to the queue

        Queue<Integer> queue = new PriorityQueue<>();

        for(ListNode head:lists){
            while(head != null){
                queue.add(head.val);

                head = head.next;
            }
            //todo construct the dummy node by removing queue items
            while(!queue.isEmpty()){
                dummy.next = new ListNode(queue.remove());
                dummy = dummy.next;
            }
        }
        return output.next;
    }

    public static int fourSum(int[] A,int[] B, int[] C, int[] D){

        //Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, retu
        //rn the number of tuples (i, j, k, l) such that
        // 0 <= i, j, k, l < n
        // nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
        //Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
        //Output: 2

        //todo loop through the first two arrays and find sums and check for the sum key in the map

        Map<Integer,Integer> map = new HashMap<>();
        int la = A.length;
        int lb = B.length;
        int lc = C.length;
        int ld = D.length;
        int results =0;

        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){

                int sum = A[i]+B[i];

                if(!map.containsKey(sum)){
                    map.put(sum,map.getOrDefault(sum,0)+1);
                }
            }
            // todo loop through the last two arrays and find target
            for(int k=0;k<lc;k++){
                for(int m=0;m<ld;m++){
                    int sum  = C[k]+D[m];
                    int target = -sum;
                    if(map.containsKey(target)){
                        results += map.get(target);
                    }
                }
            }
        }
        //todo return the counts
        return results;

    }

    public static Set<Integer> intersect(int[] nums1, int[] nums2) {
        //Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        //Output: [4,9]
        //Explanation: [9,4] is also accepted.
        int l1 = nums1.length;
        int l2 = nums2.length;


        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set = new HashSet<>();

        for(int num:nums1){
            set1.add(num);
        }
        for(int num:nums2){
            set2.add(num);
        }

        if(set1.size() > set2.size()){

            for(int num:set1){
                if(set2.contains(num)){
                    set.add(num);
                }
            }
        }
        else{
            for(int num:set2){
                if(set1.contains(num)){
                    set.add(num);
                }
            }
        }

      return set;
    }

    public int[] topKFrequent(int[] nums, int k) {

        // Example 1:
        // Input: nums = [1,1,1,2,2,3], k = 2
        //Output: [1,2]
        Map<Integer,Integer> map = new HashMap<>();


        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        Queue<Integer> queue = new PriorityQueue<>((a,b)->map.get(b)-map.get(a));
        for(int key:map.keySet()){
            queue.add(key);

            if(queue.size() >k){
                queue.poll();
            }
        }

        int[] results = new int[queue.size()];

        for(int i=0;i<queue.size();i++){
            results[i] = queue.remove();
        }
        return results;
    }

    //twitter interview

public static List<List<Integer>> subsets(int[] nums){

        List<List<Integer>> results = new ArrayList<>();
        generateSubsets(0,nums,new ArrayList<>(),results);
        return results;
}
public static void generateSubsets(int index, int[] nums,List<Integer> current, List<List<Integer>> subsets){
        subsets.add(new ArrayList<>(current));

        for(int i=index;i<nums.length;i++){
            current.add(nums[i]);
            generateSubsets(index+1,nums,current,subsets);
            current.remove(current.size()-1);
        }
}

public static List<List<Integer>> minimumAbsDiff(int[] arr){
        List<List<Integer>> result = new ArrayList<>();

        int min = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++) {
            int diff = arr[i + 1] - arr[i];

            if (diff < min) {
                min = diff;
                result.clear();
                result.add(Arrays.asList(arr[i + 1], arr[i]));
            } else if (diff == min) {
                result.add(Arrays.asList(arr[i + 1], arr[i]));
            }

        }
    return result;

    }

    public static int minMoves(Integer[]nums){
        int min = Collections.min(Arrays.asList(nums));
        int move =0;

        for(int i=0;i< nums.length;i++){
            move += nums[i]-min;
        }

        return move;
    }

    public boolean exist(char[][] board, String word) {
        for(int row=0;row<board.length;row++){
            for(int col =0;col<board[0].length;col++){
                if(board[row][col] == word.charAt(0) && searchWordDfs(board,row,col,0,word)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchWordDfs(char[][] board,int row,int col, int index ,String word){
        //edge case
        if(index == word.length()){
            return true;
        }
        if(row<0 || row >= board.length|| col <0 || col > board[row].length || board[row][col] != word.charAt(index)){
            return false;
        }
        char temp = board[row][col];
        board[row][col] = ' ';
        boolean found = searchWordDfs(board,row+1,col,index+1,word)
                || searchWordDfs(board,row-1,col,index+1,word)
                || searchWordDfs(board,row,col+1,index+1,word);

        board[row][col] = temp;

        return found;
    }

    public void moveZeroes(int[] nums) {
        // Input: nums = [0,1,0,3,12]
        //Output: [1,3,12,0,0]
        int index =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for(int i=index;i<nums.length;i++){
            nums[i] =0;
        }
    }

    public static int numIsLands(char[][] grid){
        if(grid == null || grid.length ==0){
            return 0;
        }

        int numberOfIsland = 0;

        for(int row=0;row<grid.length;row++){
            for(int col=0;col<grid[0].length;col++){
                if (grid[row][col] == '1') {

                    numberOfIsland += dfsNumberIsland(grid,row,col);
                }
            }
        }
        return numberOfIsland;
    }

    public static int dfsNumberIsland(char[][]grid,int row,int col){
        // edge case

        if(row< 0 || row>= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0'){
            return 0;
        }

        grid[row][col] = '0';
        dfsNumberIsland(grid,row+1,col);
        dfsNumberIsland(grid,row-1,col);
        dfsNumberIsland(grid,row,col+1);
        dfsNumberIsland(grid,row,col-1);

        return 1;
    }

    public static boolean validParenthesisSolution(String s){
        Stack<Character> stack = new Stack<>();

        for(Character c:s.toCharArray()){
            if(c== '{' || c== '(' || c=='['){
                stack.push(c);
            }else if(c == '}' && stack.peek() == '{' && !stack.isEmpty()){
                stack.pop();
            }
            else if(c == ']' && stack.peek() == '[' && !stack.isEmpty()){
                stack.pop();
            }
            else if(c == ')' && stack.peek() == '(' && !stack.isEmpty()){
                stack.pop();
            }
            else{
               return false;
            }
        }
         return stack.isEmpty();
    }

    public List<String> fizzBuzz(int n){
        List<String> result = new ArrayList<>();

        for(int i=1;i<n;i++){
            if(i%3==0 && i%5==0){
                result.add("FizzBuzz");
            }
            else if(i%3 ==0){
                result.add("Fizz");
            }
            else if(i%5 ==0){
                result.add("Buzz");
            }
            else{
                result.add(Integer.toString(i));
            }
        }

        return result;
    }

    public static int[] twoSumSolution(int[] nums,int target){
        Arrays.sort(nums);
        int[] result = new int[2];

        int left =0;
        int right = nums.length-1;

        while(left < right){
            int temp = nums[left]+nums[right];

            if(temp ==target) {
                result[0] = left;
                result[1] = right;

                left += 1;
                right -= 1;
            }
            else if(temp > target){
                right -=1;
            }
            else{
                left -=1;
            }
        }
        return result;
    }

    public static int[] twoSumSolution2(int[] nums,int target){
        int[] results = new int[2];

        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int temp = target-nums[i];
            if(map.containsKey(temp)){
                results[0] = map.get(temp);
                results[1] = i;
            }
            map.put(nums[i],i);
        }

        return results;
    }

    public static int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (stack.peek() == ')' && c == '(' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }

        }
        return stack.size()*2;
    }

        public static int binarySearch(int[] nums, int target){
            int left =0;
            int right = nums.length-1;

            while(left<right){
                int mid = left+(right-left)/2;

                if(nums[mid] == target){
                return mid;
                }else if(nums[mid] > target){
                    left = mid;
                }else{
                    right = mid+1;
                }
            }
            return -1;
        }

public static int pivotIndex(int[] nums){
        int totalSum =0;
        for(int num:nums){
            totalSum +=num;
        }
        int accumSum = 0;
        for(int i=0;i<nums.length;i++){
            if(totalSum-nums[i]-accumSum == accumSum) return i;
            accumSum += nums[i];
        }
        return -1;
}
public static void rotateHelper(int[] nums,int right,int left){
        while(left < right){
            int temp =nums[right];
            nums[right] = nums[left];
            nums[left]= temp;

            left +=1;
            right -=1;
        }
}

public static void rotate(int[] nums,int k){
        k = k%nums.length;
        rotateHelper(nums,0,nums.length-1);
        rotateHelper(nums,0,k-1);
        rotateHelper(nums,k,nums.length-1);
}

public static void reverseStringArrayInPlace(char[] s){
        int left =0;
        int right = s.length-1;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] =temp;

            left +=1;
            
        }
}



    public static void main(String[] args){
        int[] nums = new int[]{2,7,11,15};
        System.out.println(Arrays.toString(twoSumSolution(nums, 9)));
        System.out.println(Arrays.toString(twoSumSolution2(nums,9)));
       // Integer[] nums = new Integer[]{1,2,3};
       // System.out.println(minMoves(nums));
       // int[] nums1 = new int[] {4,9,5};
       // int[] nums2 = new int[]{9,4,9,8,4};
        //System.out.println(intersect(nums1,nums2));
        //System.out.println(reverse68(-123));

       // String paragraph = "BOb hit a ball, the hit Ball flew far after it was hit";

        //String[] banned = new String[] {"hit"};
       // System.out.println(mostCommonWord(paragraph,banned));

       // System.out.println(isAnagram("anagramm","nagaram"));

       // String input = "ab-cd";
      //  System.out.println(reverseOnlyLetters(input));
        //System.out.println(validPalindrome("abca"));

       // String s = "abba./";
       // String sh = "babad";
       // System.out.println(isPalindrome(s));
       // System.out.println(longestPalindrome2(sh));
        //System.out.println(longestSubWithouRepeatingChar("abcabcbb"));

        //int[] nums = new int[]{2,7,11,15};

        //System.out.println(twoSum(nums,9));
       // System.out.println(Arrays.toString(twoSum(nums,9)));

        //int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
      // int[][] intervals = new int[][]{{0,30},{15,10},{10,20}};

       // System.out.println(maxSubArray(nums));
       // System.out.println(canAttendMeetings(intervals));

        //String s1 = "leetcode";
       // String s2 = "loveleetcode";

        //System.out.println(firstUniq(s2));

        //System.out.println(reverseString("hello"));

       // System.out.println(addString("11","123"));

        //System.out.println(frequencySort35("treettteee"));
      //  System.out.println(isPalindrome11("abba"));

        //int[] nums = new int[]{1,5,2,7,4,6};
        //System.out.println(sortArrayInPlace(nums);
       // System.out.println(Arrays.toString(sortArrayInPlace(nums)));
        //reverseArrayInPlace37(nums);


    }
}
