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
