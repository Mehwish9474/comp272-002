
/*
 * *** Mehwish Tabassum / 272-002 ***
 *
 * Homework # 2 (Programming Assignment). This Java class defines a few basic
 * manipulation operations of a binary trees.
 *
 * ONLY MODIFY THIS FILE (NOT 'Main.Java')
 *
 */

 import java.util.Queue;
 import java.util.LinkedList;
 
 public class BinaryTree {
 
     public Node root;
 
     public BinaryTree() {
         root = null;
     }
 
     public BinaryTree(Node node) {
         root = node;
     }
 
     static class Node {
         public int data;
         public Node left;
         public Node right;
 
         Node(int d) {
             data = d;
             left = null;
             right = null;
         }
 
         Node(int d, Node l, Node r) {
             data = d;
             left = l;
             right = r;
         }
     }
 
     public void deleteTree() {
         root = null;
     }
 
     // replaceValue(oldVal, newVal)
     // This method will traverse the tree using a depth first search approach,
     // and for each node found with the value of 'oldVal', replace it (update the value in place),
     // with the provided 'newVal'.
     // E.g., if your main routine invokes replaceValue(67, 99), the resultant tree would have all node values
     // containing the data value of 67 replaced with the value of 99. Note that more than one tree node
     // may contain the same value.
     public void replaceValue(int oldVal, int newVal) {
         replaceValueHelper(root, oldVal, newVal);
     }
 
     private void replaceValueHelper(Node node, int oldVal, int newVal) {
         if (node == null) {
             return;
         }
         if (node.data == oldVal) {
             node.data = newVal;
         }
         replaceValueHelper(node.left, oldVal, newVal);
         replaceValueHelper(node.right, oldVal, newVal);
     }
 
     // findMin()
     // This method will traverse the tree using depth first search traversal and return the minimum data value
     // in the binary tree. If the tree is empty, the value 'Integer.MAX_VALUE' is returned. For example, in the image above,
     // the value of 45 would be returned by the method.
     public int findMin() {
         return findMinHelper(root);
     }
 
     private int findMinHelper(Node node) {
         if (node == null) {
             return Integer.MAX_VALUE; // Return max value if tree is empty
         }
         int leftMin = findMinHelper(node.left);
         int rightMin = findMinHelper(node.right);
         return Math.min(node.data, Math.min(leftMin, rightMin));
     }
 
     // nodesGT(val)
     // This method will traverse the tree using depth first search traversal and return a count on the number of nodes
     // that contain a data value larger than the parameter 'val'. For example, if your main routine invokes nodesGT( 56 )
     // on the tree shown in the image above, the method would return the value 5 (there are 5 nodes that have a data value
     // larger than 56).
     public int nodesGT(int val) {
         return nodesGTHelper(root, val);
     }
 
     private int nodesGTHelper(Node node, int val) {
         if (node == null) {
             return 0; // Return 0 if the node is null
         }
         int count = (node.data > val) ? 1 : 0; // Check if the current node's value is greater than val
         count += nodesGTHelper(node.left, val); // Recur on the left subtree
         count += nodesGTHelper(node.right, val); // Recur on the right subtree
         return count;
     }
 
     // average()
     // This method will traverse the tree using depth first search traversal and return the average value
     // contained in the binary tree. Using the above binary tree image, the average would be 58.33.
     // See the comments in the file HW1.java for more assistance on this method.
     public double average() {
         int[] sumAndCount = averageHelper(root);
         if (sumAndCount[1] == 0) {
             return 0.0; // Return 0 if the tree is empty
         }
         return (double) sumAndCount[0] / sumAndCount[1]; // Calculate the average
     }
 
     private int[] averageHelper(Node node) {
         if (node == null) {
             return new int[]{0, 0}; // Return 0 for both sum and count if the node is null
         }
         int[] left = averageHelper(node.left); // Recur on the left subtree
         int[] right = averageHelper(node.right); // Recur on the right subtree
         int sum = node.data + left[0] + right[0]; // Calculate the sum of the current node and both subtrees
         int count = 1 + left[1] + right[1]; // Count the current node and both subtrees
         return new int[]{sum, count}; // Return the sum and count
     }
 
     // Additional method for testing: PreOrder traversal
     public String preOrder() {
         return preOrderHelper(root);
     }
 
     private String preOrderHelper(Node node) {
         if (node == null) {
             return ""; // Base case: if node is null, return empty string
         }
         return node.data + " " + preOrderHelper(node.left) + preOrderHelper(node.right); // Preorder: root, left, right
     }
 
     // Method to insert values in level order (helper function)
     public Node insert(int data) {
         Node tempNode = new Node(data);
 
         if (root == null)
             return root = tempNode;
 
         Queue<Node> queue = new LinkedList<>();
         queue.add(root);
 
         while (!queue.isEmpty()) {
             Node front = queue.peek();
 
             if (front.left == null) {
                 front.left = tempNode;
                 break;
             } else if (front.right == null) {
                 front.right = tempNode;
                 break;
             } else {
                 queue.remove();
             }
 
             if (front.left != null)
                 queue.add(front.left);
 
             if (front.right != null)
                 queue.add(front.right);
         }
 
         return tempNode;
     }
 } 
