import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculationTest {

    Calculation calculation = new Calculation();

    @Test
    void requirements1() {
        String str = "2 + 3 * 4 / 2";

        calculation.input(str);
        assertThat(calculation.getResult()).isEqualTo(10);
    }
}