package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
		
	// METHOD #1: calculateColumnTotal()
	
    @Test
    public void calculateColumnTotalForTwoPositiveValues() {
        // setup
        Mockery mockingContext = new Mockery();
        final Values2D values = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {{
            oneOf(values).getRowCount();
            will(returnValue(2));
            oneOf(values).getValue(0, 0);
            will(returnValue(7.5));
            oneOf(values).getValue(1, 0);
            will(returnValue(2.5));
        }});

        // exercise
        double result = DataUtilities.calculateColumnTotal(values, 0);

        // verify
        assertEquals("Adding 2 positive values in column", 10.0, result, .000000001d);

        // tear-down: NONE in this test method
    }
    
	@Test
	public void calculateColumnTotalForThreeValuesMixedNegativeAndPositive() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 rows
			oneOf(values).getRowCount();
			will(returnValue(3));
			// fist value in first column
			oneOf(values).getValue(0, 0);
			will(returnValue(2.0));
			// second value in first column
			oneOf(values).getValue(1, 0);
			will(returnValue(-2.5));
			// third value in first column
			oneOf(values).getValue(2, 0);
			will(returnValue(8.0));
		}});
		
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 0);
		
		// verify
		assertEquals("Adding 3 negative and positive values in column", 7.5, result, .000000001d);
		
		// tear-down: None in this test method

	}
	
	@Test (expected = InvalidParameterException.class)
	public void calculateColumnTotalNullInput() throws InvalidParameterException {
		// no setup required
		
		// exercise
		DataUtilities.calculateColumnTotal(null, 0);
		
	}
	
	@Test
	public void calculateColumnTotalValidColumnIndexOne() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 rows
			oneOf(values).getRowCount();
			will(returnValue(3));
			// fist value in second column
			oneOf(values).getValue(0, 1);
			will(returnValue(2.0));
			// second value in second column
			oneOf(values).getValue(1, 1);
			will(returnValue(2.5));
			// third value in second column
			oneOf(values).getValue(2, 1);
			will(returnValue(7.5));
		}});
		
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 1);
		
		// verify
		assertEquals("Testing Valid Column Index", 12.0, result, .000000001d);
		
		// tear-down: None in this test method
	}
	
	
	// METHOD #2: calculateRowTotal()	
	
	@Test
	public void calculateRowTotalForFourPositiveValues() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 4 columns
			oneOf(values).getColumnCount();
			will(returnValue(4));
			// fist value in first row
			oneOf(values).getValue(0, 0);
			will(returnValue(2.0));
			// second value in first row
			oneOf(values).getValue(0, 1);
			will(returnValue(2.5));
			// third value in first row
			oneOf(values).getValue(0, 2);
			will(returnValue(7.5));
			// fourth value in first row
			oneOf(values).getValue(0, 3);
			will(returnValue(3.0));
		}});
		
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		// verify
		assertEquals("Adding 4 positive values in row", 15.0, result, .000000001d);
	}
	
	@Test
	public void calculateRowTotalforMixedNegativeAndPositive() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 columns
			oneOf(values).getColumnCount();
			will(returnValue(3));
			// fist value in first row
			oneOf(values).getValue(0, 0);
			will(returnValue(-100.0));
			// second value in first row
			oneOf(values).getValue(0, 1);
			will(returnValue(300.0));
			// third value in first row
			oneOf(values).getValue(0, 2);
			will(returnValue(500.0));
		}});
		
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		// verify
		assertEquals("Adding positive and negative values in row", 700.0, result, .000000001d);
	}
	
	@Test (expected = InvalidParameterException.class)
	public void calculateRowTotalNullInput() throws InvalidParameterException {
		// no setup required
		
		// exercise
		DataUtilities.calculateColumnTotal(null, 0);
		
	}
	
	@Test
	public void calculateRowTotalValidRowIndexOne() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		
		mockingContext.checking(new Expectations() {{
			// values table has 3 columns
			oneOf(values).getColumnCount();
			will(returnValue(3));
			// fist value in second row
			oneOf(values).getValue(1, 0);
			will(returnValue(2.0));
			// second value in second row
			oneOf(values).getValue(1, 1);
			will(returnValue(2.5));
			// third value in second row
			oneOf(values).getValue(1, 2);
			will(returnValue(7.5));
		}});
		
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 1);
		
		// verify
		assertEquals("Testing Valid Column Index", 12.0, result, .000000001d);
		
		// tear-down: None in this test method
	}
	
	// METHOD #3: createNumberArray()
	
	@Test
	public void createNumberArraySimple1x7() {
		// setup
		double[] passedIn = {0.1, 0.2, 0.3, 4.0, 5.0, 6.0, 7.0};
		Number[] expected = {0.1, 0.2, 0.3, 4.0, 5.0, 6.0, 7.0};
		
		// exercise
		Number[] result = DataUtilities.createNumberArray(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	
	@Test
	public void createNumberArrayEmpty() {
		// setup
		double[] passedIn = {};
		Number[] expected = {};
		
		// exercise
		Number[] result = DataUtilities.createNumberArray(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	
	@Test (expected = InvalidParameterException.class)
	public void createNumberArrayNullInput() throws InvalidParameterException {
		// setup: None
		
		// exercise
		DataUtilities.createNumberArray(null);
	}
	
	// METHOD #4: createNumberArray2D()
	
	@Test
	public void createNumberArray2DSimple3x1() {
		// setup
		double[][] passedIn = { {0.1},
								{1},
								{100}
							  };
		Number[][] expected = { {0.1},
								{1},
								{100}
			  				  };
		
		// exercise
		Number[][] result = DataUtilities.createNumberArray2D(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	
	@Test
	public void createNumberArray2DComplex3x4() {
		// setup
		double[][] passedIn = { {0.1, 0.2, 0.3, 0.4},
								{1, 2, 3, 4},
								{100, 200, 300, 400}
							  };
		Number[][] expected = { {0.1, 0.2, 0.3, 0.4},
								{1, 2, 3, 4},
								{100, 200, 300, 400}
			  				  };
		
		// exercise
		Number[][] result = DataUtilities.createNumberArray2D(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	
	@Test
	public void createNumberArray2DEmpty() {
		// setup
		double[][] passedIn = {};
		Number[][] expected = {};
		
		// exercise
		Number[][] result = DataUtilities.createNumberArray2D(passedIn);
		
		// verify
		assertArrayEquals(expected, result);
		
		// tear-down
		passedIn = null;
		expected = null;
		result = null;
	}
	
	@Test (expected = InvalidParameterException.class)
	public void createNumberArray2DNullInput() throws InvalidParameterException {
		// setup: None
		
		// exercise
		DataUtilities.createNumberArray2D(null);
	}
	
	// METHOD #5: getCumulativePercentages()
	
	@Test
	public void getCumulativePercentagesThreeFirstPair() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, 20)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(20));
			
			// third key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 0", 10.0 / (10.0 + 20.0 + 30.0), result.getValue(0).doubleValue(), .1d);
		
	}
	
	@Test
	public void getCumulativePercentagesThreeSecondPair() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, 20)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(20));
			
			// third key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 1", (10.0 + 20.0) / (10.0 + 20.0 + 30.0), result.getValue(1).doubleValue(), .1d);
		
	}
	
	@Test
	public void getCumulativePercentagesThreeThirdPair() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, 20)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(20));
			
			// third key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 2", (10.0 + 20.0 + 30.0) / (10.0 + 20.0 + 30.0), result.getValue(2).doubleValue(), .1d);
		
	}
	
	@Test (expected = NullPointerException.class)
	public void getCumulativePercentagesTwoPairsOneNull() throws NullPointerException {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(2));
			
			// first key-value pair (0, 100)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(100));
			
			// second key-value pair (1, null)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(null));
			
		}});
		
		// exercise
		DataUtilities.getCumulativePercentages(kValues);
		
	}
	
	@Test
	public void getCumulativePercentagesThreePairsOneNegative() {
		// setup
		Mockery mockingContext = new Mockery();
		final KeyedValues kValues = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {{
			// keys
			allowing(kValues).getItemCount();
			will(returnValue(3));
			
			// first key-value pair (0, 10)
			allowing(kValues).getKey(0);
			will(returnValue(0));
			allowing(kValues).getValue(0);
			will(returnValue(10));
			
			// second key-value pair (1, -200)
			allowing(kValues).getKey(1);
			will(returnValue(1));
			allowing(kValues).getValue(1);
			will(returnValue(-200));
			
			// second key-value pair (2, 30)
			allowing(kValues).getKey(2);
			will(returnValue(2));
			allowing(kValues).getValue(2);
			will(returnValue(30));
			
		}});
		
		// exercise
		KeyedValues result = DataUtilities.getCumulativePercentages(kValues);
		
		// verify
		assertEquals("The cumulative percentage at key 1 (negative value)", (10 - 200) / (10.0 - 200.0 + 30.0), result.getValue(1).doubleValue(), .1d);
		
	}
	
	@Test (expected = InvalidParameterException.class)
	public void getCumulativePercentagesNullInput() throws InvalidParameterException {
		// setup: None
		
		// exercise
		DataUtilities.getCumulativePercentages(null);
	}
	
	
}
