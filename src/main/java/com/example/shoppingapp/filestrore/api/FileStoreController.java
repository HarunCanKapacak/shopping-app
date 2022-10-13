package com.example.shoppingapp.filestrore.api;

import com.example.shoppingapp.filestrore.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/filestore")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FileStoreController {

    private final FileStoreService fileStoreService;


    @GetMapping("/{id}")
    public Mono<Void> getImage(@PathVariable("id") String id, ServerWebExchange serverWebExchange) throws  Exception{
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.IMAGE_JPEG);
        DataBufferFactory factory = new DefaultDataBufferFactory();
        return fileStoreService.getImage(id).flatMap(img -> {
           return response.writeWith(Flux.just(factory.wrap(img)));
        });

    }
}
