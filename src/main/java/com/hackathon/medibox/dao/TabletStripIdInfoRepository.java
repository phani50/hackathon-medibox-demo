package com.hackathon.medibox.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hackathon.medibox.entity.TabletStripId;
import com.hackathon.medibox.entity.TabletStripIdInfo;

public interface TabletStripIdInfoRepository extends CrudRepository<TabletStripIdInfo, String> {
    public List<TabletStripIdInfo> findByTabletStripId(TabletStripId tabletStripId);
}
