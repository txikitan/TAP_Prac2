/*---------------------------------------------------
- TAP JavaDataFrame: Abstract class that provides
    a simple api to access the data in the DataFrame

    Gabriel Garcia
/----------------------------------------------------*/

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DataFrame implements Iterable<List<String>> {


    /*Counters for the number of columns(labels) and rows of the dataframe*/
    protected int labels, rows;
    /*The DataFrame will be stored in a LinkedHashMap with the label and all the column values stored in a list<string> */
    protected LinkedHashMap<String, List<String>> df = new LinkedHashMap<>();

    /*Making the dataframe iterable through columns*/
    public Iterator<List<String>> iterator() {
        return new Iterator<List<String>>() {
            Iterator<String> it = df.keySet().iterator();

            public boolean hasNext() {
                return it.hasNext();
            }

            /*For the method next we return the corresponding column */
            public List<String> next() {
                String key = it.next();
                return df.get(key);
            }
        };
    }

    /*Returns a specific item by text key*/
    public String at(int row, String label) {
        if(this.df.get(label)!=null && row<this.rows && row>=0) {
            return this.df.get(label).get(row);
        }
        else return null;
    }

    /*Returns a specific item by number indexes*/
    public String iat(int row, int col) {
        if(col<this.labels && col>=0 && row<this.rows && row>=0) {
            Set<String> keys = this.df.keySet();
            String[] keyList = keys.toArray(new String[0]);
            String key = keyList[col];
            return this.df.get(key).get(row);
        }
        return null;
    }

    /*Returns number of columns*/
    public int columns() {
        return this.labels;
    }

    /*Returns number of rows*/
    public int size() {
        return this.rows;
    }

    /*Sorts a list based in a comparator*/
    public List<String> sort(String label, Comparator<String> comparator) {
        if(this.df.get(label)!=null) {
            List<String> sortedList = this.df.get(label);
            sortedList.sort(comparator);
            return sortedList;
        }
        else return null;
    }

    /*Queries all values of the desired col that comply the predicate*/
    public LinkedHashMap<String, List<String>> query( String label, Predicate<String> predicate) {
        return df.entrySet().stream().collect  // this first stream is for the dataframe itself (each entry)
                (Collectors.toMap(Map.Entry::getKey, key->{
                    if(key.getKey().equals(label))                         // if we are on the correct column
                        return key.getValue().stream().filter(predicate).//  filter each column(value) in a second inner stream and map it (toMap)
                                    collect(Collectors.toList());       //  collect it to form each column filtered
                            return key.getValue();
                        },
                        (u,v)-> {throw new IllegalStateException();}    // if repeated key, throw exception
                        ,LinkedHashMap<String,List<String>>::new)); // we want it as a linkedHashMap to preserve the order
    }

    public void accept(AbstractDataFrameVisitor v, String label) {
        v.visit(this, label);
    }
}
