import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class TriangleSolver {

	/**
	 * This Class works to solve the Maxium Path Triangle puzzle for Yodle. It uses a recursive algorithm to 
	 * explore children nodes with a dynamic programming technique to save time and prevent redundant 
	 * calculations. Solution is complete and optimal.
	 * 
	 * Kevin Crimi
	 * 12/28/2013
	 * 
	 */
	
	Cell root; //This is the top cell of the triangle
	
	public static void main(String[] args) {
		TriangleSolver ts = new TriangleSolver();
		ts.buildTriangle("triangle.txt");
		System.out.println(ts.root.getMax());
		System.out.println(ts.root.getPath());
	}

	//This method builds the triangle graph from the .txt file provided
	public void buildTriangle(String fileName) {
		// This creates the tree structure from the .txt file
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String strLine;
			String[] strs;
			ArrayList<Cell> parents = new ArrayList<Cell>();
			ArrayList<Cell> newLevel = new ArrayList<Cell>();
			while((strLine = in.readLine()) != null){
				strs = strLine.split(" ");
				for (String s : strs ) {
					newLevel.add(new Cell(Integer.valueOf(s)));
				}
				if (newLevel.size()==1) {
					this.root = newLevel.get(0);
				}else{
					for (int i=0; i < newLevel.size(); i++) {
						if ( i != 0) {
							parents.get(i-1).addChild(newLevel.get(i));
						}
						if ( i != (newLevel.size()-1) ) {
							parents.get(i).addChild(newLevel.get(i));
						}
								
					}
				}
				parents = new ArrayList<Cell>(newLevel);
				newLevel.clear();
			}
		} catch (Exception e) {
			// FilenotFound
			e.printStackTrace();
		}
		
	}
}