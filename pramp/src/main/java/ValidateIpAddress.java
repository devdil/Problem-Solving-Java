/**
 * Problem Statement
 * <p>
 * Validate an IP address (IPv4). An address is valid if and only if it is in the form "X.X.X.X", where each X is a
 * number from 0 to 255.
 * <p>
 * For example, "12.34.5.6", "0.23.25.0", and "255.255.255.255" are valid IP addresses, while "12.34.56.oops",
 * "1.2.3.4.5", and "123.235.153.425" are invalid IP addresses.
 * <p>
 * Examples:
 * <p>
 * ip = '192.168.0.1' output: true
 * <p>
 * ip = '0.0.0.0' output: true
 * <p>
 * ip = '123.24.59.99' output: true
 * <p>
 * ip = '192.168.123.456' output: false Constraints:
 * <p>
 * [time limit] 5000ms [input] string ip [output] boolean
 */

public class ValidateIpAddress {

    public static class Solution {

        static boolean validateIp(String ip) {

            if (ip == null || ip == "") {
                return false;
            } else {
                String[] octetSplit = ip.split("\\.");
                // check length of the split
                if (octetSplit.length < 4 || octetSplit.length > 4) {
                    return false;
                }
                for (String octet : octetSplit) {

                    try {
                        int octetInInt = Integer.parseInt(octet);
                        // if neither of them fall within the range, return false;
                        if (!(octetInInt >= 0 && octetInInt <= 255)) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        // in case its not a number
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
