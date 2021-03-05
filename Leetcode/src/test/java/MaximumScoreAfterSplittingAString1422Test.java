import problems.generalproblems.MaximumScoreAfterSplittingAString1422;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaximumScoreAfterSplittingAString1422Test {

    private final MaximumScoreAfterSplittingAString1422.Solution maximumScoreAfterSplittingAString1422 =
        new MaximumScoreAfterSplittingAString1422.Solution();

    @Test
    public void testShouldReturn1WhenThereisOnly1() {
        String input = "1";
        Assertions.assertEquals(1, maximumScoreAfterSplittingAString1422.maxScore(input));
    }

    @Test
    public void testShouldReturnZeroWhenThereIsNullInput(){
        String input = null;
        Assertions.assertEquals(0, maximumScoreAfterSplittingAString1422.maxScore(input));
    }

    @Test
    public void testShouldReturnZeroWhenThereIsBlank(){
        String input = "";
        Assertions.assertEquals(0, maximumScoreAfterSplittingAString1422.maxScore(input));
    }

    @Test
    public void testShouldReturn1WhenThereisOnly0() {
        String input = "0";
        Assertions.assertEquals(1, maximumScoreAfterSplittingAString1422.maxScore(input));
    }

    @Test
    public void testShouldReturn2WhenTheSequneisZeroOne() {
        String input = "01";
        Assertions.assertEquals(2, maximumScoreAfterSplittingAString1422.maxScore(input));
    }

    @Test
    public void testCaseShouldReturn0WhenTheSequenceIs10(){
        String input = "10";
        Assertions.assertEquals(0, maximumScoreAfterSplittingAString1422.maxScore(input));
    }

    @Test
    public void testShouldReturn5WhenSequeneIs011101(){
        String input = "011101";
        Assertions.assertEquals(5, maximumScoreAfterSplittingAString1422.maxScore(input));
    }


}
