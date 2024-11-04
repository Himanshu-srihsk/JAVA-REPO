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

public class ConcurrentMTreeWithLimitedPropagation {
    private final TreeNode root;

    public ConcurrentMTreeWithLimitedPropagation(int rootValue) {
        this.root = new TreeNode(rootValue);
    }

    // Add operation - adds x to the node and all its parent nodes up to a certain depth
    public void add(TreeNode node, int x, int depth) {
        TreeNode current = node;
        int currentDepth = 0;
        List<Long> stamps = new ArrayList<>();

        // Lock the current node and its parents up to the specified depth
        while (current != null && currentDepth <= depth) {
            long stamp = current.lock.writeLock();
            stamps.add(stamp);
            current = current.parent;
            currentDepth++;
        }

        // Perform the addition
        current = node;
        currentDepth = 0;
        while (current != null && currentDepth <= depth) {
            try {
                // Simulate a time-consuming operation
                Thread.sleep(500); // Delay to simulate work being done
                current.value += x;
                System.out.println(Thread.currentThread().getName() + " added " + x + " to node with current value: " + current.value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
            } finally {
                current = current.parent;
                currentDepth++;
            }
        }

        // Release locks in reverse order
        for (int i = stamps.size() - 1; i >= 0; i--) {
            TreeNode releaseNode = node;
            for (int j = 0; j < i; j++) {
                releaseNode = releaseNode.parent;
            }
            releaseNode.lock.unlockWrite(stamps.get(i));
        }
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
        ConcurrentMTreeWithLimitedPropagation tree = new ConcurrentMTreeWithLimitedPropagation(10); // Initialize root with value 10

        // Create a larger tree structure
        TreeNode child1 = new TreeNode(5);
        TreeNode child2 = new TreeNode(3);
        TreeNode child3 = new TreeNode(8);
        TreeNode child4 = new TreeNode(4);
        TreeNode child5 = new TreeNode(2);
        TreeNode child6 = new TreeNode(6);
        TreeNode child7 = new TreeNode(1);
        TreeNode child8 = new TreeNode(7);

        tree.root.addChild(child1);
        tree.root.addChild(child2);
        child1.addChild(child3);
        child1.addChild(child4);
        child2.addChild(child5);
        child2.addChild(child6);
        child3.addChild(child7);
        child3.addChild(child8);

        // Create threads that modify the same nodes with depth limit
        Thread t1 = new Thread(() -> {
            tree.add(child1, 4, 1);
        }, "Thread 1");

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Thread 2 waiting to add 2 to child2...");
                tree.add(child2, 2, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 2");

        Thread t3 = new Thread(() -> {
            try {
                System.out.println("Thread 3 waiting to add 3 to child3...");
                tree.add(child3, 3, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 3");

        Thread t4 = new Thread(() -> {
            try {
                System.out.println("Thread 4 waiting to add 1 to child1...");
                tree.add(child1, 1, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 4");

        Thread t5 = new Thread(() -> {
            try {
                System.out.println("Thread 5 waiting to add 5 to child2...");
                tree.add(child2, 5, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 5");

        Thread t6 = new Thread(() -> {
            try {
                System.out.println("Thread 6 waiting to add 2 to child4...");
                tree.add(child4, 2, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 6");

        Thread t7 = new Thread(() -> {
            try {
                System.out.println("Thread 7 waiting to add 2 to child5...");
                tree.add(child5, 2, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 7");

        Thread t8 = new Thread(() -> {
            try {
                System.out.println("Thread 8 waiting to add 1 to child6...");
                tree.add(child6, 1, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Thread 8");

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the current tree values
        System.out.println("Current tree values:");
        tree.printTree();
    }
}
