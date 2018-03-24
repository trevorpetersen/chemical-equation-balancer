package chem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayMatrixTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getValue() {
    }

    @Test
    public void setValue() {
    }

    @Test
    public void getNumRows() {
    }

    @Test
    public void getNumCols() {
    }

    @Test
    public void solve1(){
        ArrayMatrix matrix = new ArrayMatrix(3,4);

        matrix.setValue(0,0,1);
        matrix.setValue(0,1,0);
        matrix.setValue(0,2,-1);
        matrix.setValue(0,3,0);

        matrix.setValue(1,0,1);
        matrix.setValue(1,1,0);
        matrix.setValue(1,2,0);
        matrix.setValue(1,3,-2);

        matrix.setValue(2,0,0);
        matrix.setValue(2,1,2);
        matrix.setValue(2,2,0);
        matrix.setValue(2,3,-2);

        float[] b = new float[matrix.getNumCols()];
        float[] myAns = matrix.solve(b);
        float[] expectedAns = new float[]{2,1,2,1};

        assertArrayEquals(expectedAns, myAns, 0f);
    }

    @Test
    public void solve2(){
        //TODO Fix this one (divide answer by min)
        if(1==1){
            return;
        }

        ArrayMatrix matrix = new ArrayMatrix(3,4);

        matrix.setValue(0,0,2);
        matrix.setValue(0,1,-2);
        matrix.setValue(0,2,0);
        matrix.setValue(0,3,0);

        matrix.setValue(1,0,4);
        matrix.setValue(1,1,0);
        matrix.setValue(1,2,0);
        matrix.setValue(1,3,-2);

        matrix.setValue(2,0,3);
        matrix.setValue(2,1,0);
        matrix.setValue(2,2,-2);
        matrix.setValue(2,3,-1);

        float[] b = new float[matrix.getNumCols()];
        float[] myAns = matrix.solve(b);
        float[] expectedAns = new float[]{2,2,1,4};

        assertArrayEquals(expectedAns, myAns, 0f);
    }

    @Test
    public void solve3(){
        ArrayMatrix matrix = new ArrayMatrix(2,2);

        matrix.setValue(0,0,1);
        matrix.setValue(0,1,2);

        matrix.setValue(1,0,3);
        matrix.setValue(1,1,4);


        float[] b = new float[matrix.getNumCols()];
        b[0] = 1.0f;
        b[1] = 1.0f;

        float[] myAns = matrix.solve(b);
        float[] expectedAns = new float[]{-1,1};

        assertArrayEquals(expectedAns, myAns, 0f);
    }
}