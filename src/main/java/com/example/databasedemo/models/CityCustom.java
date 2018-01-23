package com.example.databasedemo.models;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 复杂sql  Dao类
 */
@Component
public class CityCustom {
	@Autowired
	@PersistenceContext(unitName="secondaryPersistenceUnit")
	private EntityManager em;
	
	public void getSelectSql(){
		String sql="select * from city2";
	    Query query = em.createNativeQuery(sql);
	    // Query 接口是 spring-data-jpa 的接口，而 SQLQuery 接口是 hibenate 的接口，这里的做法就是先转成 hibenate 的查询接口对象，然后设置结果转换器
	    query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	 System.out.println(query.getResultList());
		
		
       
	}

}
