package problems.monthlychallenges.y2021.march;

import java.util.HashMap;
import problems.generalproblems.MaximumScoreAfterSplittingAString1422.Solution;

public class SingleRowKeyboard {

    public static class Solution{

        public int calculateTime(String keyboard, String word) {

            if (word == null || keyboard == null)
                return 0;
            else {

                HashMap<Character, Integer> mp = new HashMap<>();

                for(int i=0;i<keyboard.length();i++){
                    mp.put(keyboard.charAt(i), i);
                }

                int from = 0;
                int distance = 0;

                for (int i=0;i<word.length();i++){
                    int to = mp.get(word.charAt(i));
                    distance += Math.abs(from-to);
                    from = to;
                }

                return distance;
            }
        }
    }
}
