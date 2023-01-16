import org.example.Gamev2;
import org.example.Pionek;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * hopefully it tests something
 */
public class TestP1 {

    @Test
    public void Test1(){
        Gamev2 gamev2 = new Gamev2();
        int size1 =8;
        boolean isPolish=false;
        for (int i = 0; i < size1 / 2 - 1; ++i) {
            gamev2.fillrow(i, size1, isPolish);
        }
        Assertions.assertNull(Pionek.getActivePionek());
    }
}
