import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by shiva on 19-09-2018.
 */
public class HashTableChainingTest {
    ArrayList<String> listS;
    HashTableChaining<String, Integer> ht;
    @Before
    public void setUp() throws Exception {
        Scanner s = new Scanner(new File("/Users/shwetha/Project2/src/test.txt"));
        listS = new ArrayList<String>();
        int size =0;
        while(s.hasNextLine())
            listS.add(s.nextLine());
        ht = new HashTableChaining<String, Integer>(11);
        for(int i=0;i<500; i++){
            ht.insert(listS.get(i), 1);
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void hash() throws Exception {
        ht = new HashTableChaining<String, Integer>(11);
        int[] expectedHashes = {0, 2, 5, 10, 10};
        for(int i=0;i<5; i++)
        {
            assertEquals(expectedHashes[i], ht.hash(listS.get(i)));
        }
    }

    @Test
    public void insert_Contains() throws Exception {
        for(int i=0;i<500; i++)
        {
            assertTrue(ht.contains(listS.get(i)));
        }
    }

    @Test
    public void size() throws Exception {

        assertEquals(500, ht.size());
    }

    @Test
    public void remove() throws Exception {
        for(int i=0;i<500; i++) {
            ht.remove(listS.get(i));
            assertEquals(false,ht.contains(listS.get(i)));
        }
        for(int i=0;i<500; i++) {
            assertEquals(false,ht.contains(listS.get(i)));
        }
    }

    @Test
    public void mostFrequentStrings() throws Exception {
        String[] in = new String[listS.size()];
        for (int i = 0; i<listS.size(); i++){
            in[i] = listS.get(i);
        }
        ArrayList<String> expectedRes = new ArrayList<>(Arrays.asList("ZmPJaMhiUr", "szDZHwYiHQ", "uNjsnFOxLW", "VwEkneRFGJ", "WVetUMccSY"));
        String[] res = ht.mostFrequentStrings(in);

        for (String str: res)
        {
            System.out.printf(str);
            //assertTrue(expectedRes.contains(str));
        }
    }


}
