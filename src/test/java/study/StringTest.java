package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void requirements1() {
        List<Integer> actual = Arrays.stream("1,2".split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Integer[] arr = actual.toArray(new Integer[actual.size()]);

        assertThat(arr).containsExactly(1, 2);

        actual = Arrays.stream("1".split(",")).map(Integer::parseInt).collect(Collectors.toList());
        arr = actual.toArray(new Integer[actual.size()]);

        assertThat(arr).containsExactly(1);
    }

    @Test
    void requirements2() {
        List<Integer> actual = Arrays.stream("(1,2)".replaceAll("[\\(\\)]", "").split(",")).map(Integer::parseInt).collect(Collectors.toList());
        Integer[] arr = actual.toArray(new Integer[actual.size()]);

        assertThat(arr).containsExactly(1, 2);
    }

    @Test
    @DisplayName("str.charAt에 5를 넣었을 때 index가 벗어났다는 예외 메세지 발생")
    void requirements3() {
        String str = "abc";

        Throwable thrown = catchThrowable(() -> { System.out.println(str.charAt(5)); });

        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: 5");
    }
}
