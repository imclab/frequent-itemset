package com.gavinmhackeling.frequentitemset;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;

public class BloomFilterTe 
{

	private static BloomFilter<String>[] filters;
	private static int minimumSupport = 3;
	private static List<String> frequentPairs;
	
	private static void addToHighestFilter(String s1, String s2) 
	{
		int greatestFilter = 0;
		for (int i=0; i<filters.length; i++)
		{
			greatestFilter = i;
			if (!filters[i].mightContain(s1+s2)) {
				if (i == minimumSupport) frequentPairs.add(s1+s2);
				break;
			}
		}
		System.out.println("the greatest filter for " + s1 + s2 + " is " + greatestFilter);
		filters[greatestFilter].put(s1+s2);
	}


	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		frequentPairs = Lists.newArrayList();
		StringFunnel stringFunnel = new StringFunnel();

		List<ArrayList<String>> l = Lists.newArrayList(
				Lists.newArrayList("a", "b", "c", "d", "e"),
				Lists.newArrayList("a", "c"),
				Lists.newArrayList("b", "d", "e"),
				Lists.newArrayList("a", "d", "e"),
				Lists.newArrayList("a"),
				Lists.newArrayList("a", "b", "c"),
				Lists.newArrayList("a", "b"),
				Lists.newArrayList("a", "c"),
				Lists.newArrayList("d", "e"),
				Lists.newArrayList("a", "b", "c"),
				Lists.newArrayList("a", "b")
				);

		filters = new BloomFilter[minimumSupport+1];
		for (int i=0; i < filters.length; i++)
		{
			filters[i] = BloomFilter.create(stringFunnel, 1000); // TODO decrease number of expected insertions each time
		}

		// get a pair
		for (List<String> document: l)
		{
			System.out.println("\nDocument: " + document);
			for (int i=0; i<document.size()-1; i++)
			{
				for (int j=i+1; j<document.size(); j++)
				{
//					System.out.println(document.get(i) + ", " + document.get(j));
					addToHighestFilter(document.get(i), document.get(j));
				}
			}
		}
		
		System.out.println("\nFrequent pairs:");
		for (String frequentPair: frequentPairs)
		{
			System.out.println(frequentPair);
		}
		
		
		
	}

}
