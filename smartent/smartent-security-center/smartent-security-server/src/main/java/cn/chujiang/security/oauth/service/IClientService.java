package cn.chujiang.security.oauth.service;

import java.util.List;
import java.util.Map;

import cn.chujiang.commons.model.result.PageResult;
import cn.chujiang.commons.model.result.Result;
import cn.chujiang.security.oauth.dto.ClientDto;
import cn.chujiang.security.oauth.model.Client;


public interface IClientService {

	
	Client getById(Long id) ;
	 
    void saveClient(ClientDto clientDto);

    Result saveOrUpdate(ClientDto clientDto);

    void deleteClient(Long id);
    
    public PageResult<Client> listRoles(Map<String, Object> params);
    
    List<Client> findList(Map<String, Object> params) ;
    
    List<Client> listByUserId(Long userId) ;
    
}
