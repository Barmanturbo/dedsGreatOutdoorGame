package com.example;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestPannenkoek 
{
    /**
     * Rigorous Test :-)
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

    
}
