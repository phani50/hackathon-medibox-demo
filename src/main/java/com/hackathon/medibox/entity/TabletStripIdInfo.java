package com.hackathon.medibox.entity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TABLET_STRIP_ID_INFO")
public class TabletStripIdInfo {

    @Id
    @GeneratedValue
    @Column(name = "UUID", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "TABLET_NAME")
    private String tabletName;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "WEIGHT")
    private int weight;

    @Column(name = "DATE_OF_MANUFACTURE")
    private Instant dateOfManufacture;

    @Column(name = "DATE_OF_EXPIRY")
    private Instant dateOfExpiry;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "TABLET_STRIP_ID_UUID")
    private TabletStripId tabletStripId;

    public TabletStripIdInfo() {
        super();
    }

    public static TabletStripIdInfo create(String tabletName, int quantity, int weight, Instant dateOfManufacture,
            Instant dateOfExpiry, TabletStripId tabletStripId) {
        UUID uuid = UUID.randomUUID();
        return new TabletStripIdInfo(uuid, tabletName, quantity, weight, dateOfManufacture, dateOfExpiry, tabletStripId);
    }

    private TabletStripIdInfo(UUID uuid, String tabletName, int quantity, int weight, Instant dateOfManufacture,
            Instant dateOfExpiry, TabletStripId tabletStripId) {
        super();
        this.uuid = uuid;
        this.tabletName = tabletName;
        this.quantity = quantity;
        this.weight = weight;
        this.dateOfManufacture = dateOfManufacture;
        this.dateOfExpiry = dateOfExpiry;
        this.tabletStripId = tabletStripId;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public TabletStripId getTabletStripId() {
        return tabletStripId;
    }

    public void setTabletStripId(TabletStripId tabletStripId) {
        this.tabletStripId = tabletStripId;
    }

}
