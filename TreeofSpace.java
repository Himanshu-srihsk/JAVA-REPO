/*
Given a world map in the form of Generic M-ary Tree consisting of N nodes and an array queries[], the task is to implement the 
functions Lock, Unlock and Upgrade for the given tree. 
For each query in queries[], the functions return true when the operation is performed successfully,
 otherwise it returns false. The functions are defined as: 

X: Name of the node in the tree and will be unique
uid: User Id for the person who accesses node X

1. Lock(X, uid): Lock takes exclusive access to the subtree rooted.

Once Lock(X, uid) succeeds, then lock(A, any user) should fail, where A is a descendant of X.
Lock(B. any user) should fail where X is a descendant of B.
Lock operation cannot be performed on a node that is already locked.
2. Unlock(X, uid): To unlock the locked node.

The unlock reverts what was done by the Lock operation.
It can only be called on same and unlocked by same uid.
3. UpgradeLock(X, uid): The user uid can upgrade their lock to an ancestor node.

It is only possible if any ancestor node is only locked by the same user uid.
The Upgrade should fail if there is any node that is locked by some other uid Y below.



You are given a complete, balanced N-Ary Tree and must support Q queries. There are 3 kinds of queries. Return true or false depending on whether the query was successful.

Lock(v, id) - Lock vertex v for user - id
Unlock(v, id) - If vertex v is locked by the same id, unlock it.
Upgrade(v, id) - If v is unlocked and has at least one locked vertex in it's subtree and every locked vertex in the subtree of v is locked by id, unlock them and lock v instead.
Further, here are some additional constraints

A vertex cannot be locked if it has any locked ancestors or descendants, by any ID.
When a vertex is upgraded, it's locked descendants are automatically unlocked.
An upgrade operation is not possible if the vertex is already locked or has any locked ancestors
An unlock operation is only possible if the vertex is already locked and locked by the same id
*/
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


class Node{
	 String value;
	private List<Node> children;
	private int isAncestorLocked;
	private int isDescendantLocked;
	private boolean isLocked;
	private Node parent;
	private int uid;
	Node(){}
	Node(String value,Node parent){
          this.value = value;
          this.parent = parent;
          this.children=new ArrayList<>();
          this.isLocked=false;
          this.isAncestorLocked=0;
          this.isDescendantLocked=0;
          this.uid=0;
	}
	public int isAncestorLocked(){
		return isAncestorLocked;
	}
	public int isDescendantLocked(){
		return isDescendantLocked;
	}
	public boolean isLocked(){
		return isLocked;
	}
	public void setAncestorLocked(int isAncestorLocked){
		this.isAncestorLocked= isAncestorLocked;
	}
	public void setDescendantLocked(int isDescendantLocked){
		this.isDescendantLocked=isDescendantLocked;
	}
	public void setLocked(boolean isLocked){
		this.isLocked=isLocked;
	}
	public String getV(Node node){
		return node.value;
	}
	public void insert(List<String> child,Node p){
            for(String node : child){
               this.children.add(new Node(node,p));
            }
	}
	public Node getParent(){
		return this.parent;
	}
	public List<Node> getChildren(){
		return this.children;
	}
	public void setuid(int uid){
		this.uid=uid;
	}
	public int getuid(){
		return this.uid;
	}
}


class Tree{
   private Node root;
   private Map<String,Node> ref = new HashMap<String,Node>();
   Tree(){}
   public Tree(Node root){
   	this.root=root;
   }
   public Node getRoot(){
   	return this.root;
   }

   public void fillallNode(Node r){
   	if(r==null){
   		return;
   	}
   	ref.put(r.getV(r),r);
        for(Node c:r.getChildren()){
            fillallNode(c);
        }
   }
   
   public void informAncestors(Node v){
   	if(v==null){
   		return;
   	}
   	
   	v.setDescendantLocked(v.isDescendantLocked()+1);
   	informAncestors(v.getParent());
   }
   public void informDescendants(Node v,int val){
        for(Node c:v.getChildren()){
        	c.setAncestorLocked(c.isAncestorLocked()+val);
        	informDescendants(c,val);
        }
   }
   public boolean verifyDescendants(Node x,int uid,List<Node> l){
         if(x.isLocked()){
         	if(x.getuid()!=uid){
         		return false;
         	}
         	l.add(x);
         }
         if(x.isDescendantLocked()==0){
             return true;
         }
         boolean ans = true;
         for(Node c:x.getChildren()){
            ans=ans & verifyDescendants(c,uid,l);
            if(ans==false){
            	return false;
            }
         }
         return ans;
   }
   public boolean lock(String v,int uid){
     Node x= ref.get(v);
     if(x.isLocked()){
     	return false;
     }
     if(x.isAncestorLocked() !=0){
     	return false;
     }
     if(x.isDescendantLocked() !=0){
     	return false;
     }
     Node p= x.getParent();
     informAncestors(p);
     informDescendants(x,1);
     x.setLocked(true);
     x.setuid(uid);
     return true;
   }
   public boolean unlock(String v,int uid){
     Node x= ref.get(v);
     if(!x.isLocked()){
     	return false;
     }
     if(x.isLocked() && x.getuid()!=uid){
     	return false;
     }
     Node p= x.getParent();

     while(p!=null){
        p.setDescendantLocked(p.isDescendantLocked()-1);
        p = p.getParent();
    }
    informDescendants(x,1);
     x.setLocked(false);
     return true;
   }

   public boolean upgrade(String v,int uid){
     Node x= ref.get(v);
     if(x.isLocked()){
     	return false;
     }
     if(x.isAncestorLocked() !=0){
     	return false;
     }
     if(x.isDescendantLocked() ==0){
     	return false;
     }
     List<Node> l=new ArrayList<>();
     if(verifyDescendants(x,uid,l)){
        for(Node child:x.getChildren()){
        	unlock(child.value,uid);
        }
     }else{
     	return false;
     }
     lock(v,uid);
    return true;
   }

}



class TreeofSpace{

public static Node buildTree(Node root,String[] child,int m){
	Deque<Node> q = new ArrayDeque<Node>();
	q.add(root);
	int size=1;
	while(!q.isEmpty()){
           Node p = q.poll();
           if(size>=child.length){
           	continue;
           }
           List<String>temp = new ArrayList<String>();
           for(int i=size;i<size+m;i++){
           	temp.add(child[i]);
           }
           p.insert(temp,p);
           size=size+m;
           for(Node c:p.getChildren()){
                q.add(c);
           }

	}
  return root;
}

public static void main(String[] agrs){
    
   /*
     * INPUT
     * n = total number of nodes
     * m = number of child per node
     * q = number of queries
     *
     * next 'n' lines = node name in string
     * next 'q' lines = queries with (opcode, string, uid)
     *
     * opcode => 1 = Lock, 2 = Unlock, 3 = Upgrade
    */

    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int k = scn.nextInt();
    int q=  scn.nextInt();
    
    String[] arr = new String[n];
    for(int i=0 ; i<n; i++){
        arr[i] = scn.next();
    }
    Node root= new Node(arr[0],null);
    root=buildTree(root,arr,k);
    Tree tree= new Tree(root);
    tree.fillallNode(tree.getRoot());


    for(int i=0;i<q;i++){
    	
    	int op = scn.nextInt();
    	String sq=scn.next();
    	int uid = scn.nextInt();

        switch(op){
        case 1: 
	        if(tree.lock(sq,uid)){
	        	System.out.println("true");
	        }else{
	        	System.out.println("false");
	        }
	        break;
	 case 2: 
	        if(tree.unlock(sq,uid)){
	        	System.out.println("true");
	        }else{
	        	System.out.println("false");
	        }
	        break;
	 case 3: 
	        if(tree.upgrade(sq,uid)){
	        	System.out.println("true");
	        }else{
	        	System.out.println("false");
	        }   
	        break;           
        }
    }

}

}