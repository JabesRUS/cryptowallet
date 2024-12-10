package com.javaacademy.cryptowallet.mapper;

import com.javaacademy.cryptowallet.dto.CryptoAccountDto;
import com.javaacademy.cryptowallet.entity.CryptoAccount;
import org.springframework.stereotype.Service;

@Service
public class CryptoAccountMapper {
    public CryptoAccountDto convertToDto(CryptoAccount account) {
        return new CryptoAccountDto(account.getUserLogin(),
                account.getCurrency(),
                account.getAmountOnAccount(),
                account.getUuid());
    }
}
