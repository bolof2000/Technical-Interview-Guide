package org.example;

import java.util.*;

public class TwiiterInterview {


    public static void main (String[]args){

        String s = "whitespaces    4";
        System.out.println(reverseWordsInString(s));
    }

    public boolean isPalindromeLinkedist(ListNode head){
        ListNode current = head;
        List<Integer> list = new ArrayList<>();

        while(current != null){
            list.add(current.val);
            current = current.next;
        }

        int left =0;
        int right = list.size()-1;

        while(left < right){
            if(list.get(left) != list.get(right)){
                return false;
            }
            right -=1;
            left +=1;
        }
        return true;
    }

    public static List<String> generateParen(int n){
        // Input: n = 3
        //Output: ["((()))","(()())","(())()","()(())","()()()"]
        List<String> result = new ArrayList<>();
        backtrack(result,"",0,0,n);
        return result;
    }
    public static void backtrack(List<String> output,String currentString,int open,int close,int max){
        if(currentString.length() == max*2){
            output.add(currentString);
            return;
        }

        if(open <max)backtrack(output,currentString+"(",open+1,close,max);
        if(close<open)backtrack(output,currentString+")",open,close+1,max);
    }



    public static String reverseWordsInString(String s){
        String[] str = s.split(" ");
        StringBuilder result = new StringBuilder();
        for(int i=str.length-1;i>=0;i--){
            result.append(" ");
            result.append(str[i]);
        }

        return result.toString();

    }

    public static String encodeString2(String[] strs){
        String encoded= "";
        for(String str:strs){
            int len = str.length();

            encoded = Integer.toString(len)+"/"+str;
        }
        return encoded;
    }

    public static List<String> decodeString2(String s){

        List<String> result = new ArrayList<>();

        int position = 0;

        while(position < s.length()-1){
            int slashPosition = s.indexOf("/");
            int len =Integer.valueOf(s.charAt(slashPosition-1));
            position = position+1;

            result.add(s.substring(position,position+len));
            position +=len;

        }
        return result;

    }

    public String addStrings(String num1, String num2) {
        //Input: num1 = "456", num2 = "77"
        //Output: "533"

        int i = num1.length()-1;
        int j= num2.length()-1;
        StringBuilder str = new StringBuilder();
        int carry =0;
        while(i>=0 || j >=0){
            int sum = carry;

            if(i >=0){
                sum += num1.charAt(i)-'0';
                i--;
            }

            if(j >=0){
                sum += num1.charAt(j)-'0';
                j--;
            }

            str.append(sum%10);
            carry = sum/10;

        }

        if(carry !=0);
        str.append(carry);
        return str.reverse().toString();

    }
    public static int minMoves(Integer[] nums) {
        int min = Collections.min(Arrays.asList(nums));
        int move = 0;

        for (int i = 0; i < nums.length; i++) {
            move += nums[i] - min;
        }

        return move;
    }

    public int findPairs(int[] nums, int k) {

        int result = 0;

        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }


        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            int x = entry.getKey();
            int val = entry.getValue();
            if (k > 0 && counter.containsKey(x + k)) {
                result++;
            } else if (k == 0 && val > 1) {
                result++;
            }
        }
        return result;
    }

    public static int[][] merge(int[][] intervals) {
        //Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//
//
// Example 2:
//
//
//Input: intervals = [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.

        LinkedList<int[]> merged = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static int linkedListTest(int[] nums) {
        LinkedList<Integer> items = new LinkedList<>();
        for (int num : nums) {
            items.add(num);
        }
        return items.getLast();
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        generate(nums, 0, new ArrayList<>(), results);
        return results;

    }

    public static void generate(int[] nums, int index, List<Integer> current, List<List<Integer>> subsets) {

        subsets.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            generate(nums, i + 1, current, subsets);
            current.remove(current.size() - 1);
        }
    }

    public static boolean exist(char[][] boards, String word) {
        for (int row = 0; row < boards.length; row++) {
            for (int col = 0; col < boards[0].length; col++) {
                if (boards[row][col] == word.charAt(0) && dfsWordSearch(boards, row, col, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfsWordSearch(char[][] board, int row, int col, int index, String word) {

        if (index == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length || col < 0 || col > board[0].length || board[row][col] != word.charAt(index)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = ' ';

        boolean found = dfsWordSearch(board, row + 1, col, index + 1, word)
                || dfsWordSearch(board, row - 1, col, index + 1, word)
                || dfsWordSearch(board, row, col + 1, index + 1, word)
                || dfsWordSearch(board, row, col - 1, index + 1, word);

        board[row][col] = temp;

        return found;

    }

    class WordDictionary {
        Map<Integer, Set<String>> map;

        public WordDictionary() {
            map = new HashMap<>();
        }

        public void addWord(String word) {
            int len = word.length();
            if (!map.containsKey(len)) {
                map.put(len, new HashSet<>());
            }
            map.get(len).add(word);
        }

        public boolean search(String word) {
            int len = word.length();
            if (map.containsKey(len)) {
                for (String w : map.get(len)) {
                    int i = 0;
                    while (i < len && w.charAt(i) == word.charAt(i) || word.charAt(i) == '.') {
                        i++;
                    }
                    if (i == len) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static int minStepsToMakeAnagrams(String s1, String s2) {
        int[] map1 = new int[26];
        int[] map2 = new int[26];

        for (int i = 0; i < s2.length(); i++) {
            map1[s1.charAt(i) - 'a']++;
            map2[s2.charAt(i) - 'a']++;
        }

        int total = 0;
        for (int i = 0; i < map1.length; i++) total = Math.abs(map1[i] - map2[i]);
        return total / 2;
    }

    public static int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }

        return count;

    }

    public static int[] productArrayExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        Arrays.fill(output, 1);

        int mul = 1;
        for (int i = 0; i < nums.length; i++) {
            output[i] = mul * output[i];
            mul = mul * nums[i];
        }
        mul = 1;

        for (int i = nums.length - 1; i > 0; i--) {
            output[i] = mul * output[i];
            mul = mul * nums[i];
        }
        return output;
    }

    public static int maxSubArray(int[] nums) {

        int maxSum = nums[0];
        int currentSum = 0;

        for (int num : nums) {
            if (num < 0) {
                currentSum = 0;
            }
            currentSum += num;

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static String runLineEncoding(String s) {
        StringBuilder str = new StringBuilder();
        int currentLen = 1;

        for (int i = 1; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            char prevChar = s.charAt(i - 1);
            if (currentChar != prevChar || currentLen == 9) {
                str.append(Integer.toString(currentLen));
                str.append(prevChar);
                currentLen = 0;
            }
            currentLen += 1;
        }
        str.append(Integer.valueOf(currentLen));
        str.append(s.charAt(s.length() - 1));

        return str.toString();
    }

    public static String runLenEncoded(String s){
        int currentlen = 1;
        StringBuilder str = new StringBuilder();

        for(int i=1;i<s.length();i++){
            char currentchar = s.charAt(i);
            char prevchar = s.charAt(i-1);

            if(currentchar != prevchar || currentlen==9){
                str.append(Integer.toString(currentlen));
                str.append(prevchar);
                currentlen = 0;
            }
            currentlen +=1;
        }
        str.append(Integer.toString(currentlen));
        str.append(s.charAt(s.length()-1));
        return str.toString();
    }

    public static String encodeString(String[] str) {
        StringBuilder result = new StringBuilder();
        for (String s : str) {
            int len = s.length();
            result.append(Integer.toString(len)).toString();
            result.append("/");
            result.append(s);

        }

        return result.toString();
    }

    public List<String> decodeString(String s) {
        List<String> result = new ArrayList<>();

        int position = 0;
        while (position < s.length()) {
            int slash_position = s.indexOf("/");
            position = slash_position + 1;
            int len = slash_position - 1;
            result.add(s.substring(position, position + len));
            position += 1;
        }
        return result;
    }

    public static int[] moveZeros(int[] nums) {
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    public static void moveElementToTheEnd(int[] nums, int tomove) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            while (left < right && nums[right] == tomove) {
                right -= 1;
            }
            if (nums[left] == tomove) {
                int temp = nums[left];
                nums[left] = nums[right];

            }
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //Input: l1 = [2,4,3], l2 = [5,6,4]
        //Output: [7,0,8]
        //Explanation: 342 + 465 = 807.
        ListNode dummy = new ListNode(0);
        ListNode result = dummy;

        int carry =0;

        while(l1 != null || l2 !=null || carry !=0){
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
    public int lengthOfLongestSubstring(String s) {
        //Input: s = "abcabcbb"
    //Output: 3
    //Explanation: The answer is "abc", with the length of 3.

        Map<Character,Integer> map = new HashMap<>();

        int maxlen =0;
        int windowStart = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if (map.containsKey(ch) && map.get(ch) >= windowStart) {
             windowStart = map.get(ch)+1;
            }
            map.put(ch,i);
            maxlen = Math.max(maxlen,i-windowStart+1);
        }
        return maxlen;

    }

    public static String longestPalindrome(String s) {
        //Input: s = "babad"
        //Output: "bab"
        //Note: "aba" is also a valid answer.

        String longest = "";
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                String subString = s.substring(i,j+1);
                if(subString.length() > longest.length() && isPalindrome(subString)){
                    longest = subString;
                }
            }
        }

        return longest;

    }

    public static boolean isPalindrome(String s){
        int right = s.length()-1;
        int left =0;
        while(left <right){
            if(s.charAt(left) != s.charAt(right)) {
                return false;

            } left += 1;
            right -= 1;
        }

        return true;
    }

    public static int romanToInt(String s) {
    Map<Character,Integer> map = new HashMap<>();
    int result =0;
    map.put('I',1);
    map.put('V',5);
    map.put('X',10);
    map.put('L',50);
    map.put('C',100);
    map.put('D',500);
    map.put('M',1000);

    //IV 1 , 5
    for(int i=0;i<s.length();i++){
     if(i <s.length()-1 && map.get(s.charAt(i)) < map.get(s.charAt(i+1))){
         result -=map.get(s.charAt(i));
     }
     else{
         result += map.get(s.charAt(i));
     }
    }
    return result;

    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for(int i=0;i<strs.length;i++){
            while(strs[i].indexOf(prefix) !=0){
                prefix = prefix.substring(0,prefix.length()-1);
            }
        }
        return prefix;
    }

    public static List<List<Integer>> threesums(int[] nums){
        // Example 1:
        // Input: nums = [-1,0,1,2,-1,-4]
        //Output: [[-1,-1,2],[-1,0,1]]
        Arrays.sort(nums);
        List<List<Integer>> results = new ArrayList<>();
        for(int i=0;i<nums.length-2;i++){
            if(i ==0 || (i>0 && nums[i] != nums[i-1])) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int temp = nums[left] + nums[right] + nums[i];

                    if (temp == 0) {
                        results.add(Arrays.asList(nums[left], nums[right], nums[i]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left += 1;
                        right -= 1;
                    } else if (temp > 0) {
                        right -= 1;
                    } else {
                        left += 1;
                    }
                }
            }
        }
        return results;
    }

    public static int removeDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num:nums){
            list.add(num);
        }

        int i =0;
        while( i < list.size()-1){
            if(list.get(i) == list.get(i+1)){
                list.remove(i);
            }else{
                i +=1;
            }
        }
        return list.size();
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int l3 = nums3.length;
        int l4 = nums4.length;
        int result = 0;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<nums1.length;i++){
            int x = nums1[i];
            for(int j=0;i<nums2.length;j++){
                int y = nums2[j];

                int sum = x+y;
                if(!map.containsKey(sum)){
                    map.put(sum,map.getOrDefault(sum,0)+1);
                }
            }
        }
        for(int k=0;k<nums3.length;k++){
            int x = nums3[k];

            for(int m=0;m<nums4.length;m++){
                int y = nums4[m];

                int target = -(x+y);
                if(map.containsKey(target)){
                    result += map.get(target);
                }
            }
        }
        return result;
    }


    }


