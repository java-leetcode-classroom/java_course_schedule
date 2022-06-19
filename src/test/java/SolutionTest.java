import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    final private Solution sol = new Solution();
    @Test
    void canFinishExample1() {
        assertEquals(true, sol.canFinish(2, new int[][]{{1,0}}));
    }

    @Test
    void canFinishExample2() {
        assertEquals(false, sol.canFinish(2, new int[][]{{1,0}, {0,1}}));
    }
}