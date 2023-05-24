package com.neusoft.elmboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.domain.CreditsRecord;
import com.neusoft.elmboot.service.CreditsWalletService;

@RestController
@RequestMapping("/CreditsWalletController")
public class CreditsWalletController {
	@Autowired
	private CreditsWalletService creditsWalletService;

	@RequestMapping("/getCreditsById")
	public Integer getCreditsById(String userId) {
		return creditsWalletService.getCreditsById(userId);
	}
	
	@RequestMapping("/creditsAdd")
	public int creditsAdd(Integer amount, String userId) {
		return creditsWalletService.creditsAdd(amount, userId);
	}
	
	@RequestMapping("/creditsDeduct")
	public int creditsDeduct(Integer amount, String userId) throws Exception {
		return creditsWalletService.creditsDeduct(amount, userId);
	}
	
	@RequestMapping("/getCreditsRecordById")
	public List<CreditsRecord> getCreditsRecordById(String userId){
		return creditsWalletService.getCreditsRecordById(userId);
	}
}
