package mythware.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Banner {
    private String id;  // 注解
    private String title; // 标题名称
    private String image2560; // 2560 px的图
    private String image1440; // 1440 px的图
    private String image1024; // 1024 px的图
    private String image768; // 768 px的图
    private LocalDateTime startTime; // banner生效的开始时间
    private LocalDateTime endTime; // banner生效的结束时间
    private int sequenceNo; // 展示序号
    private int state; // 状态 详情见 BannerState
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    @AllArgsConstructor @NoArgsConstructor
    public enum BannerState {
        DELETE(0, "删除"),
        ENABLE(1, "启用"),
        DISABLE(2, "禁用");

        @Getter
        Integer code;
        String desc;
    }
}
