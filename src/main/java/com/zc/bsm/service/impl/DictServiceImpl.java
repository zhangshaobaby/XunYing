package com.zc.bsm.service.impl;

import java.io.File;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.bsm.dao.UserDao;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.DictService;
import com.zc.bsm.service.UserService;
@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<Dict, Integer> implements DictService {
	
	public  List<Dict> findByDictype(int dicttype){
	 List<Dict>  dictList=this.find("from Dict where dictType.id=?",dicttype);
	 return dictList;
	 }

	public Dict findByDictId(String dictid) {
		List<Dict>  dictList=this.find("from Dict where dictid=?",dictid);
		return dictList!=null&&dictList.size()>0?dictList.get(0):null;
	}
}