package org.zerock.ex00.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
create table tbl_board
(
    bno        int auto_increment
        primary key,
    title      varchar(500)                          not null,
    content    varchar(2000)                         not null,
    writer     varchar(100)                          not null,
    regDate    timestamp default current_timestamp() null,
    updateDate timestamp default current_timestamp() null
);

alter table tbl_board add column  delflag boolean default false;


 */
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
