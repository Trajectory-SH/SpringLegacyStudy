package org.zerock.ex00.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVO {
    private Long rno;
    private Long bno;
    private String replyText;
    private String replyer;

    private LocalDateTime regTime;
    private LocalDateTime updateDate;


}
