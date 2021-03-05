import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidateIpAddressTest {

    @Test
    public void testShouldReturnFalseWHenInputIsNull(){
        assertFalse(ValidateIpAddress.Solution.validateIp(null));
    }

    @Test
    public void testShouldReturnFalseWHenInputDoesNotContainDot(){
        assertFalse(ValidateIpAddress.Solution.validateIp("foobar"));
    }

    @Test
    public void testShouldReturnFalseWHenInputDoesNotFourOcted(){
        assertFalse(ValidateIpAddress.Solution.validateIp("1.1.1"));
    }

    @Test
    public void testShouldReturnFalseWHenInputContains2ctets(){
        assertFalse(ValidateIpAddress.Solution.validateIp("1.1"));
    }

    @Test
    public void testShouldReturnFalseWHenInputContainssingleoctet(){
        assertFalse(ValidateIpAddress.Solution.validateIp("1"));
    }

    @Test
    public void testShouldReturnFalseWHenInputContainsExactlyFourOctetButNotNumbers(){
        assertFalse(ValidateIpAddress.Solution.validateIp("a.b.c.d"));
    }

    @Test
    public void testShouldReturnFalseWHenInputContainsBlankString(){
        assertFalse(ValidateIpAddress.Solution.validateIp(""));
    }

    @Test
    public void testShouldReturnFalseWHenInputContainsOctetsButOutsideRange1(){
        assertFalse(ValidateIpAddress.Solution.validateIp("1.1.1.256"));
    }

    @Test
    public void testShouldReturnFalseWHenInputContainsOctetsButOutsideRange2(){
        assertFalse(ValidateIpAddress.Solution.validateIp("999999.9999999999999.999999999999.256"));
    }

    @Test
    public void testShouldReturnTrueWhenInputContainsAValidOctet1(){
        assertTrue(ValidateIpAddress.Solution.validateIp("192.168.0.1"));
    }

    @Test
    public void testShouldReturnTrueWhenInputContainsAValidOctet2(){
        assertTrue(ValidateIpAddress.Solution.validateIp("0.0.0.0"));
    }

    @Test
    public void testShouldReturnTrueWhenInputContainsAValidOctet3(){
        assertTrue(ValidateIpAddress.Solution.validateIp("123.24.59.99"));
    }
}
