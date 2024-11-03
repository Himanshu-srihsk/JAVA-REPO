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

    int getValue() {
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
                System.out.println(Thread.currentThread().getName() + " added " + x + " to node with current value: " + current.value);
                if (current.parent != null) {
                    System.out.println(Thread.currentThread().getName() + " modified parent node with new value: " + current.parent.value);
                }
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
                System.out.println(Thread.currentThread().getName() + " subtracted 2 from node with current value: " + current.value);
                if (current.parent != null) {
                    System.out.println(Thread.currentThread().getName() + " modified parent node with new value: " + current.parent.value);
                }
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

        // Run concurrent operations with named threads
        Thread t1 = new Thread(() -> tree.add(child1, 4), "Thread 1");
        Thread t2 = new Thread(() -> tree.subtract(child2), "Thread 2");
        Thread t3 = new Thread(() -> tree.add(tree.root, 7), "Thread 3");
        Thread t4 = new Thread(() -> tree.subtract(child1), "Thread 4");
        Thread t5 = new Thread(() -> tree.add(child2, 6), "Thread 5");

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


/*
 * 

Thread 5 added 6 to node with current value: 9
Thread 3 added 7 to node with current value: 17
Thread 1 added 4 to node with current value: 9
Thread 5 modified parent node with new value: 17
Thread 1 modified parent node with new value: 17
Thread 5 added 6 to node with current value: 23
Thread 2 subtracted 2 from node with current value: 7
Thread 2 modified parent node with new value: 27
Thread 4 subtracted 2 from node with current value: 7
Thread 4 modified parent node with new value: 27
Thread 1 added 4 to node with current value: 27
Thread 2 subtracted 2 from node with current value: 25
Thread 4 subtracted 2 from node with current value: 23
Current tree values:
Node Value: 23
Node Value: 7
Node Value: 7
 */