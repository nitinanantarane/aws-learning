package com.nitinrane.learning.aws;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class QuickstartAppTest extends TestCase {

    public QuickstartAppTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( QuickstartAppTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
