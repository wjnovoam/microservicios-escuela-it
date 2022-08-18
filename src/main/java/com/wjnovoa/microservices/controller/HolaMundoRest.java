package com.wjnovoa.microservices.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William Johan Novoa Melendrez
 * @date 18/08/2022
 */
@RestController
@Slf4j
public class HolaMundoRest {
    //private final Logger log = LoggerFactory.getLogger(HolaMundoRest.class)

    @GetMapping("/holamundo/{name}")
    public String saludo(@PathVariable(name = "name") String name){
        MDC.put("userid", name);

        log.trace("Ejecutando hola mundo trace");
        log.info("Ejecutando hola mundo info");
        log.debug("Ejecutando hola mundo debug");
        log.warn("Ejecutando hola mundo warn");
        log.error("Ejecutando hola mundo error");

        return "Hola mundo servicio Rest";
    }
}