package com.luca.AutogarageGianlucaMeens.UsedPart;

import com.luca.AutogarageGianlucaMeens.Part.part;
import com.luca.AutogarageGianlucaMeens.Repair.Repair;

import javax.persistence.*;

@Entity
@Table(name = "UsedParts")
public class UsedPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "partID")
    part partID;

    @ManyToOne
    @JoinColumn(name = "repairID")
    Repair repairID;

    @Column(name = "amount")
    int amount;

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPartID(part partID) {
        this.partID = partID;
    }

    public void setRepairID(Repair repairID) {
        this.repairID = repairID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    //getters
    public Long getId() {
        return id;
    }

    public part getPartID() {
        return partID;
    }

    public Repair getRepairID() {
        return repairID;
    }

    public int getAmount() {
        return amount;
    }
}
