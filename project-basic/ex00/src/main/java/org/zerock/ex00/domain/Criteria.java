package org.zerock.ex00.domain;

import lombok.Data;

@Data
public class Criteria {

    private int pageNum = 1;
    private int amount = 10;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {

        if (amount <= 10 || amount > 100) {
            this.amount = 10;
            return;
        }
        this.amount = amount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum <= 0) {
            this.pageNum = 1;
            return;
        }
        this.pageNum = pageNum;
    }

    public int getSkip() {
        return (this.pageNum - 1) * this.amount;
    }
}
