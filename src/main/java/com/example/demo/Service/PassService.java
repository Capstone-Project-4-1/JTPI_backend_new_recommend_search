package com.example.demo.Service;
import com.example.demo.DTO.PassSearchResultDTO;
import com.example.demo.DTO.SlideShowPassDTO;
import com.example.demo.SearchParameters;
import com.example.demo.entity.PassInformation;
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
    //신규
    public List<SlideShowPassDTO> fetchSlideShowRecommendedPasses() {
        return passRepository.findSlideShowRecommendedPasses().stream()
                .map(this::convertToSlideShowPassDTO)
                .limit(4)
                .collect(Collectors.toList());
    }
    //추천
    public List<SlideShowPassDTO> fetchSlideShowNewPasses() {
        return passRepository.findSlideShowNewPasses().stream()
                .map(this::convertToSlideShowPassDTO)
                .limit(4)
                .collect(Collectors.toList());
    }

    //검색
/*    public List<PassSearchResultDTO> searchPasses(SearchParameters searchParams) {
        return passRepository.findBySearchQuery(
                        prepareLikePattern(searchParams.getQuery()),
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

    private String prepareLikePattern(String input) {
        return input != null ? "%" + input + "%" : null;
    }*/

    //검색
    public List<PassSearchResultDTO> searchPasses(SearchParameters searchParams) {
        return passRepository.findByDynamicQuery(
                        searchParams.getsearchQuery(),
                        searchParams.getDepartureCity(),
                        searchParams.getArrivalCity(),
                        searchParams.getTransportType(),
                        searchParams.getCityNames(),
                        searchParams.getDuration()
                ).stream()
                .map(this::convertToPassSearchResultDTO)
                .collect(Collectors.toList());
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
