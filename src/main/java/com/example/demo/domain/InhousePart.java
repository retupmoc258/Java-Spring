package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *
 *
 *
 */
@Entity
@DiscriminatorValue("1")
public class InhousePart extends Part{
    int partId;

    public InhousePart() {
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public InhousePart(String name, double price, int inv, int partId) {
        super(name, price, inv);
        this.setPartId(partId);
    }

    public InhousePart(String name, double price, int inv, int partId, int minInv, int maxInv) {
        super(name, price, inv, minInv, maxInv);
        this.setPartId(partId);
    }
}
