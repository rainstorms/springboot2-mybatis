package mythware.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class Region {
    private String id;  // 主键
    private String region; // 区域
    private String name; // 姓名
    private String mobile; // 移动电话
    private String email; // 邮箱
    private String position; // 职位
    private int state; // 状态 0删除 1是启用
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
