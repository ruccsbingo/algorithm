import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangbing on 2019/5/10.
 */
public class prTest {
    @Test
    public void recall() throws Exception {
        System.out.println(1 & 1 & 0);
//        pr.recall("pr-data");
    }

    @Test
    public void precision() throws Exception {
    }

    @Test
    public void parse() throws Exception {
        int[] result = pr.parse("5678119997,0,1,1,1");
        int[] expect = new int[]{0, 0, 1, 1, 1};
        assertArrayEquals(expect, result);
    }

}