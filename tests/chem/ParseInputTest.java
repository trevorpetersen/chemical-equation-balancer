package chem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ParseInputTest {
    InputParser inputParser = new InputParser();

    @Before
    public void setup(){
        inputParser = new InputParser();
    }

    @Test
    public void test1(){
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("");
        assertNull(chemicalEquation);
    }

    @Test
    public void test2(){
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("H+O=H2O");
        assertNotNull(chemicalEquation);
    }

    @Test
    public void test3(){
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("H+O=H2O-");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H+O=H#2O");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H~+O=H2O");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H+O=H]2O");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H+O=H2<O");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("ljhf;aoi asdf");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation(" asdflha =erw =");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("asdf =eceA");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("Hjawe=KF%^");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("h+o=h2o");
        assertNull(chemicalEquation);
    }

    @Test
    public void parenthesisTest(){
        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation("H+((O)=H2O");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H+(O))=H2O");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H+)(O)=H2O");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H+O=H2O()");
        assertNull(chemicalEquation);

        chemicalEquation = inputParser.parseChemicalEquation("H+O=H2O()1");
        assertNull(chemicalEquation);
    }
}
