import java.util.*;
public class test {
	public static void main(String[] args) {
		String s1="s1";
		String s2="s2";
		String s3="s3";
		String s4="s4";
		
		TreeSet<String> setA = new TreeSet<String>();
		TreeSet<String> setB = new TreeSet<String>();
		
		setA.add(s1);setA.add(s2);setA.add(s3);
		setB.add(s1);setB.add(s4);
		
		setA.removeAll(setB);
		return;
	}
	void abc(int x){
		int a;
	}
	//public void func(int a, int b,){}

}
