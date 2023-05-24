package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.domain.CreditsRecord;
import com.neusoft.elmboot.domain.CreditsWallet;
import com.neusoft.elmboot.mapper.CreditsWalletMapper;
import com.neusoft.elmboot.service.CreditsWalletService;

@Service
public class CreditsWalletServiceImpl implements CreditsWalletService {
	@Autowired
	CreditsWalletMapper creditsWalletMapper;

	@Override
	public Integer getCreditsById(String userId) {
		updateValidState(userId);
		List<CreditsWallet> creditsWallets = creditsWalletMapper.getCreditsWalletById(userId);
		Integer totalCredit = 0;
		for (CreditsWallet creditsWallet : creditsWallets) {
			if (creditsWallet.getIsValid() == 1)
				totalCredit += creditsWallet.getRemainCredits();
		}
		return totalCredit;
	}

	@Override
	public int creditsAdd(Integer amount, String userId) {
		CreditsWallet creditsWallet = new CreditsWallet(userId, amount);
		CreditsRecord creditsRecord = new CreditsRecord(userId, amount);
		creditsWalletMapper.saveCreditsWallet(creditsWallet);
		if (amount > 0)
			return creditsWalletMapper.saveCreditsRecord(creditsRecord);
		else
			return 0;
	}

	@Override
	public int creditsDeduct(Integer amount, String userId) {
		Integer total = amount;
		try {
			if (getCreditsById(userId) < amount) {
				throw new Exception("余额不足");
			} else {
				List<CreditsWallet> creditsWallets = creditsWalletMapper.getCreditsWalletById(userId);
				for (CreditsWallet creditsWallet : creditsWallets) {
					if (creditsWallet.getRemainCredits() >= amount) {
						creditsWallet.creditsDeduct(amount);
						creditsWalletMapper.creditsDeduct(creditsWallet);
					} else {
						amount -= creditsWallet.getRemainCredits();
						creditsWallet.setRemainCredits(0);
						creditsWalletMapper.creditsDeduct(creditsWallet);
					}
					if (amount == 0)
						break;
				}
				CreditsRecord creditsRecord = new CreditsRecord(userId, -total);
				return creditsWalletMapper.saveCreditsRecord(creditsRecord);
			}
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int updateValidState(String userId) {
		List<CreditsWallet> creditsWallets = creditsWalletMapper.getCreditsWalletById(userId);
		Integer rows = 0;
		for (CreditsWallet creditsWallet : creditsWallets) {
			if ((!creditsWallet.isCreditsValid(creditsWallet)) | (creditsWallet.getRemainCredits() == 0)) {
				creditsWalletMapper.updateValidState(creditsWallet.getCreditsId());
				rows++;
			}
		}
		return rows;
	}

	@Override
	public List<CreditsRecord> getCreditsRecordById(String userId) {
		return creditsWalletMapper.getCreditsRecordById(userId);
	}
}
