import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.*;
import java.util.*;
import java.util.ListIterator;
class Pair<K,V>
{
    /*
    The Pair class is intended to store key, value pairs. It'll be helpful
    for part 1.2 of the assignment.
    */
    public K key;
    public V value;

    public Pair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}

/**************PART 1.2.1*******************/

public class HashTableChaining<K,V>
{
   /*
    Write your code for the hashtable with chaining here. You are allowed
    to use arraylists and linked lists.
    */

   int hcapacity=0;
   int size=0;
    ArrayList<LinkedList<Pair<String,Integer>>> h;


    public HashTableChaining(int capacity)
    {
        /*
        Initialize your hashtable with capacity equal to the input capacity.
        */
        hcapacity=capacity;
        h = new ArrayList<LinkedList<Pair<String,Integer>>>(capacity);
        for(int i=0;i<=capacity;i++) {
            LinkedList<Pair<String,Integer>> object = new LinkedList<Pair<String,Integer>>();
            h.add(object);
        }

    }
    public void insert(String key, int val)
    {
        size=size+1;
        /*
        Insert the key into the hashtable if it is not already in the hashtable.
        */

        LinkedList<Pair<String,Integer>> tmp = new LinkedList<Pair<String,Integer>>();

        tmp=h.get(hash(key));

        tmp.add(new Pair(key,val));
        //System.out.println(hash(key));
        //h.add(0,tmp);
        h.set(hash(key),tmp);

    }
    public void remove(String key)
    {
        /*
        Remove key from the hashtable if it is present in the hashtable.
         */
        LinkedList<Pair<String,Integer>> head = h.get(hash(key));
        for(int i=0;i<head.size();i++)
        {
            if(head.get(i).key.equals(key))
            {
               head.remove(i);
            }
        }

    }
    public boolean contains(String key)
    {
        /*
        Search the hashtable for key, if found return true else
        return false
        */
        LinkedList<Pair<String,Integer>> head = h.get(hash(key));

        for(int i=0;i<head.size();i++)
        {
            if(head.get(i).key.equals(key))
            {
                return true;
            }
        }

        return false;
    }

    public int size()
    {
        /*
        return the total number of keys in the hashtable.
        */
        //System.out.println(h.size());
        return size;
    }

    public int hash(String key)
    {
        /*
        Use Horner's rule to compute the hashval and return it.
        */
        //size=size+1;
        int Hashvalue=0;
        int HashKey=0;

        for(int i=0;i<key.length();i++)
        {
            HashKey = (37 * HashKey + key.charAt(i)) % hcapacity;

        }

        Hashvalue=HashKey % hcapacity;
        return Hashvalue;


    }

    public int getVal(String key)
    {

        /*
        return the value corresponding to the key in the hashtable.
        */
        LinkedList<Pair<String,Integer>> head = h.get(hash(key));

        for(int i=0;i<head.size();i++)
        {
            if(head.get(i).key.equals(key))
            {
                return head.get(i).value;
            }
        }
        return 0;

    }


    public void rehash()
    {
        /*
        Resize the hashtable such that the new size is the first prime number
        greater than two times the current size.
        For example, if current size is 5, then the new size would be 11.
        */

        int newsize=nextPrime(hcapacity*2);


    }

   public int nextPrime(int no) {
        while(!Prime(no++)) {
            return no;
        }
        return 0;
    }
    public boolean Prime(int no) {
        for(int i = 2; i <= no; i++) {
            if (no % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**************PART 1.2*******************/

    public String[] mostFrequentStrings(String[] in)
    {
       /*
        Given an array of strings, print the five most
        frequent strings. You must use your implementation
        for hashtable with seperate chaining for this.
        */


        String[] mostFrequent = new String[5];
        HashTableChaining h1= new HashTableChaining(in.length);
        int[] valmostFrequent = new int[5];
        ArrayList<Pair<String,Integer>> f = new ArrayList<Pair<String,Integer>>();
        int i=0;
        int val=0;
        for(i=0;i<5;i++)
        {
            valmostFrequent[i]=0;
        }
        int val1=0;
        for( i=0;i<in.length;i++) {
            if (h1.contains(in[i]) == false) {
                h1.insert(in[i], 1);
            } else {
                val1 = h1.getVal(in[i]);
                val1++;
                LinkedList<Pair<String, Integer>> head = (LinkedList<Pair<String, Integer>>) h1.h.get(hash(in[i]));

                for (int j = 0; j < head.size(); j++) {
                    if (head.get(j).key.equals(in[i])) {
                        head.get(j).value++;

                    }

                }
            }
        }

            for( i=0;i<in.length;i++)
        {
            h1.insert(in[i],1);


            for(int k=0;k<f.size();k++)
            {
                val=0;
                if(f.get(k).key.equals(in[i]))
                {
                    f.get(k).value++;
                    val=1;
                    break;
                }
            }

            if(val==0)
            {
                f.add((new Pair(in[i],1)));
            }

        }

        int max=0;
        int posi=0;
        String pos="";
        int mid=0;
        for(i=0;i<5;i++) {
            max=0;
            posi=0;
            pos="";
            for (int j = 0; j < f.size(); j++) {
                if (f.get(j).value > max) {
                    max = f.get(j).value;
                    posi=j;
                    pos = f.get(j).key;
                }
            }
            //System.out.println("pos="+pos);
            //System.out.println("max="+max);
            mostFrequent[i] = pos;
            f.set(posi,new Pair("",0));

        }

        /*for(i=0;i<f.size();i++)
        {
            System.out.println(f.get(i).key +" "+ f.get(i).value);
        }

        for(i=0;i<mostFrequent.length;i++)
        {
            System.out.println(mostFrequent[i]);
        }
        */




        return mostFrequent;
    }
}


