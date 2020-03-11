package br.com.ht7.controllers;

import br.com.ht7.documents.Employee;
import br.com.ht7.models.EmployeeEvent;
import br.com.ht7.repositories.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.awt.*;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Employee> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable String id) {
        return repository.findById(id);
    }

    @GetMapping(value = "/events/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEventsById(@PathVariable String id) {
        return repository.findById(id).flatMapMany(employee -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

            Flux<EmployeeEvent> eEvents = Flux.fromStream(
                Stream.generate(() -> new EmployeeEvent( employee, new Date() ))
            );

            return Flux.zip(interval, eEvents).map(Tuple2::getT2);
        });
    }

    @PostMapping
    public Mono<Employee> save(@Validated @RequestBody Employee obj) {
        return repository.save(obj);
    }

    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return repository.deleteById(id);
    }
}
