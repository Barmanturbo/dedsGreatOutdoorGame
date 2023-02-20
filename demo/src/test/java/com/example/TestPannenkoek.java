package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestPannenkoek 
{
    /**
     * Standaard test bij het aanmaken van deze file. Deal with it.
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test1itemopdestack(){
        String teststring = "testwaarde";

        Stack teststapel = new Stack();

        teststapel.nieuwPannenkoekOpBord(teststring);

        assertEquals(teststapel.pakPannenkoekVanBord(),teststring);
    }

    @Test
    public void test2itemsopdestackNietEquals(){
        Stack teststapel = new Stack();

        String teststring1 = "testwaarde1";
        String teststring2 = "testwaarde2";

        teststapel.nieuwPannenkoekOpBord(teststring1);
        teststapel.nieuwPannenkoekOpBord(teststring2);

        assertNotEquals(teststring1, teststapel.pakPannenkoekVanBord());
    }

    @Test
    public void test2itemsopdestackWelEquals(){
        Stack teststapel = new Stack();

        String teststring1 = "t1";
        String teststring2 = "t2";

        teststapel.nieuwPannenkoekOpBord(teststring1);
        teststapel.nieuwPannenkoekOpBord(teststring2);

        Object o1 = teststapel.pakPannenkoekVanBord();
        Object o2 = teststapel.pakPannenkoekVanBord();

        assertEquals(teststring2, o1);
        assertEquals(teststring1, o2);
    }

    @Test
    public void pakbovenstepannenkoekMetLegeStapel(){
        Stack teststapel = new Stack();
        assertNull(teststapel.pakPannenkoekVanBord());
    }

    
}
