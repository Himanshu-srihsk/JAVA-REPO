//Refer this as it is corrected and modified Himansju
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;

class NotFoundException extends RuntimeException {

	public NotFoundException(final String message) {
		super(message);
	}

}
class StorageFullException extends RuntimeException {

	public StorageFullException(final String message) {
		super(message);
	}

}

class InvalidElementException extends RuntimeException {

	public InvalidElementException(final String message) {
		super(message);
	}
}

class keyalreadyExist extends RuntimeException{
	public keyalreadyExist(final String message){
		super(message);
	}
}

interface Storage<K,V>{
   V get(K key);
   void put(K key, V value);
   void remove(K key);
   boolean isStorageFull();
}

class HashMapBasedStorage<K, V> implements Storage<K, V>{
	private final Integer capacity;
	private final Map<K, V> storage;

	public HashMapBasedStorage(Integer capacity) {
		this.capacity = capacity;
		this.storage = new HashMap<>();
	}

     public V get(K key){
     	if(!this.storage.containsKey(key))
	     { 
                      throw new NotFoundException("No Value Cached with that key");                                                                                
	     }		
         return this.storage.get(key);
     }
   public void put(K key, V value){
         if(isStorageFull()){
         	throw new StorageFullException("StorageFullException");
         }
         this.storage.put(key,value);
   }
   public void remove(K key){
         if (!this.storage.containsKey(key)) {
         	 throw new NotFoundException("No Value Cached with that key");   
         }
		this.storage.remove(key);
   }

   public boolean isStorageFull() {
		return this.storage.size() == capacity;
	}
}

class DoublyLinkedListNode<E>{
      private final E element;
      DoublyLinkedListNode<E> next;
      DoublyLinkedListNode<E> prev;

      public DoublyLinkedListNode(E element) {
		this.element = element;
		this.next = null;
		this.prev = null;
	}
	public E getElement() {
		return element;
	}

	public DoublyLinkedListNode<E> getNext() {
		return next;
	}

	public DoublyLinkedListNode<E> getPrev() {
		return prev;
	}

}

class DoublyLinkedList<E>  {
	private DoublyLinkedListNode<E> head;
	private DoublyLinkedListNode<E> tail;
	public DoublyLinkedList(){
		this.head = new DoublyLinkedListNode<>(null);
		this.tail = new DoublyLinkedListNode<>(null);
		this.head.next=this.tail;
		this.tail.prev=this.head;
	}

	public void detachNode(DoublyLinkedListNode<E> node) {
			// simply shifting the pointers
			if (node != null) {
				node.prev.next = node.next;
				node.next.prev = node.prev;
			}
		}

		public void addNodeToLast(DoublyLinkedListNode<E> node) {
			node.prev = this.tail.prev;
			this.tail.prev.next = node;

			node.next = this.tail;
			this.tail.prev = node;
		}

		public DoublyLinkedListNode<E> addElementToLast(E element) {
			if (element == null) {
				throw new InvalidElementException("Null element cannot be added in list.");
			}

			DoublyLinkedListNode<E> node = new DoublyLinkedListNode<>(element);
			this.addNodeToLast(node);

			return node;
		}

        public boolean isEmpty() {
			return this.head.next == tail;
		}
		public DoublyLinkedListNode<E> getFirstNode() {
			if (isEmpty())
				return null;

			return this.head.next;
	       }

	       public DoublyLinkedListNode<E> getLastNode() {
			if (isEmpty())
				return null;

			return this.tail.prev;
		}
}
interface EvictionPolicy<K>{
	void keyAccessed(K key);
	K removeKey();
	void checkifkeyalreadyExists(K key);
	void removeFromDllAndNodeMap(K key);
}

class LRUEvictionPolicy<K> implements EvictionPolicy<K>{
	private final DoublyLinkedList<K> dll;
	private final Map<K, DoublyLinkedListNode<K>> nodeMap;

	public LRUEvictionPolicy() {
		this.nodeMap = new HashMap<>();
		this.dll = new DoublyLinkedList<>();
	}

	public void keyAccessed(K key){
              DoublyLinkedListNode<K> node;
              if (nodeMap.containsKey(key)) {
              	node=nodeMap.get(key);
              	dll.detachNode(node);
              	dll.addNodeToLast(node);
              }else {
              	node = dll.addElementToLast(key);
	        nodeMap.put(key, node);
              }
              
	}
	public void checkifkeyalreadyExists(K key){
		if (nodeMap.containsKey(key)) {
			throw new keyalreadyExist("key already exists");
		}
	}

	public K removeKey(){
           DoublyLinkedListNode<K> firstNode = dll.getFirstNode();
           if (firstNode == null) {
			return null;
		}
		dll.detachNode(firstNode);
		return firstNode.getElement();
	}
	public void removeFromDllAndNodeMap(K key){
		DoublyLinkedListNode<K> node;
		node=nodeMap.get(key);
        dll.detachNode(node);
		nodeMap.remove(key);
	}

}

class Cache<K,V>{
   private final Storage<K, V> storage;
	private final EvictionPolicy<K> evictionPolicy;

    public Cache(Storage<K, V> storage, EvictionPolicy<K> evictionPolicy) {
		this.storage = storage;
		this.evictionPolicy = evictionPolicy;
     }
     public void put(K key, V value) {
       try {
		this.evictionPolicy.checkifkeyalreadyExists(key);
         this.storage.put(key, value);
	    this.evictionPolicy.keyAccessed(key);

       }catch (StorageFullException e){
		System.out.println("came into storage full");
		
			K keyToRemove = this.evictionPolicy.removeKey();
          if (keyToRemove == null) {
			throw new RuntimeException("Unexpected state. Storage full and key is not evicting.");
		}
		this.storage.remove(keyToRemove);
	       put(key, value);
          
       }
	   catch (keyalreadyExist e1) {
		//check if the key already exist then just override the key in Hashmap storage and in the eviction poilcy just add it to last
		System.out.println("keyalreadyExist");
		// this.evictionPolicy.keyAccessed(key);
		this.evictionPolicy.removeFromDllAndNodeMap(key);
		this.storage.remove(key);
		put(key, value);
	  }
     }
     public V get(K key) {
          V value = null;
          try {
			value = this.storage.get(key);
			this.evictionPolicy.keyAccessed(key);
		} catch (NotFoundException e) {
			System.out.println("Key you are looking for, is not present in storage.");
		}

		return value;
     }


}

class CacheFactory<K, V> {
	public Cache<K, V> defaultCache(final int capacity) {
		return new Cache<K, V>(new HashMapBasedStorage<K, V>(capacity), new LRUEvictionPolicy<K>());
	}
}

class Cache_Design{

        public static void main(String[] args) {
        	CacheFactory<String,Integer> c= new CacheFactory<String,Integer>();
        	Cache<String,Integer> cache= c.defaultCache(3);
        	cache.put("test",1200);
        	cache.put("Media",19011);
        	cache.put("Himanshu",1457);
			 cache.put("cdd",14111);
			cache.put("Himanshu",14111);
        	System.out.println(""+cache.get("test"));
        	System.out.println(""+cache.get("Himanshu"));
        	System.out.println(""+cache.get("Media"));
			System.out.println(""+cache.get("cdd"));
	}
}

// came into storage full
// keyalreadyExist
// Key you are looking for, is not present in storage.
// null
// 14111
// 19011
// 14111

// C:\Users\KumarHimansh\Desktop\edit_hsk\TechieDelight>