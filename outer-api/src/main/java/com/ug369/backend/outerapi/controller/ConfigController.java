package com.ug369.backend.outerapi.controller;


import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.base.response.BasicResponse;
import com.ug369.backend.bean.base.response.DataResponse;
import com.ug369.backend.bean.bean.response.PlatformResponse;
import com.ug369.backend.bean.bean.response.TradeResponse;
import com.ug369.backend.outerapi.annotation.UserInjected;
import com.ug369.backend.service.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ConfigController {

	@Autowired
	private SystemConfigService systemConfigService;


	@RequestMapping(value = "/config/market", method = RequestMethod.GET)
	public DataResponse config() {
		return new DataResponse(systemConfigService.getMarkets());
	}

	@RequestMapping(value = "/config/market", method = RequestMethod.POST)
	public BasicResponse config(@RequestBody PlatformResponse platform) {
		systemConfigService.addMarket(platform);
		return BasicResponse.success();
	}

	@RequestMapping(value = "/config/market/{id}", method = RequestMethod.DELETE)
	public BasicResponse config(@PathVariable("id") long id) {
		systemConfigService.deleteMarket(id);
		return BasicResponse.success();
	}

	@RequestMapping(value = "/config/trade", method = RequestMethod.GET)
	public DataResponse trade(@UserInjected WebUser user) {
		return new DataResponse(systemConfigService.getTrades(user));
	}

	@RequestMapping(value = "/config/trade", method = RequestMethod.POST)
	public BasicResponse trade(@RequestBody TradeResponse trade, @UserInjected WebUser user) {
		systemConfigService.addTrade(trade,user);
		return BasicResponse.success();
	}

	@RequestMapping(value = "/config/trade/{id}", method = RequestMethod.DELETE)
	public BasicResponse trade(@PathVariable("id") long id) {
		systemConfigService.deleteTrade(id);
		return BasicResponse.success();
	}


}
