package ra.java_service_09.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/divide")
@Slf4j
public class DivideController {

    @GetMapping
    public String divide(@RequestParam int a, @RequestParam int b) {
        try {
            int result = a / b;
            return "Result: " + result;
        } catch (ArithmeticException ex) {
            log.error("Error occurred while dividing {} by {}: {}", a, b, ex.getMessage());
            throw ex;
        }
    }
}
