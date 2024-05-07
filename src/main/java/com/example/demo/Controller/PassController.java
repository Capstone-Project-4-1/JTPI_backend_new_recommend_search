package com.example.demo.Controller;
import com.example.demo.DTO.PassPreviewDTO;
import com.example.demo.Service.PassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/passes")
public class PassController {

    private final PassService passService;

    public PassController(PassService passService) {
        this.passService = passService;
    }

    @GetMapping("/recommended")
    public List<PassPreviewDTO> getRecommendedPasses() {
        return passService.fetchRecommendedPasses();
    }
}
