package com.hackathon.medibox.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hackathon.medibox.entity.TabletStripIdInfo;

public interface TabletStripIdInfoRepository extends CrudRepository<TabletStripIdInfo, String> {
    public Optional<TabletStripIdInfo> findByStripId(String stripId);

    public void deleteByStripId(String stripId);
}
