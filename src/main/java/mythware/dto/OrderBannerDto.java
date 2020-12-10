package mythware.dto;

import lombok.Data;
import mythware.domain.Banner;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderBannerDto {
    private String id;
    private int sequenceNo;

    public static List<Banner> toOrderBanners(List<OrderBannerDto> orderBannerDto) {
        return orderBannerDto.stream()
                .map(OrderBannerDto::toOrderBanner)
                .collect(Collectors.toList());
    }

    public static Banner toOrderBanner(OrderBannerDto orderBannerDto) {
        return Banner.builder()
                .id(orderBannerDto.id)
                .sequenceNo(orderBannerDto.sequenceNo)
                .build();
    }
}
