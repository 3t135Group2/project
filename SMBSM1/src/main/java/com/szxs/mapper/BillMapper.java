package com.szxs.mapper;

import com.szxs.entity.Bill;
import com.szxs.entity.Provider;

import java.util.List;

public interface BillMapper {

    List<Bill> serachAll(Bill bill);

    List<Provider> serachPro();
    List<Provider> serachPro1(Provider provider);
    int saveBill(Bill bill);

    int delBill(Bill bill);

    Bill serachById(Bill bill);

    int updateBillById(Bill bill);
}
