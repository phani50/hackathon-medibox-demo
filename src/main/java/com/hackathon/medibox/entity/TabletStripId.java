package com.hackathon.medibox.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TABLET_STRIP_ID")
public class TabletStripId {

    @Id
    @GeneratedValue
    @Column(name = "UUID", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "ID")
    private String stripId;

    @OneToMany(mappedBy = "tabletStripId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TabletStripIdInfo> tabletStripIdInfos = new ArrayList<>();

    public TabletStripId() {
        super();
    }

    public static TabletStripId create(String stripId) {
        return new TabletStripId(UUID.randomUUID(), stripId);
    }

    private TabletStripId(UUID uuid, String stripId) {
        super();
        this.uuid = uuid;
        this.stripId = stripId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStripId() {
        return stripId;
    }

    public void setStripId(String stripId) {
        this.stripId = stripId;
    }

    public List<TabletStripIdInfo> getTabletStripIdInfos() {
        return tabletStripIdInfos;
    }

    public void setTabletStripIdInfos(List<TabletStripIdInfo> tabletStripIdInfos) {
        this.tabletStripIdInfos = tabletStripIdInfos;
    }

}
