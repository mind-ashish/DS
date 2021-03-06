package findNextLargerNodeForGivenInputElementN;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
public class Solution {
	public static TreeNode<Integer> findNextLargerNode(TreeNode<Integer> root, int n){
		int min = Integer.MAX_VALUE;
		TreeNode<Integer> node1= null;
		if(root.data>n) {
			node1=root;
			min= root.data-n;
		}
		for(int i=0;i<root.children.size();i++) {
			TreeNode<Integer> node2= findNextLargerNode(root.children.get(i), n);
			if(node2!=null) {
				if(node1==null) {
					node1= node2;
					min= node2.data-n;
				}else {
					if((node2.data-n)<min) {
						min = node2.data-n;
						node1=node2;
					}
				}
			}
		}
		return node1;
	}
	public static TreeNode<Integer> inputLevelWise(Scanner scan){
		System.out.println("Enter root data");
		int n= scan.nextInt();
		TreeNode<Integer> root = new TreeNode<>(n);
		Queue<TreeNode<Integer>> q= new LinkedList<>();
		q.add(root);
		while(q.size()!=0) {
			TreeNode<Integer> parent = q.remove();
			System.out.println("Enter no of children for "+ parent.data);
			int m= scan.nextInt();
			for(int i=0;i<m;i++) {
				System.out.println("Enter "+(i+1)+" th child of "+parent.data );
				int k= scan.nextInt();
				TreeNode<Integer> node= new TreeNode<>(k);
				parent.children.add(node);
				q.add(node);
			}
		}
		return root;
	}
	public static void printLevelWise(TreeNode<Integer> root) {
	Queue<TreeNode<Integer>> q1 = new LinkedList<>();
	q1.add(root);
	while(q1.size()!=0) {
		Queue<TreeNode<Integer>> q2= new LinkedList<>();
		String str="";
		while(q1.size()!=0) {
			TreeNode<Integer> p= q1.remove();
			q2.add(p);
			str=str+p.data+" ";
		}
		System.out.println(str);
		while(q2.size()!=0) {
			TreeNode<Integer> t=q2.remove();
			for(int i=0;i< t.children.size();i++) {
				q1.add(t.children.get(i));
			}
		}
	}
		
	}
public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int n= scan.nextInt();
	TreeNode<Integer> root = inputLevelWise(scan);
	TreeNode<Integer> node = findNextLargerNode(root, n);
	System.out.println(node.data);
	//printLevelWise(root);
	scan.close();
}
}
class TreeNode<T>{
public	T data;
public	ArrayList<TreeNode<T>> children;
	public TreeNode(T data) {
		this.data=data;
		children = new ArrayList<>();
	}
}