package org.jfree.data.test;

import org.junit.*;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.Range;
import org.junit.BeforeClass;
import org.junit.Test;

public class RangeTest {
	private Range exampleRange;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		exampleRange = new Range(-1, 1);
	}
	
	// Example test from assignment instructions
	@Test
	public void centralValueShouldBeZero() {
		assertEquals("The central value of -1 and 1 should be 0", 0, exampleRange.getCentralValue(), 0.000000001d);
	}
	
	
	// METHOD #1: constrain()
	
	@Test
	public void constrainValueBelowLowerLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -1000;
		
		// exercise
		double result = exampleRange.constrain(value);
		
		// verify
		assertEquals("Constraining value below lower limit of range (-1000), result should be lower limit -1:", -1, result, .000000001d);
		
		// tear-down: none in this method
	}
	
	@Test
	public void constrainValueAboveUpperLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 1000;
		
		// exercise
		double result = exampleRange.constrain(value);
		
		// verify
		assertEquals("Constraining value above upper limit of range (1000), result should be upper limit 1:", 1, result, .000000001d);
		
		// tear-down: non in this method
	}
	
	@Test
	public void constrainValueWithinRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -0.5;
		
		// exercise
		double result = exampleRange.constrain(value);
		
		// verify
		assertEquals("Constraining value within the range (-0.5), result should be the same value -0.5:", -0.5, result, .000000001d);
		
		// tear-down
	}
	
	@Test
	public void constrainInvalidValue() throws Exception {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = Double.NaN;
		
		// exercise
		exampleRange.constrain(value);
		
	}
	
	
	// METHOD #2: expandToInclude()
	
	@Test
	public void expandToIncludeValueAboveUpperLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 200;
		Range expected = new Range(-1, 200); // upper limit should increase
		
		// exercise
		Range result = Range.expandToInclude(exampleRange, value);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void expandToIncludeValueBelowLowerLimit() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -200;
		Range expected = new Range(-200, 1); // lower limit should decrease
		
		// exercise
		Range result = Range.expandToInclude(exampleRange, value);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void expandToIncludeValueWithinRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 0;
		Range expected = new Range(-1, 1); // no change should occur
		
		// exercise
		Range result = Range.expandToInclude(exampleRange, value);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void expandToIncludeInvalidValue() throws Exception {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = Double.NaN;
		
		// exercise
		Range.expandToInclude(exampleRange, value);
	}
	
	
	// METHOD #3: shift()
	
	@Test
	public void shiftAcrossZeroByPositiveAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = 5;
		boolean allowZeroCrossing = true;
		
		Range expected = new Range(4, 6);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftAcrossZeroByNegativeAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = -5;
		boolean allowZeroCrossing = true;
		
		Range expected = new Range(-6, -4);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftNotAcrossZeroByPositiveAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = 5;
		boolean allowZeroCrossing = false; // no zero crossing
		
		Range expected = new Range(0, 6);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftNotAcrossZeroByNegativeAmount() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = -5;
		boolean allowZeroCrossing = false; // no zero crossing
		
		Range expected = new Range(-6, 0);
		
		// exercise
		Range result = Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		expected = null;
		result = null;
	}
	
	@Test
	public void shiftByInvalidAmonut() throws Exception {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double shiftAmount = Double.NaN;
		boolean allowZeroCrossing = false; // either false or true, does not matter for this test case
		
		// exercise
		Range.shift(exampleRange, shiftAmount, allowZeroCrossing);
		
	}
	
	
	// METHOD #4: contains()
	
	@Test
	public void containsValueSlightlyAboveRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = 1.01;
		
		// exercise
		boolean result = exampleRange.contains(value);
		
		// verify
		assertFalse(result);
	}
	
	@Test
	public void containsValueSlightlyBelowRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -1.01;
		
		// exercise
		boolean result = exampleRange.contains(value);
		
		// verify
		assertFalse(result);
	}
	
	@Test
	public void containsValueWithinRange() {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = -0.5;
		
		// exercise
		boolean result = exampleRange.contains(value);
		
		// verify
		assertTrue(result);
	}
	
	@Test
	public void containsInvalidValue() throws Exception {
		// @Before: exampleRange [-1, 1]
		
		// setup
		double value = Double.NaN;
		
		// exercise
		exampleRange.contains(value);
	}
	
	
	// METHOD #5: combine()
	
	@Test
	public void combineOverlappingRanges() {
		// setup
		Range range1 = new Range(-20, 1);
		Range range2 = new Range(-5, 50);
		Range expected = new Range(-20, 50);
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		range1 = null;
		range2 = null;
		expected = null;
		result = null;
	}
	
	@Test
	public void combineDistinctRanges() {
		// setup
		Range range1 = new Range(-20, 1);
		Range range2 = new Range(50, 100);
		Range expected = new Range(-20, 100);
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		range1 = null;
		range2 = null;
		expected = null;
		result = null;
	}
	
	@Test
	public void combineNullRangeAndNonNullRange() {
		// setup
		Range range1 = null;
		Range range2 = new Range(-5, 50);
		Range expected = new Range(-5, 50);
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		range2 = null;
		expected = null;
		result = null;
	}
	
	@Test
	public void combineTwoNullRanges() {
		// setup
		Range range1 = null;
		Range range2 = null;
		Range expected = null;
		
		// exercise
		Range result = Range.combine(range1, range2);
		
		// verify
		assertEquals(expected, result);
		
		// tear-down
		result = null;
	}
	
	
	@After
	public void tearDown() throws Exception {
		exampleRange = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

}
