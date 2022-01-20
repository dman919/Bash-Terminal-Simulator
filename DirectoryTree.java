/**
 * David Man 111940002 RO3
 * Directory Tree class
 */

public class DirectoryTree {
	DirectoryNode root;
	DirectoryNode cursor;
	
	/**
	 * Directory Tree No argument constructor
	 */
	public DirectoryTree() {
		DirectoryNode temp = new DirectoryNode(
		  "root", null, null, null, false);
		root = temp;
		root.setDepth(0);
		root.setParent(null);
		cursor = temp;
	}
	
	/**
	 * Resets cursor method to set cursor back to root
	 */
	public void resetCursor() {
		cursor = root;
	}
	
	/**
	 * Change Directory method to move cursor through specified paths
	 * @param name
	 *   String variable for specified paths/names
	 * @throws NotADirectoryException
	 *   Thrown when node  is a file or if node doesn't exist
	 */
	public void changeDirectory(String name)
	  throws NotADirectoryException {
		DirectoryNode temp = cursor;
		String s = name;
		int count = 1;
		
		//Checks how many paths are specified
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == '/')
				count++;
		}
		
		//Moves the cursor through the specified path
		for (int i = 0; i < count; i++) {
			//Finding name in path
			if (name.contains("/")) {
				s = name.substring(0, name.indexOf("/"));
			}
			else {
				s = name;
			}

			//Checking if path is accessible
			if (cursor.getLeft() != null) {
				if (cursor.getLeft().getName().equals(s)) {
					cursor = cursor.getLeft();
				}
			}
			if (cursor.getMiddle() != null) {
				if (cursor.getMiddle().getName().equals(s)) {
					cursor = cursor.getMiddle();
				}
			}
			if (cursor.getRight() != null) {
				if (cursor.getRight().getName().equals(s)) {
					cursor = cursor.getRight();
				}
			}
			
			//Moving to next name in path
			if (name.contains("/")) {
				name = name.substring(name.indexOf("/") + 1);
			}
			
			//Exceptions
			if (cursor.getIsFile() == true) {
				cursor = cursor.getParent();
				throw new NotADirectoryException(
				  "ERROR: Cannot change directory into a file.");
			}
	
			if (cursor == temp) {
				throw new NotADirectoryException(
				  "ERROR: No such directory named '" + name + "'");
			}
		}
	}

	/**
	 * Present working directory method
	 * @return
	 *   String of directory names from root to cursor
	 */
	public String presentWorkingDirectory() {
		if (root == null) {
			return "";
		}
		else {
			rootToCursor(cursor);

			return cursor.getName();
		}
	}
	
	/**
	 * List directory method
	 * @return
	 *   String of cursor directory children
	 */
	public String listDirectory() {
		String s = "";
		if (cursor.getLeft() != null)
			s += cursor.getLeft().getName() + " ";
		if (cursor.getMiddle() != null)
			s += cursor.getMiddle().getName() + " ";
		if (cursor.getRight() != null)
			s += cursor.getRight().getName();
		
		return s;
	}
	
	/**
	 * Print Directory Tree method for printing all names starting from cursor
	 */
	public void printDirectoryTree() {
		if (root == null) {
			return;
		}
		else {
			traverseFromCursor(cursor, cursor.getDepth());
		}
	}
	
	/**
	 * Make Directory method to create and add new directory
	 * @param name
	 *   Name of directory being added
	 * @throws IllegalArgumentException
	 *   Thrown when name is invalid
	 * @throws FullDirectoryException
	 *   Thrown when directory is full
	 */
	public void makeDirectory(String name)
	  throws IllegalArgumentException, FullDirectoryException {
		//Exceptions
		if (name.contains(" ") || name.contains("/")) {
			throw new IllegalArgumentException("name is invalid");
		}
		if (cursor.getLeft() != null &&
		  cursor.getMiddle() != null &&
		  cursor.getRight() != null) {
			throw new FullDirectoryException(
			  "No empty positions in the children of this node");
		}
		
		//New directory created
		DirectoryNode temp = new DirectoryNode(name, null, null, null, false);
		
		//Adding new directory to first empty child available
		if (cursor.getLeft() == null) {
			cursor.setLeft(temp);
			cursor.getLeft().setDepth(cursor.getDepth() + 1);
			cursor.getLeft().setParent(cursor);
		}
		else if (cursor.getMiddle() == null) {
			cursor.setMiddle(temp);
			cursor.getMiddle().setDepth(cursor.getDepth() + 1);
			cursor.getMiddle().setParent(cursor);
		}
		else if (cursor.getRight() == null) {
			cursor.setRight(temp);
			cursor.getRight().setDepth(cursor.getDepth() + 1);
			cursor.getRight().setParent(cursor);
		}
	}
	
	/**
	 * Make file method to create and add new file
	 * @param name
	 *   Name of file being added
	 * @throws IllegalArgumentException
	 *   Thrown when name is invalid
	 * @throws FullDirectoryException
	 *   Thrown when directory is full
	 */
	public void makeFile(String name)
	  throws IllegalArgumentException, FullDirectoryException {
		//Exceptions
		if (name.contains(" ") || name.contains("/")) {
			throw new IllegalArgumentException("name is invalid");
		}
		if (cursor.getLeft() != null &&
		  cursor.getMiddle() != null &&
		  cursor.getRight() != null) {
			throw new FullDirectoryException(
			  "No empty positions in the children of this node");
		}
		
		//New file created
		DirectoryNode temp = new DirectoryNode(name, null, null, null, true);
		
		//Adding new file to first empty child available
		if (cursor.getLeft() == null) {
			cursor.setLeft(temp);
			cursor.getLeft().setDepth(cursor.getDepth() + 1);
			cursor.getLeft().setParent(cursor);
		}
		else if (cursor.getMiddle() == null) {
			cursor.setMiddle(temp);
			cursor.getMiddle().setDepth(cursor.getDepth() + 1);
			cursor.getLeft().setParent(cursor);
		}
		else if (cursor.getRight() == null) {
			cursor.setRight(temp);
			cursor.getRight().setDepth(cursor.getDepth() + 1);
			cursor.getLeft().setParent(cursor);
		}
	}
	
	/**
	 * Root to Cursor method to traverse from the root to the cursor
	 * @param cursor
	 *   Class object representing the cursor node
	 */
	public void rootToCursor(DirectoryNode cursor) {
		try {
			String s = "";
			while (cursor != root) {
				s = cursor.getName() + "/" + s;
				cursor = cursor.getParent();
			}
			
			s = root.getName() + "/" + s;
			s = s.substring(0, s.length() - 1);
			System.out.println(s);
		}
		catch (Exception e) {
		}
	}
	
	/**
	 * Traverse From Cursor method
	 * @param cursor
	 *   Class object representing cursor node
	 * @param depth
	 *   Integer variable representing number of indents
	 */
	public void traverseFromCursor(DirectoryNode cursor, int depth) {
		//Prints number of indents for node
		for (int i = 0; i < cursor.getDepth() - depth; i++) {
			System.out.print("    ");
		}
		//Prints '|- ' if node is a directory
		if (!cursor.isFile) {
			System.out.print("|- ");
		}
		//Prints '- ' if node is a file
		else if (cursor.isFile) {
			System.out.print("- ");
		}
		
		//Prints out name of cursor
		System.out.println(cursor.getName());
		
		//Moves through tree using recursion
		if (cursor.getLeft() != null) {
			traverseFromCursor(cursor.getLeft(), depth);
		}
		if (cursor.getMiddle() != null) {
			traverseFromCursor(cursor.getMiddle(), depth);
		}
		if (cursor.getRight() != null) {
			traverseFromCursor(cursor.getRight(), depth);
		}
	}
	
	/**
	 * Find method to find specified node using name
	 * @param cursor
	 *   Class object representing the cursor
	 * @param name
	 *   String variable representing the name of node trying to be found
	 * @param found
	 *   Boolean variable representing true if node is found, false if not
	 */
	public void find(DirectoryNode cursor, String name, boolean found) {
		//Prints path from root to cursor when node is found
		if (cursor.getName().equals(name)) {
			rootToCursor(cursor);
			found = true;
		}
		
		//Goes through the tree using recursion
		if (cursor.getLeft() != null) {
			find(cursor.getLeft(), name, found);
		}
		if (cursor.getMiddle() != null) {
			find(cursor.getMiddle(), name, found);
		}
		if (cursor.getRight() != null) {
			find(cursor.getRight(), name, found);
		}
		
		if (found == false) {
			System.out.println("ERROR: No such file exists.");
		}
	}
	
	/**
	 * Move to Parent method for moving cursor to parent node
	 */
	public void moveToParent() {
		//Checks if cursor is at root
		if (cursor == root) {
			System.out.println("ERROR: Already at root directory.");
		}
		else {
			//Moves cursor to parent
			cursor = cursor.getParent();
		}
	}
}