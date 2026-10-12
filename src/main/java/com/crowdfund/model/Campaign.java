package com.crowdfund.model;

import java.math.BigDecimal;

public class Campaign {
    private int id;
    private String title;
    private String description;
    private String creatorName;
    private BigDecimal targetAmount;
    private BigDecimal raisedAmount;
    private String status;

    public Campaign(int id, String title, String description, String creatorName,
                    BigDecimal targetAmount, BigDecimal raisedAmount, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creatorName = creatorName;
        this.targetAmount = targetAmount;
        this.raisedAmount = raisedAmount;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCreatorName() { return creatorName; }
    public BigDecimal getTargetAmount() { return targetAmount; }
    public BigDecimal getRaisedAmount() { return raisedAmount; }
    public String getStatus() { return status; }
}
