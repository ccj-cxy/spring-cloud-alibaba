package com.snk.userprovider.controller;


import com.snk.userprovider.pojo.domain.Waybill;
import com.snk.userprovider.service.WaybillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 运单 前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/waybill")
public class WaybillController {
    @Autowired
    private WaybillService waybillService;

    @PostMapping
    public Boolean saveWaybill(@RequestBody Waybill waybill) {
        return waybillService.save(waybill);
    }

}

