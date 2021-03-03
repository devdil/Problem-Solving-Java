public class MaximumScoreAfterSplittingAString1422 {

    public static class Solution {

        public int maxScore(String s){

            if (s == null)
                return 0;

            if (s.length() == 1)
                return 1;

            else {

                int[] leftArr = new int[s.length()];
                int[] rightArr = new int[s.length()];

                // fill in left sub arr
                int zerosFound = 0;
                for (int i=0; i<s.length(); i++){
                    zerosFound = s.charAt(i) == '0' ? zerosFound+1 : zerosFound;
                    leftArr[i] = zerosFound;
                }

                // reset max and start fill in right sub arr
                int onesFound = 0;
                for (int i=s.length()-1; i>=0; i--){
                    onesFound = s.charAt(i) == '1' ? onesFound+1 : onesFound;
                    rightArr[i] = onesFound;
                }

                // compute maximum score
                int maxScore = 0;
                for (int i=1;i<s.length();i++){
                    maxScore = Math.max(maxScore, leftArr[i-1] + rightArr[i]);
                }

                // return maximum score
                return maxScore;
            }

        }
    }
}
