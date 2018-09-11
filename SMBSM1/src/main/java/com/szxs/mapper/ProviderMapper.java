package com.szxs.mapper;

import com.szxs.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
   // int deletePro(@Param("id") int id);

    int updatePro(Provider provider);

    int savePro(Provider provider);

    List<Provider> serachPro();

    Provider serachById(Provider provider);

    int delPro(Provider provider);




}
