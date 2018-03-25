package chem.math.solver;

import chem.math.Matrix;

public interface SolveStrategy {
    float[] solve(Matrix matrix, float[] b);
}
