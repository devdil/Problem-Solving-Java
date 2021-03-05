package problems.monthlychallenges.y2021.march;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problems.monthlychallenges.y2021.march.SingleRowKeyboard.Solution;

class SingleRowKeyBoardTest{

    SingleRowKeyboard.Solution solution = new Solution();

    @Test
    public void testShouldReturn3(){
        Assertions.assertEquals(2, solution.calculateTime("abc", "abc"));
    }

    @Test
    public void testShoudlReturn4(){
        Assertions.assertEquals(4, solution.calculateTime("abcdefghijklmnopqrstuvwxyz", "cba"));
    }

    @Test
    public void testShoudlReturn73(){
        Assertions.assertEquals(73, solution.calculateTime("pqrstuvwxyzabcdefghijklmno", "leetcode"));
    }
}
