/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractdatatypes.vectors;

/**
 * @author dw
 */
public interface IVector
{
    double TOLERANCE = 0.00001;

    double[] getComponents();

    int getDimensions();
}
