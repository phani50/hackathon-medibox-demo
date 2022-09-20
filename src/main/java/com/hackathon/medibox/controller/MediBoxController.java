package com.hackathon.medibox.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.medibox.SwaggerConfig2;
import com.hackathon.medibox.model.TableInfoResponse;
import com.hackathon.medibox.model.TabletInfoRequest;
import com.hackathon.medibox.service.MediBoxService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { SwaggerConfig2.MEDIBOX_TAG })
@RestController
@RequestMapping("/medibox")
public class MediBoxController {

    private final Logger LOGGER = LoggerFactory.getLogger(MediBoxController.class);

    @Autowired
    private MediBoxService mediBoxService;

    @PostMapping("/tablets/details")
    @ApiOperation(value = "Save tablet information", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tablet information created successfully"),
            @ApiResponse(code = 400, message = "Bad request format") })
    public ResponseEntity<String> saveTabletInfo(@RequestBody TabletInfoRequest tabletInfoRequest) {
        LOGGER.info("Request reached to MediBoxController saveTabletInfo method");
        mediBoxService.saveTabletIndo(tabletInfoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tablets/details")
    @ApiOperation(value = "Retrive all tablet information", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrived all tablet information"),
            @ApiResponse(code = 400, message = "Bad request format") })
    public List<TableInfoResponse> findAllTabletInfos() {
        return mediBoxService.findAllTabletInfos();
    }

    @GetMapping("/tablets/details/{id}")
    @ApiOperation(value = "Fetch single tablet information by id", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrived single tablet information"),
            @ApiResponse(code = 400, message = "Bad request format") })
    public TableInfoResponse findTabletInfoByStripId(@PathVariable("id") String tabletStripId) {
        return mediBoxService.findTabletInfoByStripId(tabletStripId);
    }

    @DeleteMapping("/tablets/details")
    @ApiOperation(value = "Delete tablet information", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Tablet information deleted successfully"),
            @ApiResponse(code = 400, message = "Bad request format") })
    public void deletetabletInfo(@RequestParam(value = "id", required = false) String tabletStripId) {
        mediBoxService.deleteTabletInfo(tabletStripId);
    }
}
