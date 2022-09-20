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
import com.hackathon.medibox.dao.TabletStripIdRepository;
import com.hackathon.medibox.entity.TabletStripId;
import com.hackathon.medibox.entity.TabletStripIdInfo;
import com.hackathon.medibox.model.TableDetails;
import com.hackathon.medibox.model.TableInfoResponse;
import com.hackathon.medibox.model.TabletDetailsRequest;
import com.hackathon.medibox.model.TabletInfoRequest;

@Service
public class MediBoxService {

    private final Logger LOGGER = LoggerFactory.getLogger(MediBoxService.class);

    private final TabletStripIdRepository tabletStripIdRepository;
    private final TabletStripIdInfoRepository tabletStripIdInfoRepository;

    @Autowired
    public MediBoxService(TabletStripIdRepository tabletStripIdRepository,
            TabletStripIdInfoRepository tabletStripIdInfoRepository) {
        super();
        this.tabletStripIdRepository = tabletStripIdRepository;
        this.tabletStripIdInfoRepository = tabletStripIdInfoRepository;
    }

    @Transactional
    public void saveTabletIndo(TabletInfoRequest tabletInfoRequest) {
        LOGGER.info("Request reached to saveTabletIndo method in service class");
        TabletStripId tabletStripId = null;
        Optional<TabletStripId> optTabletStrip = tabletStripIdRepository.findByStripId(tabletInfoRequest.getId());
        if (optTabletStrip.isPresent()) {
            tabletStripId = optTabletStrip.get();
        } else {
            tabletStripId = TabletStripId.create(tabletInfoRequest.getId());
            tabletStripId = tabletStripIdRepository.save(tabletStripId);
        }
        TabletDetailsRequest details = tabletInfoRequest.getTabletDetailsRequest();
        TabletStripIdInfo tabletStripIdInfo = TabletStripIdInfo.create(details.getTabletName(), details.getQuantity(),
                details.getWeight(), Instant.parse(details.getDateOfManufacture()), Instant.parse(details.getDateOfExpiry()),
                tabletStripId);
        tabletStripIdInfoRepository.save(tabletStripIdInfo);
        LOGGER.info("Request finished saveTabletIndo method in service class");
    }

    public List<TableInfoResponse> findAllTabletInfos() {
        LOGGER.info("Request reached to findAllTabletInfos method in service class");
        List<TableInfoResponse> tableDetailsResponses = new ArrayList<>();
        List<TabletStripId> tabletStripIds = Streamable.of(tabletStripIdRepository.findAll()).toList();
        for (TabletStripId tabletStripId : tabletStripIds) {
            TableInfoResponse tableDetailsResponse = new TableInfoResponse();
            TableDetails tableDetails = new TableDetails();
            tableDetailsResponse.setId(tabletStripId.getStripId());
            Optional<TabletStripIdInfo> optTabletStripIdInfo = tabletStripId.getTabletStripIdInfos().stream().findFirst();
            if (optTabletStripIdInfo.isPresent()) {
                TabletStripIdInfo tabletStripIdInfo = optTabletStripIdInfo.get();

                // set to pojo class
                tableDetails.setDateOfExpiry(tabletStripIdInfo.getDateOfExpiry().toString());
                tableDetails.setDateOfManufacture(tabletStripIdInfo.getDateOfManufacture().toString());
                tableDetails.setQuantity(tabletStripIdInfo.getQuantity());
                tableDetails.setTabletName(tabletStripIdInfo.getTabletName());
                tableDetails.setWeight(tabletStripIdInfo.getWeight());
                tableDetails
                        .setDaysLeft(Math.abs(Duration.between(tabletStripIdInfo.getDateOfExpiry(), Instant.now()).toDays()));
            }
            tableDetailsResponse.setDetails(tableDetails);
            tableDetailsResponses.add(tableDetailsResponse);
        }
        return tableDetailsResponses;
    }

    public TableInfoResponse findTabletInfoByStripId(String stripId) {
        LOGGER.info("Request reached to findTabletInfoByStripId method in service class");
        TableInfoResponse tableDetailsResponse = new TableInfoResponse();
        TableDetails tableDetails = new TableDetails();
        Optional<TabletStripId> optTabletStrip = tabletStripIdRepository.findByStripId(stripId);
        if (optTabletStrip.isPresent()) {
            TabletStripId tabletStripId = optTabletStrip.get();
            tableDetailsResponse.setId(tabletStripId.getStripId());
            Optional<TabletStripIdInfo> optTabletStripIdInfo = tabletStripId.getTabletStripIdInfos().stream().findFirst();
            if (optTabletStripIdInfo.isPresent()) {
                TabletStripIdInfo tabletStripIdInfo = optTabletStripIdInfo.get();

                // set to pojo class
                tableDetails.setDateOfExpiry(tabletStripIdInfo.getDateOfExpiry().toString());
                tableDetails.setDateOfManufacture(tabletStripIdInfo.getDateOfManufacture().toString());
                tableDetails.setQuantity(tabletStripIdInfo.getQuantity());
                tableDetails.setTabletName(tabletStripIdInfo.getTabletName());
                tableDetails.setWeight(tabletStripIdInfo.getWeight());
                tableDetails
                        .setDaysLeft(Math.abs(Duration.between(tabletStripIdInfo.getDateOfExpiry(), Instant.now()).toDays()));
            }
            tableDetailsResponse.setDetails(tableDetails);
        }
        return tableDetailsResponse;
    }

    @Transactional
    public void deleteTabletInfo(String stripId) {
        LOGGER.info("Request reached to deleteTabletInfo method in service class");
        if (stripId != null) {
            tabletStripIdRepository.deleteByStripId(stripId);
        } else {
            tabletStripIdRepository.deleteAll();
        }
    }

}
