package chem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParseCompoundTest{
    InputParser inputParser;

    @Before
    public void setup(){
        inputParser = new InputParser();
    }

    @Test
    public void basicTest(){
        Compound compound = inputParser.parseCompound("C");

        assertEquals("1C1", compound.toString());
    }

    @Test
    public void twoElementCompoundTest(){
        Compound compound = inputParser.parseCompound("CBr2");

        assertEquals("1C1Br2", compound.toString());
    }

    @Test
    public void singleParenthesesTest(){
        Compound compound = inputParser.parseCompound("(C)1");

        assertEquals("1C1", compound.toString());
    }

    @Test
    public void singleParenthesesTestWithNonOneSubscript(){
        Compound compound = inputParser.parseCompound("(C)2");

        assertEquals("1C2", compound.toString());
    }

    @Test
    public void singleParenthesisDoubleDigitSubscriptTest(){
        Compound compound = inputParser.parseCompound("(C)12");

        assertEquals("1C12", compound.toString());
    }

    @Test
    public void doubleElementWithinParenthsesTest(){
        Compound compound = inputParser.parseCompound("(CBr2)6");

        assertEquals("1C6Br12", compound.toString());
    }

    @Test
    public void simpleTwoElementParenthesisTest(){
        Compound compound = inputParser.parseCompound("P(C)2");

        assertEquals("1P1C2", compound.toString());
    }

    @Test
    public void multipleElementParenthesisTest(){
        Compound compound = inputParser.parseCompound("CAr(H2O)2");

        assertEquals("1C1Ar1H4O2", compound.toString());
    }

    @Test
    public void nestedParenthesesTest(){
        Compound compound = inputParser.parseCompound("(C(Ar2)2)2");

        assertEquals("1C2Ar8", compound.toString());
    }

    @Test
    public void everythingTest(){
        Compound compound = inputParser.parseCompound("NiB(Ag)1Br6(C(Ar3O)2)2");

        assertEquals("1Ni1B1Ag1Br6C2Ar12O4", compound.toString());
    }
}
