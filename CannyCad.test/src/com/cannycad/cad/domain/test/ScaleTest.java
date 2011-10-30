package com.cannycad.cad.domain.test;

import static org.junit.Assert.*;

import org.apache.commons.math.linear.RealVectorFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cannycad.cad.domain.Vector2D;
import com.cannycad.cad.domain.Tansformation;

public class ScaleTest
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

        t.addScale(5);

        assertEquals(
                "Translate failed",
                "   5.000   0.000   0.000\r\n" +
                        "   0.000   5.000   0.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getTransformationMatrix(), W, D).toString());
        assertEquals(
                "Translate failed",
                "   0.200   0.000   0.000\r\n" +
                        "   0.000   0.200   0.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getInverseMatrix(), W, D).toString());

        t.addScale(0.5);

        assertEquals(
                "Translate failed",
                "   2.500   0.000   0.000\r\n" +
                        "   0.000   2.500   0.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getTransformationMatrix(), W, D).toString());
        assertEquals(
                "Translate failed",
                "   0.400   0.000   0.000\r\n" +
                        "   0.000   0.400   0.000\r\n" +
                        "   0.000   0.000   1.000\r\n",
                t.matrixToStringBuffer(
                        t.getInverseMatrix(), W, D).toString());

    }

    @Test
    public void testOperate()
    {
        Tansformation t = new Tansformation();

        t.addScale(5);

        Vector2D in = new Vector2D(2, 5);
        Vector2D out = new Vector2D();

        t.transform(in, out);

        assertEquals(
                "Translate failed",
                "{10; 25}",
                RealVectorFormat.formatRealVector(out));

    }

}
