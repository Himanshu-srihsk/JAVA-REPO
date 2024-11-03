import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.StampedLock;

class TreeNode {
    int value;
    TreeNode parent;
    List<TreeNode> children;
    StampedLock lock;

    TreeNode(int value) {
        this.value = value;
        this.children = new ArrayList<>();
        this.lock = new StampedLock();
    }

    // Add child to this node
    void addChild(TreeNode child) {
        child.parent = this;
        children.add(child);
    }
    int getValue(){
        return value;
    }
}

public class ConcurrentMTree {
    private final TreeNode root;

    public ConcurrentMTree(int rootValue) {
        this.root = new TreeNode(rootValue);
    }

    // Add operation - adds x to the node and all its parent nodes
    public void add(TreeNode node, int x) {
        TreeNode current = node;
        while (current != null) {
            long stamp = current.lock.writeLock();
            try {
                current.value += x;
                System.out.println("Added " + x + " to node with current value: " + current.value);
            } finally {
                current.lock.unlockWrite(stamp);
            }
            current = current.parent;
        }
    }

    // Subtract operation - subtracts 2 from the node and all its parent nodes
    public void subtract(TreeNode node) {
        TreeNode current = node;
        while (current != null) {
            long stamp = current.lock.writeLock();
            try {
                current.value -= 2;
                System.out.println("Subtracted 2 from node with current value: " + current.value);
            } finally {
                current.lock.unlockWrite(stamp);
            }
            current = current.parent;
        }
    }

    // Optimistic read to get node value without locking other threads out
    public int readValue(TreeNode node) {
        long stamp = node.lock.tryOptimisticRead();
        int value = node.value;

        // Validate that no write occurred during optimistic read
        if (!node.lock.validate(stamp)) {
            // Fallback to a read lock
            stamp = node.lock.readLock();
            try {
                value = node.value;
            } finally {
                node.lock.unlockRead(stamp);
            }
        }
        return value;
    }

    // Method to print the tree values starting from the root
    public void printTree() {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            int value = currentNode.getValue();
            System.out.println("Node Value: " + value);

            // Add children to queue for BFS traversal
            queue.addAll(currentNode.children);
        }
    }

    // Example usage with threads
    public static void main(String[] args) {
        ConcurrentMTree tree = new ConcurrentMTree(10); // Initialize root with value 10
        TreeNode child1 = new TreeNode(5);
        TreeNode child2 = new TreeNode(3);
        tree.root.addChild(child1);
        tree.root.addChild(child2);

        // Run concurrent operations
        Thread t1 = new Thread(() -> tree.add(child1, 4));
        Thread t2 = new Thread(() -> tree.subtract(child2));
        Thread t3 = new Thread(() -> tree.add(tree.root, 7));
        Thread t4 = new Thread(() -> tree.subtract(child1));
        Thread t5 = new Thread(() -> tree.add(child2, 6));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the current tree values
        System.out.println("Current tree values:");
        tree.printTree();
    }
}
