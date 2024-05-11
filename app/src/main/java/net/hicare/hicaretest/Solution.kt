package net.hicare.hicaretest

/**
 *
 * String s가 주어졌을 때,
 * 반복되는 문자가 없는 가장 긴 부분 문자열(substring)의 길이를 구해주세요.
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: 요구사항에 맞는 문자열은 "abc" 입니다. 정답으로 문자열의 길이인 3을 리턴합니다.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: 요구사항에 맞는 문자열은 "b" 입니다. 정답으로 문자열의 길이인 1을 리턴합니다.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: 요구사항에 맞는 문자열은 "wke" 입니다. 정답으로 문자열의 길이인 3을 리턴합니다.
 * "pwke"는 부분 문자열(substring)이 아니기 때문에 정답이 될 수 없습니다.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */

fun main() {
    val input = "abcabcbb"
//    val input = "bbbbb"
//    val input = "pwwkew"
//    val input = "a"
//    val input = "sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
    println(lengthOfLongestSubstring(input))
}

fun lengthOfLongestSubstring(input: String): Int {
    val set = hashSetOf<Char>()
    val length = input.length
    var start = 0
    var end = 0
    var longest = 0

    while (end < length) {
        if (input[end] in set) {
            set.remove(input[start++])
        } else {
            set.add(input[end++])
            longest = Integer.max(longest, end - start)
        }
    }
    return longest
}
