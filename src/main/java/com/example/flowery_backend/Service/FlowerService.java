package com.example.flowery_backend.Service;

import com.example.flowery_backend.Repository.FlowerJpaRepository;
import com.example.flowery_backend.model.Entity.Flower;
import com.example.flowery_backend.model.xml.FlowerResponse;
import com.example.flowery_backend.model.xml.Result;
import com.example.flowery_backend.model.xml.Root;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class FlowerService {

    private final FlowerJpaRepository flowerJpaRepository;

    public FlowerService(FlowerJpaRepository flowerJpaRepository) {
        this.flowerJpaRepository = flowerJpaRepository;
    }

    /**
     * 꽃 정보를 가져오는 메서드입니다.
     *
     * @param flower 꽃 정보
     */
    public List<Flower> getFlowers() {
        return flowerJpaRepository.findAll();
    }

    /**
     * 꽃 정보를 이름 가져오는 메서드입니다.
     *
     * @param flowNm 꽃 이름
     * @return 꽃 정보
     */
    public List<Flower> getFlowerByNm(String flowNm) {
        return flowerJpaRepository.findByFlowNm(flowNm);
    }

    /**
     * 꽃 정보를 월 가져오는 메서드입니다.
     *
     * @param fMonth 꽃 월
     * @return 꽃 정보
     */
    public List<Flower> getFlowerByMonth(String fMonth) {
        return flowerJpaRepository.findByfMonth(fMonth);
    }


    /**
     * 꽃 정보를 일 가져오는 메서드입니다.
     *
     * @param fDay 꽃 일
     * @return 꽃 정보
     */
    public List<Flower> getFlowerByDay(String fDay) {
        return flowerJpaRepository.findByfDay(fDay);
    }


    /**
     * 꽃 정보를 API에서 가져와서 DB에 저장하는 메서드입니다.
     *
     * @return 저장된 꽃 정보 리스트
     * @throws JAXBException XML 파싱 예외
     */
    public List<Result> sync() throws JAXBException {

        String url = "https://apis.data.go.kr/1390804/NihhsTodayFlowerInfo01/selectTodayFlowerList01"
                + "?serviceKey=HvFGWMzzjELnpmmplxBv0U1ha3BPasQuWw4QhO259PrQlcw0TFd42ngp1hyVPxfpV4a0QjtQkrhvl6xbE7w3pA=="
                + "&pageNo=1"
                + "&numOfRows=370";

        RestTemplate restTemplate = new RestTemplate();
        // API 호출 및 응답 받기
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        String XML = response.getBody();
        JAXBContext context = JAXBContext.newInstance(FlowerResponse.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        FlowerResponse flowerResponse = (FlowerResponse) unmarshaller.unmarshal(new StringReader(XML));

        Root root = flowerResponse.getRoot();

        flowerJpaRepository.deleteAll(); // Clear existing data before saving new data

        root.getResults()
                .forEach(result -> {
                    Flower flower = toEntity(result);
                    flowerJpaRepository.save(flower);
                });
        return root.getResults();
    }


    /**
     * XML 결과를 Flower 엔티티로 변환하는 메서드입니다.
     *
     * @param result XML 결과
     * @return 변환된 Flower 엔티티
     */
    Flower toEntity(Result result) {
        return Flower.builder()
                .createdAt(OffsetDateTime.now())
                .flowNm(result.getFlowNm())
                .flowLang(result.getFlowLang())
                .fileName1(result.getFileName1())
                .fileName2(result.getFileName2())
                .fileName3(result.getFileName3())
                .imgUrl1(result.getImgUrl1())
                .imgUrl2(result.getImgUrl2())
                .imgUrl3(result.getImgUrl3())
                .fMonth(result.getFMonth())
                .fDay(result.getFDay())
                .build();
    }

}
