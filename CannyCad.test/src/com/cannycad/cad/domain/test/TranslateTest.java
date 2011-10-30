package com.cannycad.cad.domain.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cannycad.cad.domain.Tansformation;
import com.cannycad.cad.domain.Vector2D;
import org.apache.commons.math.linear.RealVectorFormat;

public class TranslateTest
{

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void test2()
    {
        int W = 6;
        int D = 3;

        Tansformation t = new Tansformation();

        t.addTranslate(3, 4);

        assertEquals(
                "Translate failed",
                "   1.000   0.000   3.000\r\n" +
                        "   0.000   1.000   4.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getTransformationMatrix(), W, D).toString());
        assertEquals(
                "Translate failed",
                "   1.000   0.000  -3.000\r\n" +
                        "   0.000   1.000  -4.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getInverseMatrix(), W, D).toString());

        t.addTranslate(2, 3);

        assertEquals(
                "Translate failed",
                "   1.000   0.000   5.000\r\n" +
                        "   0.000   1.000   7.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getTransformationMatrix(), W, D).toString());
        assertEquals(
                "Translate failed",
                "   1.000   0.000  -5.000\r\n" +
                        "   0.000   1.000  -7.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getInverseMatrix(), W, D).toString());

    }

    @Test
    public void testOperate()
    {
        Tansformation t = new Tansformation();

        t.addTranslate(3, 4);

        Vector2D in = new Vector2D(2, 5);
        Vector2D out = new Vector2D();

        t.transform(in, out);

        assertEquals(
                "Translate failed",
                "{5; 9}",
                RealVectorFormat.formatRealVector(out));

    }

}
