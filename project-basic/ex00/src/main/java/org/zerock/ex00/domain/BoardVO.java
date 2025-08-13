package org.zerock.ex00.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BoardVO {

    private Long bno;
    private String title;
    private String content;
    private String writer;

    private boolean delFlag;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public String getRegStr(){
        return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getUpdateStr(){

        return updateDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

}
