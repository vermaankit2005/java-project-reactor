import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Operators {

    public static void main(String[] args) throws InterruptedException {

        getMono().subscribe(System.out::println);

        Thread.sleep(5000);
    }

    public static Flux<String> getMono() {
        return Mono.just("Ankit")
                .flatMapMany(Operators::getNameAsFlux); // Use of flatmap many.
    }

    public static Flux<String> getFlux() {
        return Flux.fromIterable(List.of("Ankit", "Priyanka"))
                .flatMap(name -> getNameAsFlux(name))
                .map(String::toUpperCase)
                .switchIfEmpty(Flux.just("Empty Flux, Switching"));
    }

    public static Mono<List<String>> getNameAsMono(String name) {
        List<String> stringList = List.of("Priyanka".split(""));
        return Mono.just(stringList);
    }

    public static Flux<String> getNameAsFlux(String name) {
        return Flux.fromIterable(List.of(name.split("")));
    }
}
