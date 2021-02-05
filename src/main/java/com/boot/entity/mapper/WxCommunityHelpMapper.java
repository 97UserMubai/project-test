package com.boot.entity.mapper;

import com.boot.entity.WxCommunityHelp;
import com.boot.entity.WxCommunityHelpExample;
import com.boot.entity.WxCommunityHelpWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxCommunityHelpMapper {
    int countByExample(WxCommunityHelpExample example);

    int deleteByExample(WxCommunityHelpExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxCommunityHelpWithBLOBs record);

    int insertSelective(WxCommunityHelpWithBLOBs record);

    List<WxCommunityHelpWithBLOBs> selectByExampleWithBLOBs(WxCommunityHelpExample example);

    List<WxCommunityHelp> selectByExample(WxCommunityHelpExample example);

    WxCommunityHelpWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxCommunityHelpWithBLOBs record, @Param("example") WxCommunityHelpExample example);

    int updateByExampleWithBLOBs(@Param("record") WxCommunityHelpWithBLOBs record, @Param("example") WxCommunityHelpExample example);

    int updateByExample(@Param("record") WxCommunityHelp record, @Param("example") WxCommunityHelpExample example);

    int updateByPrimaryKeySelective(WxCommunityHelpWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WxCommunityHelpWithBLOBs record);

    int updateByPrimaryKey(WxCommunityHelp record);
}