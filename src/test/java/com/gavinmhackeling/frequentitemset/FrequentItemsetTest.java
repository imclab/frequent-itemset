package com.gavinmhackeling.frequentitemset;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Multiset;

public class FrequentItemsetTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@Test
	public void test() throws IOException 
	{
		Multiset<String> supports = FrequentItemset.getSupports(
				"/home/gavin/workspace/frequentitemset/src/main/resources/Artist_lists_small.txt");
		assertEquals(251, supports.count("Radiohead"));
	}
	
	@Test
	public void thresholdSupports() throws IOException
	{
		Multiset<String> supports = FrequentItemset.getSupports(
				"/home/gavin/workspace/frequentitemset/src/main/resources/Artist_lists_small.txt");
		Map<String, Integer> thresholdedSupports = FrequentItemset.thresholdSupports(supports, 50);
		assertTrue(thresholdedSupports.containsKey("Radiohead"));
		assertFalse(thresholdedSupports.containsKey("Blitzen Trapper"));
		assertFalse(thresholdedSupports.containsKey("The Police"));
	}

}
