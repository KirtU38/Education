package ru.beloshitsky.SpringAOP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  Logger log = LoggerFactory.getLogger(Controller.class);

  @GetMapping
  public void print() {
    log.info("PRINT");
  }

  @GetMapping("/param/{variable}")
  public void printParams(@PathVariable("variable") String variable) {
    log.info(variable);
  }

  @GetMapping("/return/{variable}")
  public String printReturn(@PathVariable("variable") String variable) {
    return variable;
  }
}
// Здесь один метод принимает просто по "/", другой с параметрами, а третий с параметрами и return
// type
