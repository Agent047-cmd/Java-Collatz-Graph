import java.util.*;
import java.io.*;

public class Main
{
	public static int[] range(int number){
		int[] range = new int[number];
		int r=0;
		for(int i : range){
			range[r]=r;
			r++;
		}
		return range;
	}
	public static void main(String[] args)
	{
		List<Integer> level = new ArrayList<Integer>();
		level.add(1);
		Map<Integer, Integer> results = new HashMap<Integer, Integer>();
		
		int odd = 0;
		for(int i : range(20)){
			List<Integer> newLevel = new ArrayList<Integer>();
			
			for(int x : level){
				odd = (x != (x-4)%6)? (x-1)/3 : 0;
				//System.out.println(odd);
				if (odd>1){
					newLevel.add(odd);
					results.put(odd,x);
				}
				newLevel.add(x*2);
				results.put(x*2,x);
			}
			level=newLevel;
		}
		TreeMap<Integer, Integer> sorted = new TreeMap<>();
        sorted.putAll(results);
		try
		{
			FileOutputStream fos=new FileOutputStream("/sdcard/collatz.txt");
			StringBuilder sb=new StringBuilder();
			sb.append("digraph G {\n\tsize=\"16,16\";\n\troot=8;\n\tsplines=true;\n");
			for (int x : sorted.keySet()){
				sb.append("\t"+x+" -> "+sorted.get(x)+";\n");
			}
			sb.append("}\n");
			fos.write(sb.toString().getBytes());
		}
		catch (Exception e)
		{}
		System.out.println("Generated "+results.size()+" nodes");
	}
}
