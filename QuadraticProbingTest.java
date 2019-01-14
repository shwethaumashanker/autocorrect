import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by shiva on 21-09-2018.
 */
public class QuadraticProbingTest {
    QuadraticProbing qp;
    ArrayList<String> listS;

    @Before
    public void setUp() throws Exception {
        try {
            /* Add path to test file here */
            Scanner s = new Scanner(new File("/Users/shwetha/Project2/src//test1.txt"));

            listS = new ArrayList<String>();
            int count = 0;
            while (s.hasNextLine())
                listS.add(s.nextLine());

            qp = new QuadraticProbing();
            for(int i=0;i<11; i++)
            {
                qp.insert(listS.get(i));
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void probing1() throws Exception {

        qp.remove(listS.get(10));
        qp.insert(listS.get(11));
        assertEquals(0,qp.probe(listS.get(11)));
    }

    @Test
    public void probing2() throws Exception {
        qp.remove(listS.get(10));
        qp.insert(listS.get(11));
        qp.insert(listS.get(10));
        for(int i=12;i<20;i++)
            qp.insert(listS.get(i));
        qp.remove(listS.get(12));
        assertEquals(1,qp.probe(listS.get(19)));
    }

    @Test
    public void probing3() throws Exception {
        qp.remove(listS.get(10));
        qp.insert(listS.get(11));
        qp.insert(listS.get(10));
        for(int i=12;i<20;i++)
            qp.insert(listS.get(i));
        qp.remove(listS.get(12));
        qp.contains(listS.get(19));
        assertEquals(0,qp.probe(listS.get(19)));
    }

    @Test
    public void lazyDeletion() throws Exception{
        qp.remove(listS.get(10));
        assertEquals(false,qp.contains(listS.get(10)));
    }



}
