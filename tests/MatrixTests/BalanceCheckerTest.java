package MatrixTests;

import chem.ChemicalEquation;
import chem.InputParser;
import chem.math.ArrayMatrix;
import chem.math.Matrix;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BalanceCheckerTest {
    InputParser inputParser;

    @Before
    public void setUp() throws Exception {
        inputParser = new InputParser();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1(){
        // As4S6+O2=As4O6+SO2
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("As4S6+O2=As4O6+SO2");
        Matrix matrix = chemicalEquation.toMatrix();
        float[] b = new float[matrix.getNumCols()];

        assertArrayEquals("",new float[]{1,9,1,6}, matrix.solve(b), 0);
    }

    @Test
    public void test2(){
        // HgO=Hg+O2
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("HgO=Hg+O2");
        Matrix matrix = chemicalEquation.toMatrix();
        float[] b = new float[matrix.getNumCols()];

        assertArrayEquals("",new float[]{2,2,1}, matrix.solve(b), 0);
    }

    @Test
    public void test3(){
        // N2H4+O2=NO2+H2O
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("N2H4+O2=NO2+H2O");
        Matrix matrix = chemicalEquation.toMatrix();
        float[] b = new float[matrix.getNumCols()];

        assertArrayEquals("",new float[]{1,3,2,2}, matrix.solve(b), 0);
    }

    @Test
    public void test4(){
        // CaCl2+Na3PO4=Ca3(PO4)2+NaCl
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("CaCl2+Na3PO4=Ca3(PO4)2+NaCl");
        Matrix matrix = chemicalEquation.toMatrix();
        float[] b = new float[matrix.getNumCols()];

        assertArrayEquals("",new float[]{1,3,2,2}, matrix.solve(b), 0);
    }

}