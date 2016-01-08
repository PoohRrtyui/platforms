package com.xunyun.infanteduplatform.persistent;

import com.xunyun.infanteduplatform.domain.BureauInfo;
import com.xunyun.infanteduplatform.domain.SysCodeMaster;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeMasterMapper {
	// 查询数据字典数据
	List<SysCodeMaster> queryCodeMasterInfo(SysCodeMaster sysCodeMaster);

	// 添加数据字典数据
	Integer saveCodeMasterInfo(SysCodeMaster sysCodeMaster);

	// 添加功能查重验证
	SysCodeMaster querySaveRepeat(SysCodeMaster sysCodeMaster);

	// 修改数据字典数据
	Integer updateCodeMasterInfo(SysCodeMaster sysCodeMaster);

	// 数据字典逻辑删除
	Integer deleteCodeMaster(SysCodeMaster sysCodeMaster);

	// 查找机构身份数据
	List<SysCodeMaster> findOrg(String string);

	// 查找专题信息
	List<SysCodeMaster> findMessage(String largeCategoryCd);

	//查重
    Integer querycodermasterrepeat(SysCodeMaster sysCodeMaster);
	/**
	 * 字典数据条件查询
	 * 
	 * @param sysCodeMaster
	 *            字典管理实体类
	 * @return List<SysCodeMaster>
	 */
	List<SysCodeMaster> queryCodeMasterList(SysCodeMaster sysCodeMaster);
	
	SysCodeMaster queryUpdateLocationCode(String locationCode);

}
