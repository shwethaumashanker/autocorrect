/* Quadratic Probing */
public class QuadraticProbing<AnyType>
{
    private static final int DEFAULT_TABLE_SIZE = 13;
    private HashEntry<AnyType> [ ] array; // The array of elements

    public static class HashEntry<AnyType>
    {
        /* Initialize the entries here. You can write a constructor for the same */
        public AnyType  element;
        public boolean isActive;  // For Lazy deletion
        public String toString()
        {
            if(this.element!=null)
                return (String) element;
            else
                return "NULL";
        }
    }


    /* Construct the hash table */
    public QuadraticProbing( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

/* Construct the hash table */

    public QuadraticProbing( int size )
    {
		/* allocate memory to hash table */
		array = new HashEntry[size];
    }


    /* Return true if currentPos exists and is active - Lazy Deletion*/
    public boolean isActive(int position)
    {
        return array[position].isActive;
    }


    /* Find an item in the hash table. */
    public boolean contains( AnyType x )
    {
        /* Should return the active status of key in hash table */
        int temp = hash((x.toString()), array.length);
        if(array[temp]==null) {
            return true;
        }
        return false;
    }


/* Insert into the Hash Table */

    public void insert( AnyType x )
    {
		/* Insert an element */
       int temp = hash((x.toString()), array.length);
     if(array[temp]==null)
      {
          //array[temp]=  x.toString();
      }


    }


/* Remove from the hash table. */

    public void remove( AnyType x )
    {
		/* Lazy Deletion*/
    }


    /* Rehashing for quadratic probing hash table */
    private void rehash( )
    {
        int newsize=nextPrime(array.length*2);
        HashEntry<AnyType> [] array1 = new HashEntry[newsize];
        for(int i=0;i<array.length;i++)
        {
            array1[i]=array[i];
        }
        array=array1;

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


    /* Hash Function */
    public int hash( String key, int tableSize )
    {
        /**  Make sure to type cast "AnyType"  to string
         before calling this method - ex: if "x" is of "AnyType",
         you should invoke this function as hash((x.toString()), tableSize) */
        int Hashvalue=0;
        int HashKey=0;

        for(int i=0;i<key.length();i++)
        {
            HashKey = (37 * HashKey + key.charAt(i)) % tableSize;

        }

        Hashvalue=HashKey % tableSize;

		/* Compute the hash code*/
        return Hashvalue;
    }



    public int probe(AnyType x)
    {
		/* Return the number of probes encountered for a key */
        int num_of_probes = 1;

        return num_of_probes;
    }

}

