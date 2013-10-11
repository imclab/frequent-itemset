package com.gavinmhackeling.frequentitemset;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;

public class BloomFilterTe {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int minimumSupport = 3;
		StringFunnel stringFunnel = new StringFunnel();
		BloomFilter<String>[] filters = new BloomFilter[minimumSupport];
		for (int i=0; i < filters.length; i++)
		{
			filters[i] = BloomFilter.create(stringFunnel, 1000); // TODO decrease number of expected insertions each time
		}
		
		List<String> tokens = Lists.newArrayList("a", "b", "c", "d", "a", "a", "b");
		
		for (String token: tokens)
		{
			int greatestFilter = 0;
			for (int i=0; i < filters.length; i++)
			{
				greatestFilter = i;
				if (!filters[i].mightContain(token))
					break;
			}
			System.out.println("the greatest filter for " + token + " is " + greatestFilter);
			filters[greatestFilter].put(token);
//			if (!filters[0].mightContain(token))
//			{
//				filters[0].put(token);
//			}
		}
		
		
		BloomFilter<String> bloomFilter = BloomFilter.create(stringFunnel, 1000); 
		
		bloomFilter.put("a");
		bloomFilter.put("b");
		bloomFilter.put("b");
		
		if (bloomFilter.mightContain("a"))
			System.out.println("the filter might contain a");
		else System.out.println("the filter does not contain a");
		if (bloomFilter.mightContain("c"))
			System.out.println("the filter might contain c");
		else System.out.println("the filter does not contain c");
	}

}
