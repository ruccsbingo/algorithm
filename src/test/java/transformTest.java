import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by zhangbing on 2019/3/15.
 */
public class transformTest {
    @Test
    public void chooseMinimume() throws Exception {
        char[][] src = new char[][]{
                {'-', '@', '@', '@', '-'},
                {'-', '@', '@', '-', '-'},
                {'-', '@', '-', '-', '-'},
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '-'}};

        char[][] dst = new char[][]{
                {'-', '-', '-', '-', '-'},
                {'-', '-', '-', '-', '@'},
                {'-', '-', '-', '@', '@'},
                {'-', '-', '@', '@', '@'},
                {'-', '-', '-', '-', '-'}};

        Assert.assertEquals(5, transform.chooseMinimume(src, dst));
    }

    @Test
    public void transform() throws Exception {
    }

    @Test
    public void rotate90() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};

        prchar(src);
        prchar(dst);

        char[][] tmpDst = transform.rotate90(src);

        prchar(tmpDst);
        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    @Test
    public void rotate180() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

        prchar(src);
        prchar(dst);

        char[][] tmpDst = transform.rotate180(src);

        prchar(tmpDst);
        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    @Test
    public void rotate270() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{3, 6, 9}, {2, 5, 8}, {1, 4, 7}};

        prchar(src);
        prchar(dst);

        char[][] tmpDst = transform.rotate270(src);

        prchar(tmpDst);
        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    @Test
    public void reflection() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};

        prchar(src);
        prchar(dst);

        char[][] tmpDst = transform.reflection(src);

        prchar(tmpDst);
        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    @Test
    public void isCombination() throws Exception {
    }

    @Test
    public void rotateLeftDiagonal() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

        prchar(src);
        prchar(dst);

        char[][] tmpDst = transform.rotateLeftDiagonal(src);

        prchar(tmpDst);
        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    @Test
    public void rotateRightDiagonal() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{9, 6, 3}, {8, 5, 2}, {7, 4, 1}};

        prchar(src);
        prchar(dst);

        char[][] tmpDst = transform.rotateRightDiagonal(src);

        prchar(tmpDst);
        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    @Test
    public void rotateHorizontally() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{7, 8, 9}, {4, 5, 6}, {1, 2, 3}};
        char[][] tmpDst = transform.rotateHorizontally(src);

        prchar(src);
        prchar(dst);
        prchar(tmpDst);

        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    @Test
    public void rotateVertically() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        char[][] dst = new char[][]{{3, 2, 1}, {6, 5, 4}, {9, 8, 7}};
        char[][] tmpDst = transform.rotateVertically(src);

        prchar(src);
        prchar(dst);
        prchar(tmpDst);

        Assert.assertTrue(transform.isEqual(tmpDst, dst));
    }

    void prchar(char[][] arr){
        for (char i=0; i<arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println();
    }

    @Test
    public void isEqual() throws Exception {
        char[][] src = new char[][]{{1, 2, 3}, {4, 5, 6}};
        char[][] dst = new char[][]{{1, 2, 3}, {4, 5, 6}};
        Assert.assertTrue(transform.isEqual(src, dst));
    }

}