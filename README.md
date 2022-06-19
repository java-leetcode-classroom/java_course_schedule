# java_course_schedule

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `bi` first if you want to take course `ai`.

- For example, the pair `[0, 1]`, indicates that to take course `0` you have to first take course `1`.

Return `true` if you can finish all courses. Otherwise, return `false`.

## Examples

**Example 1:**

```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.

```

**Example 2:**

```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

```

**Constraints:**

- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= 5000`
- `prerequisites[i].length == 2`
- `0 <= ai, bi < numCourses`
- All the pairs prerequisites[i] are **unique**.

## 解析

給定一個正整數 numCourses，代表有 0 到 numCourses - 1的課程

還有一個 矩陣 prerequisites 每個 entry [$a_i,$ $b_i$] 代表 要完成 $a_i$ 課程必須先完成 $b_i$ 課程

題目要求寫出一個演算法去判斷給定的 numCourses, 還有 prerequisites 能不能夠完成

根據 prerequisites 可以先畫出 dependency 關係圖

![](https://i.imgur.com/uG1RAT4.png)

根據 preMap 來做 DFS 並且紀錄每個走訪過的 node

當發現遇到重複拜訪的點代表出現了 dependency 循環 所以 return false

如果走訪完所有 dependency 都沒有重複出現點代表可以完成

![](https://i.imgur.com/yzYxNet.png)
## 程式碼
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> preCourseMap = new HashMap<>();
        for (int i = 0; i < numCourses;i++) {
            preCourseMap.put(i, new ArrayList<>());
        }
        HashSet<Integer> visit = new HashSet<>();
        for (int[] dependency : prerequisites) {
            List<Integer> pre = preCourseMap.get(dependency[0]);
            pre.add(dependency[1]);
        }
        for (int idx = 0 ; idx < numCourses; idx++) {
            if (!dfs(idx, preCourseMap, visit)) {
                return false;
            }
        }
        return true;
    }
    public boolean dfs(int course, HashMap<Integer, List<Integer>> preCourseMap, HashSet<Integer> visit) {
        if (visit.contains(course)) {
            return false;
        }
        List<Integer>  curPre = preCourseMap.get(course);
        if (curPre.size() == 0) {
            return true;
        }
        visit.add(course);
        for (Integer preCourse : curPre) {
            if (!dfs(preCourse, preCourseMap, visit)) {
                return false;
            }
        }
        visit.remove(course);
        curPre.clear();
        return true;
    }
}

```
## 困難點

1. 理解如何找出有循環的 dependency
2. 理解透過 adjacency map 就可以表示每個 course 的 dependency 順序
3. 理解透過 HashSet 儲存找到有走訪過代表無法完成

## Solve Point

- [x]  理解如何找出有循環的 dependency
- [x]  透過 adjacency map 就可以表示每個 course 的 dependency 順序
- [x]  透過 DFS 來走訪 dependency graph
- [x]  透過 hashSet 儲存已經走訪過的 課程，如果有遇到則代表有 cycle 代表無法完成