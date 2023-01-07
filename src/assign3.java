
import java.util.*;
import java.io.*;

public class assign3 {
	public static class Node<A>{
		A data= null;
		ArrayList<Node<A>> child= new ArrayList<Node<A>>();
		Node<A> parent;
		
		Node<A> left;
		Node<A> right;
		
	public Node() {
		this.data=null;
		this.parent=null;
	}
	public void setLeft(Node<A> left) {
		this.left = left;
	}
	public Node getLeft() {
		return left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node<A> right) {
		this.right = right;
	}
	public void setData(A data) {
		this.data=data;
	}
	public void setParent(Node<A> p) {
		this.parent=p;
	}
	public void addChild(Node<A> c,Node<A> p) {
		this.child.add(c);
		c.setParent(p);
	}
	public Node getChild(int i) {
		return child.get(i);
	}
	public void printData() {
		System.out.print(data+ " ");
	}
	public void printChildren() {
		for(int i = 0; i<child.size();i++) {
			child.get(i).printData();
		}
	}
	public Boolean hasChildren() {
		if(child.isEmpty()) {
			return false;
		}
		return true;
	}
	public Boolean isEmpty() {
		if(this.data==null) {
			return true;
		}
		return false;
	}
	public A Data(){return this.data;}
	
	public Boolean isRoot() {
		
		if(this.parent==null) {
			return true;
		}
		return false;
	}
	}

	public static class Tree<E>{
	Node<E> root=new Node<E>();
	public Tree() {}
	public void setRoot(Node<E> r) {
		this.root=r;
	}
}
	public static class Stack<E>{
		ArrayList <E> s= new ArrayList<E>();
	public Stack() {
		s= new ArrayList<E>();
	}

	public E Pop(){
		if(s.isEmpty()) { //checks to see if the stack is empty
			System.out.println("List is empty"); 
			return null; //returns null
			
		}else {
			return s.remove(s.size()-1); //returns the last value and removes it
		}

	}

	public void Push(E e){
		s.add(e);
	}

	public void PrintStack() {
		if(s.isEmpty()) {
			System.out.println("The list is empty");
		}else {
			System.out.println(s.toString()); //prints the stack as a string
			}
		}
		public boolean isEmpty() {
			if(s.isEmpty()) {
				return true;
			}else {
				return false;
				}
			}
	
		public E Peek() {
			return s.get(s.size()-1);
		}
		public int Length() {return s.size();}
		public String PrintString() {
			E[] tmp=(E[])s.toArray();
			for(int i=0;i<tmp.length;i++) {
				System.out.print(tmp[i]);
			}
			return null;
		}
		public E[] toArray() {
			return (E[]) s.toArray();
		}
	}
			

	static class Pair {
        public Node node;
        public int childrenIndex;
        public Pair(Node _node, int _childrenIndex)
        {
            node = _node;
            childrenIndex = _childrenIndex;
        }
    }
	
//	public static int DFS(Tree t) {
//		ArrayList<Integer> DFSOrder= new ArrayList<Integer>();
//		Stack s = new Stack<Node>();
//		s.Push(t.root); //pushes root onto stack
//		Node temp = new Node();
//		while(!s.isEmpty()) {
//			temp=(Node) s.Pop();
//				if(temp.hasChildren()) {
//					for(int i=0;i<temp.child.size();i++) {
//						s.Push((Node)temp.child.get(i));
//					}
//					
//				}
//				DFSOrder.add((Integer) temp.data);
//			}
//		//System.out.println(DFSOrder);
//		return DFSOrder.size();
//	}
		
	public static Node FindTarget(Tree t, int target) {
		ArrayList<Integer> DFSOrder= new ArrayList<Integer>();
		Stack s = new Stack<Node>();
		s.Push(t.root); //pushes root onto stack
		Node temp = new Node();
		while(!s.isEmpty()) {
			temp=(Node) s.Pop();
				if(temp.hasChildren()) {
					for(int i=0;i<temp.child.size();i++) {
						s.Push((Node)temp.child.get(i));
						if((Integer)((Node) temp.child.get(i)).Data()==target) {
							return ((Node) temp.child.get(i));
						}
					}
					
				}
				DFSOrder.add((Integer) temp.data);
			}
		System.out.println(DFSOrder);
		return null;
	}
	
	public static void PrintPreOrder(Node n) {
		if(n == null) {
			return;
		}
		
		System.out.print(n.data + " ");
		
		for(int i=0;i<n.child.size();i++) {
			PrintPreOrder((Node)n.child.get(i));
		}
		
	}
	
	public static void PrintTreeasFile(Tree t) {
		ArrayList<Integer> DFSOrder= new ArrayList<Integer>();
		Stack s = new Stack<Node>();
		s.Push(t.root); //pushes root onto stack
		Node temp = new Node();
		while(!s.isEmpty()) {
			temp=(Node) s.Pop();
				if(temp.hasChildren()) {
					System.out.print(temp.data);
					System.out.print(" ");
					temp.printChildren();
					System.out.println("");
					for(int i=0;i<temp.child.size();i++) {
						s.Push((Node)temp.child.get(i));
					}
					
				}
				DFSOrder.add((Integer) temp.data);
			}
	}
	
	public static Tree ReadingTreefromFile(File f) {
		try {
			
			Scanner scnr = new Scanner(f);
			int counter=0;
			Tree tree=new Tree();
			Node targetNode= new Node();
			ArrayList<String> line = new ArrayList<String>();
			/*
			 * Below code loads the tree from the file
			 * 
			 */
			while(scnr.hasNext()) {
				String fullLine=scnr.nextLine();
				
				line.add(fullLine);
				if(fullLine=="") {
					continue;
				}
				String[] fullLineP=fullLine.split("\\s");
				for(int i=0;i<fullLineP.length;i++) {
					if(counter==0&&i==0) { //setting root
						Node<Integer> n = new Node<Integer>();
						n.setData(Integer.parseInt(fullLineP[i]));
						n.setParent(n);
						tree.setRoot(n);
					}else if(counter==0&&i!=0) {//setting root's children
					
						Node<Integer> n = new Node<Integer>();
						n.setData(Integer.parseInt(fullLineP[i]));
						if(binary == true) {
							if(tree.root.left!=null){
	                            tree.root.setRight(n);
	                            //System.out.println("root Right: " + tree.root.getRight().Data());
	                        }
	                        else{
	                            tree.root.setLeft(n);
	                            //System.out.println("root Left: " + tree.root.getLeft().Data());
	                        }
						}
						tree.root.addChild(n, tree.root);
					}
					if(counter!=0&&i==0) {
						targetNode=FindTarget(tree,Integer.parseInt(fullLineP[i])); //sets the node up for which children are going to be added to it
					} else if(counter!=0&&i!=0) {
						Node<Integer> n = new Node<Integer>();
						n.setData(Integer.parseInt(fullLineP[i]));
						if(binary == true) {
							if(tree.root.left!=null){
	                            targetNode.setRight(n);
	                            //System.out.println("root Right: " + tree.root.getRight().Data());
	                        }
	                        else{
	                            targetNode.setLeft(n);
	                        }
						}
						
						targetNode.addChild(n, targetNode);
					}
				
				}
			counter++;		
			
			
			}
			//Tree has been read from the file
			for(String var: line) {
				System.out.println(var);
			}
			return tree;
		}catch(Exception e) {
		System.out.println(e);
		}
		return null;
		}
	
	
	 static int currentRootIndex = 0;
	 static Stack<Pair> stack = new Stack<Pair>();
	 static ArrayList<Integer> postorderTraversal = new ArrayList<Integer>();
	    
	public static void PrintPostorder(Node root)
    {
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                 
                // Push the root and it's index
                // into the stack
                stack.Push(new Pair(root, currentRootIndex));
                currentRootIndex = 0;
     
                // If root don't have any children's that
                // means we are already at the left most
                // node, so we will mark root as null
                if (root.child.size() >= 1) {
                    root = (Node) root.child.get(0);
                }
                else {
                    root = null;
                }
                continue;
            }
     
            // We will pop the top of the stack and
            // add it to our answer
            Pair temp = stack.Pop();
            postorderTraversal.add((Integer)temp.node.Data());
     
            // Repeatedly we will the pop all the
            // elements from the stack till popped
            // element is last children of top of
            // the stack
            while (!stack.isEmpty() && temp.childrenIndex ==
                    stack.Peek().node.child.size() - 1) {
                temp = stack.Pop();
                 
                postorderTraversal.add((Integer)temp.node.Data());
            }
     
            // If stack is not empty, then simply assign
            // the root to the next children of top
            // of stack's node
            if (!stack.isEmpty()) {
                root = (Node) stack.Peek().node.child.get(temp.childrenIndex + 1);
                currentRootIndex = temp.childrenIndex + 1;
            }
        }
     
        for(Integer var: postorderTraversal) {
        	System.out.print(var + " ");
        }
//        return postorderTraversal;
    }
	
	 static void PrintInorder(Node node)
	    {
		 
		 if (node == null) { return; }
		 PrintInorder(node.left); 
		 System.out.printf("%s ", node.Data()); 
		 PrintInorder(node.right);

	    }
	 
	 static void PrintLevelorder(Node root)
	 {
	     if (root == null)
	         return;
	  
	     // Standard level order traversal code
	     // using queue
	     Queue<Node > q = new LinkedList<>(); // Create a queue
	     q.add(root); // Enqueue root
	     while (!q.isEmpty())
	     {
	         int n = q.size();
	  
	         // If this node has children
	         while (n > 0)
	         {
	             // Dequeue an item from queue
	             // and print it
	             Node p = q.peek();
	             q.remove();
	             System.out.print(p.Data() + " ");
	  
	             // Enqueue all children of
	             // the dequeued item
	             for (int i = 0; i < p.child.size(); i++)
	                 q.add((Node)p.child.get(i));
	             n--;
	         }
	          
	         // Print new line between two levels
	         System.out.println();
	     }
	 }
		
	public static boolean binary = true;
	
	public static void main(String[] args) {
		
		
		System.out.println("Is This A Binary Tree? Y/N?");
		Scanner scnr = new Scanner(System.in);
		char answer = scnr.next().charAt(0);
	
		if (answer == 'Y' || answer == 'y') {
			binary = true;
		}else if (answer == 'N' || answer == 'n'){
			binary = false;
		}
		
		File treeFile = new File("tree.txt");
		Tree tree = new Tree();
		System.out.println("Printing tree structure as file: ");

		tree=ReadingTreefromFile(treeFile);
		System.out.println();

		System.out.println("Printing tree structure in preorder:");
		PrintPreOrder(tree.root);
		System.out.println();

		System.out.println("Printing tree structure in postorder:");
		PrintPostorder(tree.root);
		System.out.println();
		
		
		if(binary){
			System.out.println("Printing tree structure in inorder");
			PrintInorder(tree.root);
			System.out.println();
		}

		System.out.println("Printing tree structure in levelorder:");
		PrintLevelorder(tree.root);

	}

}
