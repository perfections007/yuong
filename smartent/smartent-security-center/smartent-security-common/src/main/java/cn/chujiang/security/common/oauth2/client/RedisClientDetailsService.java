package cn.chujiang.security.common.oauth2.client;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


/**
 * 将oauth_client_details表数据缓存到redis，这里做个缓存优化
* 注意对oauth_client_details清楚redis db部分数据的清空
 * @author zhejun
 *
 */
 @Slf4j
public class RedisClientDetailsService extends JdbcClientDetailsService {
    /**
     * 缓存client的redis key，这里是hash结构存储
     */
    private static final String CACHE_CLIENT_KEY = "oauth_client_details";
    

    private RedisTemplate<String,Object> redisTemplate ;
    
    public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }



    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        ClientDetails clientDetails = null;

        // 先从redis获取
        String value = (String) redisTemplate.boundHashOps(CACHE_CLIENT_KEY).get(clientId);
        if (StringUtils.isEmpty(value)) {
            clientDetails = cacheAndGetClient(clientId);
        } else {
            clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
        }

        return clientDetails;
    }

    /**
     * 缓存client并返回client
     *
     * @param clientId
     * @return
     */
    private ClientDetails cacheAndGetClient(String clientId) {
        // 从数据库读取
        ClientDetails clientDetails = null ;
		try {
			clientDetails = super.loadClientByClientId(clientId);
			if (clientDetails != null) {            
				// 写入redis缓存
				redisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(clientId, JSONObject.toJSONString(clientDetails));
			    log.info("缓存clientId:{},{}", clientId, clientDetails);
			}
		}catch (NoSuchClientException e){
			log.info("clientId:{},{}", clientId, clientId );
		}catch (InvalidClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return clientDetails;
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
        cacheAndGetClient(clientDetails.getClientId());
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
        cacheAndGetClient(clientId);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
        removeRedisCache(clientId);
    }

    /**
     * 删除redis缓存
     *
     * @param clientId
     */
    private void removeRedisCache(String clientId) {
    	redisTemplate.boundHashOps(CACHE_CLIENT_KEY).delete(clientId);
    }

    /**
     * 将oauth_client_details全表刷入redis
     */
    public void loadAllClientToCache() {
        if (redisTemplate.hasKey(CACHE_CLIENT_KEY)) {
            return;
        }
        log.info("将oauth_client_details全表刷入redis");

        List<ClientDetails> list = super.listClientDetails();
        if (CollectionUtils.isEmpty(list)) {
        	log.error("oauth_client_details表数据为空，请检查");
            return;
        }

        list.parallelStream().forEach(client -> {
        	redisTemplate.boundHashOps(CACHE_CLIENT_KEY).put(client.getClientId(), JSONObject.toJSONString(client));
        });
    }
}
