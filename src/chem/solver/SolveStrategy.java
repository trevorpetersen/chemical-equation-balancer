package chem.solver;

import chem.Matrix;

public interface SolveStrategy {
    float[] solve(Matrix matrix, float[] b);
}
