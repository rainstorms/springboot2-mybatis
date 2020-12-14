package mythware.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class BasicInformation {
    private String companyName; // 公司名称
    private String telephone; // 电话
    private String fax; // 传真
    private String email; // 邮箱
    private String address; // 地址
    private LocalDateTime updateTime; // 更新时间
}

