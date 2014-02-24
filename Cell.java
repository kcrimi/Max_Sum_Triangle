import java.util.ArrayList;

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