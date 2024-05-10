package com.example.demo.Controller;
import com.example.demo.DTO.PassPreviewDTO;
import com.example.demo.DTO.SlideShowPassDTO;
import com.example.demo.DTO.PassSearchResultDTO;
import com.example.demo.SearchParameters;
import com.example.demo.Service.PassService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/slideshow/recommended")
    public List<SlideShowPassDTO> getSlideShowRecommendedPasses() {
        return passService.fetchSlideShowRecommendedPasses();
    }

    @GetMapping("/new")
    public List<PassPreviewDTO> getNewPasses() {
        return passService.fetchNewPasses();
    }
    @GetMapping("/slideshow/new")
    public List<SlideShowPassDTO> getSlideShowNewPasses() {
        return passService.fetchSlideShowNewPasses();
    }

    @PostMapping("/search")
    public List<PassSearchResultDTO> searchPasses(@RequestBody SearchParameters searchParams) {
        return passService.searchPasses(searchParams);
    }

}
