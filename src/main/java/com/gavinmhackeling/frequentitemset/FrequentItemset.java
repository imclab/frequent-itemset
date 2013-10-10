package com.gavinmhackeling.frequentitemset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

public class FrequentItemset 
{	

	protected static Multiset<String> getSupports(String fileName) throws IOException
	{
		Multiset<String> supports = HashMultiset.create();
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		String line;
		while ((line = br.readLine()) != null) 
		{
			String[] tokens = line.split(",");
			for (String token: tokens) supports.add(token);
		}
		br.close();
		return supports;
	}

	//	protected static void buildTransactions(String fileName) throws IOException
	//	{
	//		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
	//		String line;
	//		while ((line = br.readLine()) != null) 
	//		{
	//		   String[] tokens = line.split(",");
	//		   for (String token: tokens) supports.add(token);
	//		}
	//		br.close();		
	//	}

	protected static Map<String, Integer> thresholdSupports(Multiset<String> supports, int threshold)
	{
		Map<String, Integer> keepElements = Maps.newHashMap();
		int counter = 0;
		for (String element: Multisets.copyHighestCountFirst(supports).elementSet()) 
		{
			int count = supports.count(element);
			if (count < threshold) break;
			keepElements.put(element, counter);
			counter++;
		}
		System.out.println("Thresholded map size: " + keepElements.size());
		return keepElements;
	}


	protected static void buildTree() throws IOException
	{
		Node root = new Node("");

		BufferedReader br = new BufferedReader(new FileReader(new File("/home/gavin/workspace/frequentitemset/src/main/resources/smaller.txt")));
		String line;
		while ((line = br.readLine()) != null) 
		{
			Node parent = root;
			String[] tokens = line.split(",");
			for (String token: tokens) 
			{
				System.out.println("current node: " + parent);
				// if parent does not have child
				if (parent.hasChildWithValue(token)) {
					System.out.println("Parent has child " + token + ". Incrementing");
					Node extantChild = parent.getChildWithValue(token); 
					extantChild.increment();
					parent = extantChild;
				}
				else {
					Node node = new Node(token); // TODO not sure parent is required
					parent.addChild(node);
					System.out.println("Adding child " + node + " to parent " + parent);
					parent = node;
				}				
			}
		}
		br.close();
		
		System.out.println("tree:\n\n");
		printTree(root);
		System.out.println("\n\n");
		printTreeDepthFirst(root);
	}

	private static void printTreeDepthFirst(Node root) {
		
		if (root.getValue() == "") System.out.println("Root: " + root.getValue() + " " + root.getCount());
		else if (root.getChildren().size() == 0)
			System.out.println("Leaf: " + root.getValue() + " " + root.getCount() + "\n");
		else System.out.println("Node: " + root.getValue() + " " + root.getCount());
		
		for (Node child: root.getChildren())
			printTreeDepthFirst(child);
	}

	protected static void printTree(Node node) 
	{
		System.out.println(node);
		for (Node child: node.getChildren())
			printTree(child);
		
	}


	/**
	 * http://www.florian.verhein.com/teaching/2008-01-09/fp-growth-presentation_v1%20%28handout%29.pdf
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		Multiset<String> supports = getSupports("/home/gavin/workspace/frequentitemset/src/main/resources/smaller.txt"); // TODO move out of resources
//		Map<String, Integer> thresholded = thresholdSupports(supports, 2);
		// TODO need to sort rows items first
		buildTree();
	}

}










