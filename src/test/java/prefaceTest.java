import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhangbing on 2019/5/11.
 */
public class prefaceTest {
    @Test
    public void translate() throws Exception {
        preface.init();
        for (int i=900; i<=1000; i++) {
            System.out.println(preface.translate(i));
        }
    }

    @Test
    public void caculate() throws Exception {
    }

}