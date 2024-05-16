package com.example.demo.Service;
import com.example.demo.DTO.PassSearchResultDTO;
import com.example.demo.DTO.SlideShowPassDTO;
import com.example.demo.SearchParameters;
import com.example.demo.entity.PassInformation;
import com.example.demo.DTO.PassPreviewDTO;
import com.example.demo.Repository.PassRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassService {

    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }
    //추천
    public List<PassPreviewDTO> fetchRecommendedPasses() {
        List<PassInformation> recommendedPasses = passRepository.findRecommendedPasses();
        return recommendedPasses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<SlideShowPassDTO> fetchSlideShowNewPasses() {
        return passRepository.findSlideShowNewPasses().stream()
                .map(this::convertToSlideShowPassDTO)
                .limit(4)
                .collect(Collectors.toList());
    }

    //신규
    public List<PassPreviewDTO> fetchNewPasses() {
        List<PassInformation> newPasses = passRepository.findNewPasses();
        return newPasses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<SlideShowPassDTO> fetchSlideShowRecommendedPasses() {
        return passRepository.findSlideShowRecommendedPasses().stream()
                .map(this::convertToSlideShowPassDTO)
                .limit(4)
                .collect(Collectors.toList());
    }

    //검색
    public List<PassSearchResultDTO> searchPasses(SearchParameters searchParams) {
        return passRepository.findBySearchQuery(
                        prepareQuery(searchParams.getQuery()),
                        prepareLikePattern(searchParams.getDepartureCity()),
                        prepareLikePattern(searchParams.getArrivalCity()),
                        searchParams.getTransportType(),
                        prepareLikePattern(searchParams.getCityNames()),
                        searchParams.getDuration(),
                        searchParams.getQuantityAdults(),
                        searchParams.getQuantityChildren()
                ).stream()
                .map(this::convertToPassSearchResultDTO)
                .collect(Collectors.toList());
    }

    private String prepareQuery(String query) {
        return query != null ? "%" + query + "%" : null;
    }

    private String prepareLikePattern(String input) {
        return input != null ? "%" + input + "%" : null;
    }


    private PassPreviewDTO convertToDTO(PassInformation data) {
        PassPreviewDTO dto = new PassPreviewDTO();
        dto.setpassID(data.getpassID());
        dto.setTitle(data.getTitle());
        dto.setImageURL(data.getImageURL());
        return dto;
    }
    private SlideShowPassDTO convertToSlideShowPassDTO(PassInformation data) {
        SlideShowPassDTO dto = new SlideShowPassDTO();
        dto.setId(data.getpassID());
        dto.setTitle(data.getTitle());
        dto.setImageUrl(data.getImageURL());
        return dto;
    }
    private PassSearchResultDTO convertToPassSearchResultDTO(PassInformation data) {
        PassSearchResultDTO dto = new PassSearchResultDTO();
        dto.setpassID(data.getpassID());
        dto.setImageUrl(data.getImageURL());
        dto.setTitle(data.getTitle());
        dto.setCityNames(data.getCityNames());
        dto.setPrice(data.getPrice());
        return dto;
    }



}
