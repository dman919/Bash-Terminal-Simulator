/**
 * David Man 111940002 RO3
 * Directory Node class
 */

public class DirectoryNode {
	//Variable for name of node
	String name;
	DirectoryNode left;
	DirectoryNode middle;
	DirectoryNode right;
	//Boolean variable true if node is file, false if node is directory
	boolean isFile;
	//Variable for amount of indents per node
	int depth;
	//Node representing the parent node of the current node
	DirectoryNode parent;
	
	/**
	 * Directory Node No argument constructor
	 */
	public DirectoryNode() {
		name = "";
		left = null;
		middle = null;
		right = null;
		isFile = true;
	}
	
	/**
	 * Directory Node Argument constructor
	 * @param name
	 *   String variable for name of node
	 * @param left
	 *   Class object representing left node
	 * @param middle
	 *   Class object representing middle node
	 * @param right
	 *   Class object representing right node
	 * @param isFile
	 *   Boolean variable checking if node is file or not
	 */
	public DirectoryNode(String name, DirectoryNode left,
	  DirectoryNode middle, DirectoryNode right, boolean isFile) {
		this.setName(name);
		this.setLeft(left);
		this.setMiddle(middle);
		this.setRight(right);
		this.setIsFile(isFile);
	}
	
	public String getName() {
		return name;
	}
	/**
	 * Setter for name variable
	 * @param name
	 *   String variable for name of node
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public DirectoryNode getLeft() {
		return left;
	}
	/**
	 * Setter for left node
	 * @param left
	 *   Class object for left node
	 */
	public void setLeft(DirectoryNode left) {
		this.left = left;
	}

	public DirectoryNode getMiddle() {
		return middle;
	}
	/**
	 * Setter for middle node
	 * @param middle
	 *   Class object for middle node
	 */
	public void setMiddle(DirectoryNode middle) {
		this.middle = middle;
	}

	public DirectoryNode getRight() {
		return right;
	}
	/**
	 * Setter for right node
	 * @param right
	 *   Class object for right node
	 */
	public void setRight(DirectoryNode right) {
		this.right = right;
	}
	
	public boolean getIsFile() {
		return isFile;
	}
	/**
	 * Setter for isFile boolean variable
	 * @param isFile
	 *   Boolean variable for checking if node is a file
	 */
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	
	public int getDepth() {
		return depth;
	}
	/**
	 * Setter for depth of indents for nodes
	 * @param depth
	 *   Depth variable for indents
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public DirectoryNode getParent() {
		return parent;
	}
	/**
	 * Setter for parent node
	 * @param parent
	 *   Class object representing parent node of current node
	 */
	public void setParent(DirectoryNode parent) {
		this.parent = parent;
	}
	
	/**
	 * Add child method for adding a child to a directory
	 * @param newChild
	 *   Class object representing child being added
	 * @throws FullDirectoryException
	 *   Thrown if directory is full
	 * @throws NotADirectoryException
	 *   Thrown if node is a file and not a directory
	 */
	public void addChild(DirectoryNode newChild)
	  throws FullDirectoryException, NotADirectoryException {
		if (left != null && middle != null && right != null) {
			throw new FullDirectoryException(
			  "No empty positions in the children of this node");
		}
		if (isFile == true) {
			throw new NotADirectoryException("This node is not a file");
		}
		
		if (left == null) {
			left = newChild;
		}
		else if (middle == null) {
			middle = newChild;
		}
		else {
			right = newChild;
		}
	}
}

/**
 * Not A Directory Exception class
 */
@SuppressWarnings("serial")
class NotADirectoryException extends Exception {
	public NotADirectoryException(String e) {
		super (e);
	}
}

/**
 * Full Directory Exception class
 */
@SuppressWarnings("serial")
class FullDirectoryException extends Exception {
	public FullDirectoryException(String e) {
		super (e);
	}
}