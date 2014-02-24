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

/*
 * This is the basic data structure of the triangle graph. It stores the cell's value, references to its children,
 * a reference to its best child, and its best possible value including all of its best descendants.
 */
class Cell{
	int value;
	long bestValue = 0;
	Cell bestPath;
	ArrayList<Cell> children = new ArrayList<Cell>();
	
	public Cell(int value) {
		this.value = value;
	}	

	public void addChild(Cell child) {
		this.children.add(child);
	}
	
	// This method follows the descendants recursively and returns the cell's highest possible value 
	public long getMax() {
		if (this.bestValue > 0) {
			return this.bestValue;
		}
		if (children.isEmpty()) {
			this.bestValue = this.value;
		}else if ( children.get(0).getMax() > children.get(1).getMax() ) {
			this.bestPath = children.get(0);
			this.bestValue = children.get(0).bestValue + this.value;
		}else{
			this.bestPath = children.get(1);
			this.bestValue = children.get(1).bestValue + this.value;
		}
		return this.bestValue;
	}
	
	// Follows the cell's trail of bestPath references to print out the list of descendents which resulted 
	// in the highest total value
	public String getPath() {
		if (children.isEmpty()) {
			return String.valueOf(this.value);
		}
		return String.valueOf(this.value).concat(" ").concat(this.bestPath.getPath());
	}
}

