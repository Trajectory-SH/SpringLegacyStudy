package org.zerock.ex00.domain;

import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.*;

@Getter
@ToString
public class PageDto {
    private int startPage;//시작페이지 번호
    private int endPage;//화면 마지막 번호
    private boolean prev , next;

    private int total;
    private Criteria cri;

    public PageDto(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        this.endPage = (int) (ceil(cri.getPageNum() / 10.0)) * 10;

        this.startPage = this.endPage - 9;

        int realEnd = (int) (ceil((total * 1.0) / cri.getAmount()));

        if (realEnd <= this.endPage) {
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
