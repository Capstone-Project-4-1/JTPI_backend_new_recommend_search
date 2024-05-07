package com.example.demo.Service;
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

    public List<PassPreviewDTO> fetchRecommendedPasses() {
        // 데이터베이스에서 추천 패스 데이터를 가져옴
        List<PassInformation> recommendedPasses = passRepository.findRecommendedPasses();

        // passInformation 객체를 PassPreviewDTO로 변환
        return recommendedPasses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private PassPreviewDTO convertToDTO(PassInformation data) {
        PassPreviewDTO dto = new PassPreviewDTO();
        dto.setpassId(data.getPassId());
        dto.setTitle(data.getTitle());
        dto.setImageURL(data.getImageURL());
        return dto;
    }
}
