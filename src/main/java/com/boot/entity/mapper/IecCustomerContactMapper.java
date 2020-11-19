package com.boot.entity.mapper;

import com.boot.entity.IecCustomerContact;
import com.boot.entity.IecCustomerContactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IecCustomerContactMapper {
    int countByExample(IecCustomerContactExample example);

    int deleteByExample(IecCustomerContactExample example);

    int insert(IecCustomerContact record);

    int insertSelective(IecCustomerContact record);

    List<IecCustomerContact> selectByExample(IecCustomerContactExample example);

    int updateByExampleSelective(@Param("record") IecCustomerContact record, @Param("example") IecCustomerContactExample example);

    int updateByExample(@Param("record") IecCustomerContact record, @Param("example") IecCustomerContactExample example);
}