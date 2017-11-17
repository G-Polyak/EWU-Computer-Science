package fraction;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FractionTest {

    Fraction test;

    @Before
    public void setUp() throws Exception {
        //initial fraction
        test = new Fraction(-16, -20);
    }// end setUp

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testNumAndDen() {
        Fraction f1 = new Fraction(4, 5);
        Fraction f2 = new Fraction(-1, 2);
        Fraction f3 = new Fraction(-4, -5);
        assertEquals(4, f1.getNum());
        assertEquals(-1, f2.getNum());
        assertEquals(5, f1.getDen());
        assertEquals(2, f2.getDen());
        assertEquals(4, f3.getNum());
        assertEquals(5, f3.getDen());
    }// end test


    @Test
	public void testNegatives()
	{
		assertEquals(4, test.getNum());
		assertEquals(5, test.getDen());
		test = new Fraction(4,-5);
		assertEquals(-4, test.getNum());
		assertEquals(5, test.getDen());
        test = new Fraction(-16, -20);
	}

	@Test
	public void testProperReduction()
	{
		assertEquals(4, test.getNum());
		assertEquals(5, test.getDen());
		test = new Fraction(121,11);
		assertEquals(11, test.getNum());
		assertEquals(1, test.getDen());
        test = new Fraction(-16, -20);
	}

	@Test
	public void testEquals()  // checks both numerator and denominator are equal to passed in values
	{
		assertFalse(test.equals(new Fraction(41,50)));
		assertTrue(test.equals(new Fraction(4000,5000)));
	}

	@Test
	public void testCompareTo() // checks both numerator and denominator are less than, greater than or equal to passed in values
	{
		assertEquals(2,test.compareTo(new Fraction(2,3)));
		assertEquals(-1,test.compareTo(new Fraction(5,6)));
		assertEquals(0,test.compareTo(new Fraction(40,50)));
	}

	@Test
	public void testAdd() // adds 2 fractions
	{
		test = test.add(new Fraction(4,5));
		assertEquals(8, test.getNum());
		assertEquals(5, test.getDen());
		test = test.add(new Fraction(0,5));
		assertEquals(8, test.getNum());
		assertEquals(5, test.getDen());
	}

	@Test
	public void testToString() //toString returns num/den if neg then the num is neg
	{
		assertEquals("4/5", test.toString());
		test = new Fraction(-2,5);
		assertEquals("-2/5", test.toString());
		test = new Fraction(2,-5);
		assertEquals("-2/5", test.toString());
		test = new Fraction(-2,-5);
		assertEquals("2/5", test.toString());
	}



}// end class
