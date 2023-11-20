L3 Task 13
Days: Saturday

1.Word Search
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 Example 1:



Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:



Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []
 

2.Unique Path

We define the string base to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so base will look like this:

 

"...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Given a string s, return the number of unique non-empty sub strings of s are present in base.

 

Example 1:

Input: s = "a"

Output: 1

Explanation: Only the substring "a" of s is in base.

 

Example 2:

Input: s = "cac"

Output: 2

Explanation: There are two sub strings ("a", "c") of s in base.

 

Example 3:

Input: s = "zab"

Output: 6

Explanation: There are six sub strings ("z", "a", "b", "za", "ab", and "zab") of s in base.


3.UTF-8 Validation

Given an integer array data representing the data, return whether it is a valid UTF-8 encoding (i.e. it translates to a sequence of valid UTF-8 encoded characters).

A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

 

For a 1-byte character, the first bit is a 0, followed by its Unicode code.

For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the most significant 2 bits being 10.

This is how the UTF-8 encoding would work:

 

     Number of Bytes |        UTF-8 Octet Sequence

                                      |                         (binary)

   --------------------+-----------------------------------------

            1                        |   0xxxxxxx

            2 |   110xxxxx 10xxxxxx

            3                        |   1110xxxx 10xxxxxx 10xxxxxx

            4 |   11110xxx 10xxxxxx 10xxxxxx 10xxxxxx

x denotes a bit in the binary form of a byte that may be either 0 or 1.

 

Note: The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means each integer represents only 1 byte of data.

 

Example 1:

Input: data = [197,130,1]

Output: true

Explanation: data represents the octet sequence: 11000101 10000010 00000001.

It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.

 

Example 2:

Input: data = [235,140,4]

Output: false

Explanation: data represented the octet sequence: 11101011 10001100 00000100.

The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.

The next byte is a continuation byte which starts with 10 and that's correct.

But the second continuation byte does not start with 10, so it is invalid.

 

4.K Closest Point Origin

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

 

Example 1:

                          

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.