package modelo;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dan Arévalo
 */
public class PersonaDAOTest {
    
    public PersonaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addPersona method, of class PersonaDAO.
     */
    @Test
    public void testAddPersona() {
        System.out.println("addPersona");
        String ced = "1122334455";
        String nombres = "Esteban";
        String apellidos = "Lozano";
        int edad = 21;
        PersonaDAO instance = new PersonaDAO();
        String expResult = "Registro Exitoso";
        String result = instance.addPersona(ced, nombres, apellidos, edad);
        assertEquals(expResult, result);
        if (!result.equals(expResult))
            fail("The test case is a prototype.");
    }

    /**
     * Test of editPer method, of class PersonaDAO.
     */
    @Test
    public void testEditPer() {
        System.out.println("editPer");
        String cedula = "1075291240";
        String nombres = "Daniel";
        String apellidos = "Arévalo";
        int edad = 22;
        PersonaDAO instance = new PersonaDAO();
        int expResult = 1;
        int result = instance.editPer(cedula, nombres, apellidos, edad);
        assertEquals(expResult, result);
        if (result != expResult)
            fail("The test case is a prototype.");
    }

    /**
     * Test of deletePer method, of class PersonaDAO.
     */
    @Test
    public void testDeletePer() {
        System.out.println("deletePer");
        String cedula = "246801357";
        PersonaDAO instance = new PersonaDAO();
        int expResult = 1;
        int result = instance.deletePer(cedula);
        assertEquals(expResult, result);
        if (result != expResult)
            fail("The test case is a prototype.");
    }
}
