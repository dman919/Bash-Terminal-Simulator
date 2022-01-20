/**
 * David Man 111940002 RO3
 * Bash Terminal class
 */

import java.util.Scanner;

public class BashTerminal {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Starting bash terminal.");
		
		//Class object that represents the tree of directories and files
		DirectoryTree tree = new DirectoryTree();
		//String representing the user inputs
		String s = "";
		
		//Loop for program until prompted to end
		while (!(s.equals("exit"))) {
			System.out.print("[daman]: $ ");
			s = input.nextLine();
			
			//Present working directory
			if (s.equals("pwd")) {
				tree.presentWorkingDirectory();
			}
			
			//List directory
			if (s.equals("ls")) {
				System.out.println(tree.listDirectory());
			}
			
			//Print directory tree
			if (s.equals("ls -R")) {
				System.out.println();
				tree.printDirectoryTree();
				System.out.println();
			}
			
			//Change directory
			if (s.contains("cd ")) {
				if (s.equals("cd /")) {
					tree.resetCursor();
				}
				else if (s.equals("cd ..")) {
					tree.moveToParent();
				}
				else {
					String name = s.substring(s.indexOf(" ") + 1);
					try {
						tree.changeDirectory(name);
					}
					catch (NotADirectoryException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			//Make directory
			if (s.contains("mkdir ")) {
				String name = s.substring(s.indexOf(" ") + 1);
				try {
					tree.makeDirectory(name);
				}
				catch (IllegalArgumentException | FullDirectoryException e) {
					System.out.println("ERROR: Present directory is full.");
				}
			}
			
			//Make file
			if (s.contains("touch ")) {
				String name = s.substring(s.indexOf(" ") + 1);
				try {
					tree.makeFile(name);
				}
				catch (IllegalArgumentException | FullDirectoryException e) {
					System.out.println("ERROR: Present directory is full.");
				}
			}
			
			//Find directory
			if (s.contains("find ")) {
				String name = s.substring(s.indexOf(" ") + 1);
				boolean found = false;
				
				tree.find(tree.root, name, found);
			}
			
			//Terminates program
			if (s.equals("exit")) {
				System.out.println("Program terminating normally");
				System.exit(0);
			}
		}
	}
}
