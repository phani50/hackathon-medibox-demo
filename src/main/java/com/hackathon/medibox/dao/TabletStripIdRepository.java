package com.hackathon.medibox.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hackathon.medibox.entity.TabletStripId;

public interface TabletStripIdRepository extends CrudRepository<TabletStripId, String> {
    public Optional<TabletStripId> findByStripId(String stripId);

    public void deleteByStripId(String stripId);
}
