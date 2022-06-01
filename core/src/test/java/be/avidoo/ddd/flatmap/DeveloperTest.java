package be.avidoo.ddd.flatmap;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class DeveloperTest {

    Developer javaDev = new Developer(1, "John", List.of("DDD InPractice", "Java 17"));
    Developer phpDev = new Developer(2, "Lisa", List.of("Wordpress Advanced", "Modern PHP"));

    List<Developer> devs = List.of(javaDev, phpDev);

    @Test
    void flatmapExample() {
        List<String> output = devs.stream()
                .map(Developer::books)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertThat(output).hasSize(4);
    }

    @Test
    void functionIdentity() {
        Map<Integer, Developer> map = devs.stream()
                .collect(
                        Collectors.toMap(Developer::id, Function.identity())
                );

        assertThat(map.keySet()).hasSize(2);
    }

    @Test
    void functionIdentity2() {
        List<String> strings = List.of("john", "ann");

        Map<String, Integer> map = strings.stream()
                .collect(Collectors.toMap(Function.identity(), String::length));

        assertThat(map.get("john")).isEqualTo(4);
        assertThat(map.get("ann")).isEqualTo(3);
    }
}
