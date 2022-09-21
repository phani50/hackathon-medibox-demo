package com.hackathon.medibox.entity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TABLET_STRIP_ID_INFO")
public class TabletStripIdInfo {

    @Id
    @GeneratedValue
    @Column(name = "UUID", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "STRIP_ID")
    private String stripId;

    @Column(name = "TABLET_NAME")
    private String tabletName;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "INITIAL_WEIGHT")
    private float initialWeight;

    @Column(name = "CURRENT_WEIGHT")
    private float currentWeight;

    @Column(name = "DATE_OF_MANUFACTURE")
    private Instant dateOfManufacture;

    @Column(name = "DATE_OF_EXPIRY")
    private Instant dateOfExpiry;

    public TabletStripIdInfo() {
        super();
    }

    public static TabletStripIdInfo create(String stripId, String tabletName, int quantity, float initialWeight,
            float currentWeight, Instant dateOfManufacture, Instant dateOfExpiry) {
        UUID uuid = UUID.randomUUID();
        return new TabletStripIdInfo(uuid, stripId, tabletName, quantity, initialWeight, currentWeight, dateOfManufacture,
                dateOfExpiry);
    }

    private TabletStripIdInfo(UUID uuid, String stripId, String tabletName, int quantity, float initialWeight,
            float currentWeight, Instant dateOfManufacture, Instant dateOfExpiry) {
        super();
        this.uuid = uuid;
        this.stripId = stripId;
        this.tabletName = tabletName;
        this.quantity = quantity;
        this.initialWeight = initialWeight;
        this.currentWeight = currentWeight;
        this.dateOfManufacture = dateOfManufacture;
        this.dateOfExpiry = dateOfExpiry;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTabletName() {
        return tabletName;
    }

    public void setTabletName(String tabletName) {
        this.tabletName = tabletName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Instant getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Instant dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public Instant getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(Instant dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getStripId() {
        return stripId;
    }

    public void setStripId(String stripId) {
        this.stripId = stripId;
    }

    public float getInitialWeight() {
        return initialWeight;
    }

    public void setInitialWeight(float initialWeight) {
        this.initialWeight = initialWeight;
    }

    public float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(float currentWeight) {
        this.currentWeight = currentWeight;
    }

}
