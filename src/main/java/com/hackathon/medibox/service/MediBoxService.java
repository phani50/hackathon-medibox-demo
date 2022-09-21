package com.hackathon.medibox.service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.hackathon.medibox.dao.TabletStripIdInfoRepository;
import com.hackathon.medibox.entity.TabletStripIdInfo;
import com.hackathon.medibox.model.StripDetails;
import com.hackathon.medibox.model.TableInfoResponse;
import com.hackathon.medibox.model.TabletDetailsRequest;
import com.hackathon.medibox.model.TabletInfoRequest;

@Service
public class MediBoxService {

    private final Logger LOGGER = LoggerFactory.getLogger(MediBoxService.class);

    private final TabletStripIdInfoRepository tabletStripIdInfoRepository;

    @Autowired
    public MediBoxService(TabletStripIdInfoRepository tabletStripIdInfoRepository) {
        super();
        this.tabletStripIdInfoRepository = tabletStripIdInfoRepository;
    }

    @Transactional
    public void saveTabletIndo(TabletInfoRequest tabletInfoRequest) {
        LOGGER.info("Request reached to saveTabletIndo method in service class");
        String stripId = tabletInfoRequest.getId();
        TabletDetailsRequest details = tabletInfoRequest.getTabletDetailsRequest();
        TabletStripIdInfo tabletStripIdInfo = null;
        Optional<TabletStripIdInfo> optTabletStripIdInfo = tabletStripIdInfoRepository.findByStripId(stripId);
        if (optTabletStripIdInfo.isPresent()) {
            tabletStripIdInfo = optTabletStripIdInfo.get();
            tabletStripIdInfo.setCurrentWeight(details.getWeight());
        } else {
            tabletStripIdInfo = TabletStripIdInfo.create(stripId, details.getTabletName(), details.getQuantity(),
                    details.getWeight(), details.getWeight(), Instant.parse(details.getDateOfManufacture()),
                    Instant.parse(details.getDateOfExpiry()));
        }
        tabletStripIdInfoRepository.save(tabletStripIdInfo);
        LOGGER.info("Request finished saveTabletIndo method in service class");
    }

    public List<TableInfoResponse> findAllTabletInfos() {
        LOGGER.info("Request reached to findAllTabletInfos method in service class");
        List<TableInfoResponse> tableDetailsResponses = new ArrayList<>();
        List<TabletStripIdInfo> tabletStripIdInfos = Streamable.of(tabletStripIdInfoRepository.findAll()).toList();
        for (TabletStripIdInfo tabletStripIdInfo : tabletStripIdInfos) {
            TableInfoResponse tableDetailsResponse = new TableInfoResponse();
            tableDetailsResponse.setId(tabletStripIdInfo.getStripId());
            StripDetails stripDetails = toStripDetails(tabletStripIdInfo);
            tableDetailsResponse.setDetails(stripDetails);
            tableDetailsResponses.add(tableDetailsResponse);
        }
        return tableDetailsResponses;
    }

    private StripDetails toStripDetails(TabletStripIdInfo tabletStripIdInfo) {
        StripDetails stripDetails = new StripDetails();
        // set to pojo class
        stripDetails.setDateOfExpiry(tabletStripIdInfo.getDateOfExpiry().toString());
        stripDetails.setDateOfManufacture(tabletStripIdInfo.getDateOfManufacture().toString());
        stripDetails.setQuantity(tabletStripIdInfo.getQuantity());
        stripDetails.setTabletName(tabletStripIdInfo.getTabletName());
        stripDetails.setInitialWeight(tabletStripIdInfo.getInitialWeight());
        stripDetails.setCurrentWeight(tabletStripIdInfo.getCurrentWeight());
        stripDetails
                .setDaysLeft(Math.abs(Duration.between(tabletStripIdInfo.getDateOfExpiry(), Instant.now()).toDays()));
        return stripDetails;
    }

    public TableInfoResponse findTabletInfoByStripId(String stripId) {
        LOGGER.info("Request reached to findTabletInfoByStripId method in service class");
        TableInfoResponse tableDetailsResponse = new TableInfoResponse();
        Optional<TabletStripIdInfo> optTabletStripIdInfo = tabletStripIdInfoRepository.findByStripId(stripId);
        if (optTabletStripIdInfo.isPresent()) {
            TabletStripIdInfo tabletStripIdInfo = optTabletStripIdInfo.get();
            tableDetailsResponse.setId(tabletStripIdInfo.getStripId());
            StripDetails stripDetails = toStripDetails(tabletStripIdInfo);
            tableDetailsResponse.setDetails(stripDetails);
        }
        return tableDetailsResponse;
    }

    public void deleteTabletInfo(String stripId) {
        LOGGER.info("Request reached to deleteTabletInfo method in service class");
        if (stripId != null) {
            tabletStripIdInfoRepository.deleteByStripId(stripId);
        } else {
            tabletStripIdInfoRepository.deleteAll();
        }
    }

}
