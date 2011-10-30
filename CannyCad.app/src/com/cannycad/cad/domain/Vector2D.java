package com.cannycad.cad.domain;

import org.apache.commons.math.linear.ArrayRealVector;

public class Vector2D extends ArrayRealVector
{

    /**
	 * 
	 */
    private static final long serialVersionUID = -6632354653137715746L;

    public Vector2D(double x, double y)
    {
        super(2);
        set(x, y);
    }

    public Vector2D()
    {
        this(0, 0);
    }

    public void set(double x, double y)
    {
        setEntry(0, x);
        setEntry(1, y);
    }

    public void set(double[] v)
    {
        setEntry(0, v[0]);
        setEntry(1, v[1]);
    }

    public double getX()
    {
        return getEntry(0);
    }

    public double getY()
    {
        return getEntry(1);
    }
}
