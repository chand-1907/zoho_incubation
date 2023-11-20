L3 Task 10
Timings: 4:30 to 5:30 PM 


1.Boggle Board
Generate a list of possible words from a character matrix

 

Given an M × N boggle board, find a list of all possible words that can be formed by a sequence of adjacent characters on the board.

 

We are allowed to search a word in all eight possible directions, i.e., North, West, South, East, North-East, North-West, South-East, South-West, but a word should not have multiple instances of the same cell.

 

 

Consider the following the traditional 4 × 4 boggle board. If the input dictionary is [START, NOTE, SAND, STONED], the valid words are [NOTE, SAND, STONED].

 



 

Practice This Problem

We can use Depth–first search (DFS) to solve this problem. The idea is to start from each character in the matrix and explore all eight paths possible and recursively check if they lead to a solution or not. To make sure that a word doesn’t have multiple instances of the same cell, keep track of cells involved in the current path in the matrix, and before exploring any cell, ignore the cell if it is already covered in the current path.

 

To find all possible movements from a cell, we can use an array to store the relative position of movement from any cell. For example, if the current cell is (x, y), we can move to (x + row[k], y + col[k]) for 0 <= k <=7 using the following array:

 

int row[] = { -1, -1, -1, 0, 0, 1, 1, 1 }

int col[] = { -1, 0, 1, -1, 1, -1, 0, 1 }

So, from position (x, y), we can move to:

 

(x – 1, y – 1)

(x – 1, y)

(x – 1, y + 1)

(x, y – 1)

(x, y + 1)

(x + 1, y – 1)

(x + 1, y)

(x + 1, y + 1)

 



2.Subdomains counts
A website domain "discuss.ashwin.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "ashwin.com" and at the lowest level, "discuss.ashwin.com". When we visit a domain like "discuss.ashwin.com", we will also visit the parent domains "ashwin.com" and "com" implicitly.

A count-paired domain is a domain that has one of the two formats "rep d1.d2.d3" or "rep d1.d2" where rep is the number of visits to the domain and d1.d2.d3 is the domain itself.

For example, "9001 discuss.ashwin.com" is a count-paired domain that indicates that discuss.leetcode.com was visited 9001 times.

Given an array of count-paired domains cpdomains, return an array of the count-paired domains of each subdomain in the input. You may return the answer in any order.

 Example 1:

Input: cpdomains = ["9001 discuss.ashwin.com"]
Output: ["9001 ashwin.com","9001 discuss.ashwin.com","9001 com"]
Explanation: We only have one website domain: "discuss.ashwin.com".
As discussed above, the subdomain "ashwin.com" and "com" will also be visited. So they will all be visited 9001 times.
Example 2:

Input: cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
Explanation: We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times.
For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
