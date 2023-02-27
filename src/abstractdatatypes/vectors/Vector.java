/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractdatatypes.vectors;
/**
 *
 * @author dw
 */
public class Vector implements IVector
{
    private double[] components;
    private int dimensions;
    public Vector(double... components)
    {
        this.components = components;
        dimensions = components.length;
    }
    
    @Override
    public double[] getComponents()
    {
        return components;
    }

    @Override
    public int getDimensions()
    {
        return dimensions;
    }
    
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        for (double component : components)
        {
            output.append(component).append(":");
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }

    static Vector add(Vector a, Vector b)
    {
        dimensionCheck(a, b);
        double[] aComponents = a.getComponents();
        double[] bComponents = b.getComponents();
        double[] sum = new double[a.getDimensions()];
        for (int i = 0; i < a.getDimensions(); i++)
        {
            sum[i] = aComponents[i] + bComponents[i];
        }
        return new Vector(sum);
    }

    static Vector multiply(double scalar, Vector a)
    {
        double[] aComponents = a.getComponents();
        double[] product = new double[a.getDimensions()];
        for (int i = 0; i < a.getDimensions(); i++)
        {
            product[i] = scalar * aComponents[i];
        }
        return new Vector(product);
    }

    static double dotProduct(Vector a, Vector b)
    {
        dimensionCheck(a, b);
        double[] aComponents = a.getComponents();
        double[] bComponents = b.getComponents();
        double dotProduct = 0;
        for (int i = 0; i < a.getDimensions(); i++)
        {
            dotProduct += aComponents[i] * bComponents[i];
        }
        return dotProduct;
    }

    static Vector convexCombination(double scalarA, Vector a, double scalarB, Vector b)
    {
        if (!doubleEquals(scalarA + scalarB,  1) || scalarA < 0 || scalarB < 0)
        {
            throw new IllegalArgumentException("Scalar A and Scalar B must add to 1, and both must be >= 0!");
        }
        return add(multiply(scalarA, a), multiply(scalarB, b));
    }

    static void dimensionCheck(Vector a, Vector b)
    {
        if (a.getDimensions() != b.getDimensions())
        {
            throw new IllegalArgumentException("Vectors are of different dimensions!");
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Vector))
        {
            return false;
        }
        Vector v = (Vector) o;
        if (v.dimensions != this.dimensions)
        {
            return false;
        }
        double[] components = this.getComponents();
        double[] oComponents = v.getComponents();
        for (int i = 0; i < this.getDimensions(); i++)
        {
            if (!doubleEquals(components[i], oComponents[i]))
            {
                return false;
            }
        }
        return true;
    }

    static boolean doubleEquals(double a, double b)
    {
        return (Math.abs(a - b) < TOLERANCE);
    }
}
