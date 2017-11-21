//George Polyak
//Prog7
//CSCD300

public class ChainHashMap implements Map {


    private SLinkedList[] table;
    private int size;

    public ChainHashMap(int tableSize) {

        table = new SLinkedList[tableSize];
        size = tableSize;
        for (int i = 0; i < tableSize; i++) {
            table[i] = new SLinkedList();
        }

    }

    @Override
    public int size() {
        return size;
    }

    private int hashFunction(int id) {
        return (7 * id + 29) % 5;
    }

    @Override
    public String get(int id) {
        return table[hashFunction(id)].search(id);
    }

    @Override
    public void put(int id, String value) {

        int i = hashFunction(id);
        String s = table[i].search(id);
        if (s == null) {
            table[i].add(id, value);
        } else {
            table[i].update(id, value);
        }

    }

    @Override
    public String remove(int id) {

        String s = table[hashFunction(id)].remove(id);
        return s;

    }

    @Override
    public String toString() {

        String s = "";
        for(int i = 0; i < size; i++) {
            s += table[i] + "\n";
        }
        return s;

    }

}
