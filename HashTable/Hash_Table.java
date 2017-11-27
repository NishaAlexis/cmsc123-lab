import java.lang.Integer;

class HashTable {
  //The hash table accepts strings as key and ints as value
  class LinkedHash {
    String key;
    int value;
    LinkedHash next;
    
    LinkedHash(String key, int value){
      this.key = key;
      this.value = value;
      this.next = null;
    }
    
    int getValue() {
      return value;
    }
    void setValue(int value) {
      this.value = value;
    }
    String getKey() {
      return key;
    }
    LinkedHash getNext() {
      return next;
    }
    void setNext(LinkedHash next) {
      this.next = next;
    }
  }
  
  final int TABLESIZE = 80;
  int size = 0;
  
  LinkedHash[] table;
  
  HashTable() {
    table = new LinkedHash[TABLESIZE];
    for (int i = 0; i < TABLESIZE; i++) {
      table[i] = null;
    }
  }
  
  int indexOf(String key) {
    LinkedHash current = table[0];
    int index = 1;
    while (current != null) {
      if (current.equals(new LinkedHash(key, 0))) {
        return index;
      }
      index++;
      current = current.next;
    }
    return index;
  }
  
  //add data into the data structure
  void put(String key, int value) {
    int hash = (indexOf(key) % TABLESIZE);
    if (table[hash] == null) {
      table[hash] = new LinkedHash(key, value);
    }
    LinkedHash entry = table[hash];
    while (entry.getNext() != null && entry.getKey() != key) {
      entry = entry.getNext();
    }
    if (entry.getKey() == key) {
      entry.setValue(value);
    } else {
      entry.setNext(new LinkedHash(key, value));
    }
    size++;
  }

  //returns the value with the given key. Return null if the key does not exist.
  int get(String key) {
    int hash = (indexOf(key) % TABLESIZE);
    LinkedHash curr = table[hash];
    if (curr == null) {
      return Integer.parseInt(null);
    } else {
      while (curr.getKey() != key) {
        curr = curr.getNext();
      }
      return curr.getValue();
    }  
  }
  
  //removes the value associated with the key and returns it. Return null if the key does not exist
  int remove(String key) {
    int hash = (indexOf(key) % TABLESIZE);
    
    if (table[hash] == null) {
      return Integer.parseInt(null);
    }

    LinkedHash prev = null;
    LinkedHash curr = table[hash];
    while (curr.getNext() != null && curr.getKey() != key) {
      prev = curr;
      curr = curr.getNext();
    }
    if (curr.getKey() == key) {
      if (prev == null) {
        table[hash] = curr.getNext();
      } else {
        prev.setNext(curr.getNext());
      }  
    }
    size--;
    return curr.getValue();
  }
  
  //return True if the key exists in the data structure, False otherwise
  boolean containsKey(String key) {
    int hash = (indexOf(key) % TABLESIZE);
    LinkedHash curr = table[hash];
    while (curr != null && curr.getKey() != key) {
      curr = curr.getNext();
    }
    return curr.equals(key);
  }
  
  //returns current size of the table
  int size() {
    return size;
  }
  
  //returns a list of all values in the data structure
  int[] values() {
    int[] list = new int[TABLESIZE];
    for (int i = 0; i < TABLESIZE; i++) {
      LinkedHash curr = table[i];
      for (int j = 0; curr != null; j++) {
        list[j] = curr.getValue();  
        curr = curr.getNext();
      }
    }
    return list;
  }
  
  //returns a list of all keys in the data structure
  String[] keys() {
    String[] list = new String[TABLESIZE];
    for (int i = 0; i < TABLESIZE; i++) {
      LinkedHash curr = table[i];
      for (int j = 0; curr != null; j++) {
        list[j] = curr.getKey();
        curr = curr.getNext();
      }
    }
    return list;
  }
}

