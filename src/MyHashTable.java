/*
 * This is a simple implementation of HashMap. Main property implemented:
 * put, get, resize
 */
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;


public class MyHashTable<K,V> extends AbstractMap<K,V> 
	implements Map<K,V>,Cloneable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	transient Entry[] table;
	private int initialCapacity;
	private float loadFactor;
	private int capacity;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private static final int DEFAULT_CAPACITY = 16;
	private int size = 0;
	private int threshold;
	
	public MyHashTable(){
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		threshold = (int) (DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
		table = new Entry[DEFAULT_CAPACITY];
	}
	
	public MyHashTable(int initialCapacity, float loadFactor){
		this.initialCapacity = initialCapacity;
		this.loadFactor = loadFactor;
		capacity = 1;
		while(capacity < this.initialCapacity){
			capacity = capacity * 2;
		}
		threshold = (int) (capacity * loadFactor);
		table = new Entry[capacity];
	}
	
	static int hash(int h){
		//provide complementary hash function for hashcode() which is
		//not guaranteed to be well hashed.
		h = h^(h>>>20)^(h>>>12);
		h = h^(h>>>7)^(h>>>4); //provide randomness to bottom bits
		return h;
				
	}
	
	static int indexFor (int h, int length){
		return h&(length - 1); //length is a power of 2, therefore,
		                       //length - 1 is a series of 1;
	}
	
	 public V put(K key, V value) {
	        if(key==null){
	        	throw new IllegalArgumentException("Key cannot be null");
	        }
	        int hash = hash(key.hashCode());
	        int i = indexFor(hash, table.length);
	        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
	            Object k = e.key;
	            if (e.hash == hash && (k == key || key.equals(k))) {
	            	// if e.hash equals hash and e.key equals key,
	                V oldValue = e.value;
	                e.value = value;
	                //update its value
	                return oldValue;
	            }
	        }
	        addEntry(hash, key, value, i);
	        return null;
	    }
	 
	 public V get(Object key){
		 if(key==null){
	        	throw new IllegalArgumentException("Key cannot be null");
	        }
		 int hash = hash(key.hashCode());
	     int i = indexFor(hash, table.length);
		 for (Entry<K,V> e = table[i]; e != null; e = e.next) {
	            Object k = e.key;
	            if (e.hash == hash && (k == key || key.equals(k))) {
	            	
	                return e.value;
	            }
	        }
		 
		 
		 return null;
		 
	 }
	 
	 private void addEntry(int hash, K key, V value, int bucketIndex) {
		// TODO Auto-generated method stub
		Entry<K,V> e = table[bucketIndex];
		table[bucketIndex] = new Entry<K,V>(hash,key,value,e);
		
		// add a new entry to the beginning of the linked list
		size++;
		if(size > threshold){
			resize(2*table.length);
		}
	}

	

	@SuppressWarnings("rawtypes")
	private void resize(int newlength) {
		// TODO Auto-generated method stub
		//Entry[] oldTable = table;
		//int oldLength = oldTable.length;
		Entry[] newTable = new Entry[newlength];
		transfer(newTable);
		table = newTable;
		capacity = table.length;
		threshold = (int) (capacity * loadFactor);
	}



	@SuppressWarnings("rawtypes")
	private void transfer(Entry[] newTable) {
		// TODO Auto-generated method stub
		// transfer from old table to new table
		Entry[] oldTable = table;
		int newCapacity = newTable.length;
		for(int i=0;i<oldTable.length;i++){
			Entry<K,V> e = oldTable[i];
			if(e!=null){
				oldTable[i]=null;
			}
			do{
				Entry<K,V> next = e.next;
				int j = indexFor(e.hash,newCapacity); //find the correct index in newTable
				
				e.next = newTable[j];
				newTable[j] = e; //put e to the beginning of newTable[j]
				
				e = next;//next element
			}while(e!=null);
		}
	}



	public class Entry<K,V> implements Map.Entry<K, V>{
		final K key;
        V value;
        Entry<K,V> next;
        final int hash;
		
		 public Entry(int h, K k, V v, Entry<K,V> n){
			 hash = h;
			 key = k;
			 value = v;
	         next = n;	         
		 }

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V v) {
			// TODO Auto-generated method stub
			V oldValue = value;
			value = v;
			return oldValue;
		
		}
		public void recordAccess(){
			
		}
		 
	 }

}
