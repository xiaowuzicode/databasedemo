package com.example.databasedemo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.databasedemo.modelp.CountryRepository;
import com.example.databasedemo.models.CityCustom;
import com.example.databasedemo.models.CityRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabasedemoApplicationTests {

	@Autowired
	private CountryRepository CountryRepository; //默认jpa主数据源
	@Autowired
	private CityRepository CityRepository; //默认jpa其他数据源
	
	@Autowired
	private EntityManager entityManagerP; //EntityManager 实例
	
	@Autowired
	private CityCustom entityManagerS; // 其他 EntityManager 实例
	
	@Before
	public void before() {
		System.out.println("开始-----");
	}

	@Test
	public void test() throws Exception {
		
	/*	
	 * //默认jpa方式保存数据
	 * 
		CountryRepository.save(new Country(200l, "cg", "123"));
		CountryRepository.save(new Country(201l, "cg", "12323"));
		System.out.println(CountryRepository.findAll().size());
		Assert.assertEquals(2, userRepository.findAll().size());

		
		CityRepository.save(new City(1000L, "hhhh", "ssss"));
		CityRepository.save(new City(1000L, "hhh222h", "sss222s"));
		System.out.println(CityRepository.findAll().size());
		Assert.assertEquals(3, CityRepository.findAll().size());*/
		
		
	
	//主数据源 自定义sql查询
		System.out.println("主数据源开始");
		getSelectSql();
		System.out.println("主数据源结束");
	//其他数据源 自定义sql查询
		System.out.println("其他数据源开始");
		 entityManagerS.getSelectSql();
		 System.out.println("其他数据源结束");
	}
	
	@After
	public void end(){
		System.out.println("结束----");
	}

	public void getSelectSql(){
		String sql="select * from city";
	    Query query = entityManagerP.createNativeQuery(sql);
	    // Query 接口是 spring-data-jpa 的接口，而 SQLQuery 接口是 hibenate 的接口，这里的做法就是先转成 hibenate 的查询接口对象，然后设置结果转换器
	    query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	 System.out.println(query.getResultList());
	}
}
