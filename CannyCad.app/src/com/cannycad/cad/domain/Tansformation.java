package com.cannycad.cad.domain;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.util.MathUtils;

public class Tansformation
{
    public static final double               EPSILON         = 1e-64;

    public static final Array2DRowRealMatrix IDENTITY_MATRIX =
                                                                     new Array2DRowRealMatrix(MatrixUtils
                                                                             .createRealIdentityMatrix(3).getData());

    Array2DRowRealMatrix                     transformationMatrix;
    Array2DRowRealMatrix                     inverseMatrix;

    double[]                                 operationVector = new double[3];
    double[]                                 resultVector    = new double[3];

    public Tansformation()
    {
        init();
    }

    public void init()
    {
        transformationMatrix = new Array2DRowRealMatrix(IDENTITY_MATRIX.getDataRef());
        setMatrix(transformationMatrix, IDENTITY_MATRIX);
        inverseMatrix = new Array2DRowRealMatrix(IDENTITY_MATRIX.getDataRef());
    }

    void setMatrix(Array2DRowRealMatrix matrix, Array2DRowRealMatrix values)
    {
        matrix.setSubMatrix(values.getDataRef(), 0, 0);
    }

    void setOperationVector(double x, double y)
    {
        operationVector[0] = x;
        operationVector[1] = y;
        operationVector[2] = 1.;
    }

    public void addTransformation(Array2DRowRealMatrix transformation)
    {
        transformationMatrix = transformationMatrix.multiply(transformation);
        inverseMatrix = (Array2DRowRealMatrix) new LUDecompositionImpl(transformationMatrix).getSolver().getInverse();
    }

    public double[] transform(double x, double y)
    {
        setOperationVector(x, y);
        return transformationMatrix.operate(operationVector);
    }

    public void transform(Vector2D in, Vector2D out)
    {
        double[] o = transform(in.getX(), in.getY());
        out.set(o);
    }

    public double[] inverse(double x, double y)
    {
        setOperationVector(x, y);
        return inverseMatrix.operate(operationVector);
    }

    public void inverse(Vector2D in, Vector2D out)
    {
        double[] o = inverse(in.getX(), in.getY());
        out.set(o);
    }

    public void addTranslate(double x, double y)
    {
        Array2DRowRealMatrix translateMatrix = new Array2DRowRealMatrix(IDENTITY_MATRIX.getDataRef());
        translateMatrix.setEntry(0, 2, x);
        translateMatrix.setEntry(1, 2, y);

        addTransformation(translateMatrix);
    }

    public void addScale(double s)
    {
        if (MathUtils.equals(s, 0., EPSILON))
            throw new ArithmeticException("Scale is too small");
        if (MathUtils.equals(1. / s, 0, EPSILON))
            throw new ArithmeticException("Scale is too big");

        Array2DRowRealMatrix scaleMatrix = new Array2DRowRealMatrix(IDENTITY_MATRIX.getDataRef());
        scaleMatrix.setEntry(0, 0, s);
        scaleMatrix.setEntry(1, 1, s);

        addTransformation(scaleMatrix);
    }

    public void addRotate(double rad)
    {
        if (MathUtils.equals(rad, 0, EPSILON))
            return;

        Array2DRowRealMatrix rotateMatrix = new Array2DRowRealMatrix(IDENTITY_MATRIX.getDataRef());
        double sin = Math.sin(rad);
        double cos = Math.cos(rad);
        rotateMatrix.setEntry(0, 0, cos);
        rotateMatrix.setEntry(0, 1, -sin);
        rotateMatrix.setEntry(1, 0, sin);
        rotateMatrix.setEntry(1, 1, cos);

        addTransformation(rotateMatrix);
    }

    public StringBuffer matrixToStringBuffer(RealMatrix matrix, int w, int d)
    {
        StringWriter sWriter = new StringWriter();
        print(matrix, new PrintWriter(sWriter, true), w, d);
        return sWriter.getBuffer();
    }

    public void print(RealMatrix matrix, int w, int d)
    {
        print(matrix, new PrintWriter(System.out, true), w, d);
    }

    public void print(RealMatrix matrix, PrintWriter output, int w, int d)
    {
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(d);
        format.setMinimumFractionDigits(d);
        format.setGroupingUsed(false);
        print(matrix, output, format, w + 2);
    }

    public void print(RealMatrix matrix, NumberFormat format, int width)
    {
        print(matrix, new PrintWriter(System.out, true), format, width);
    }

    public void print(RealMatrix matrix, PrintWriter output, NumberFormat format, int width)
    {
        for (int row = 0; row < matrix.getRowDimension(); row++)
        {
            for (int column = 0; column < matrix.getColumnDimension(); column++)
            {
                String number = format.format(matrix.getEntry(row, column));
                int padding = Math.max(1, width - number.length());
                for (int p = 0; p < padding; p++)
                    output.print(' ');
                output.print(number);
            }
            output.println();
        }
    }

    public Array2DRowRealMatrix getTransformationMatrix()
    {
        return transformationMatrix;
    }

    public Array2DRowRealMatrix getInverseMatrix()
    {
        return inverseMatrix;
    }

}
