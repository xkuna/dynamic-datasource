package top.coolbreeze4j.dynamicdatasource.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.coolbreeze4j.dynamicdatasource.test.service.TestService;
import top.coolbreeze4j.dynamicdatasource.utils.SpringUtils;

/**
 * @author CoolBreeze
 * @date 2022/6/20 14:56.
 */
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    TestService testService;

    /**
     * 测试动态数据源 查询
     */
    @Test
    public void testDynamicDataSourceSelect(){
        testService.testDynamicDataSourceSelect();
    }

    /**
     * 测试service 方法调用 《多个》 本类声明了@DataSource注解的方法时的查询
     */
    @Test
    public void testDynamicDataSourceSelect2(){
        testService.testDynamicDataSourceSelect2();
    }

    /**
     * 测试主库数据源 插入
     */
    @Test
    public void testMasterDataSourceInsert(){
        testService.testMasterDataSourceInsert();
    }

    /**
     * 测试从库数据源 插入
     */
    @Test
    public void testSlaveDataSourceInsert(){
        testService.testSlaveDataSourceInsert();
    }

    /**
     * 测试主库数据源 事务
     */
    @Test
    public void testMasterDataSourceTransactional(){
        testService.testMasterDataSourceTransactional();
    }

    /**
     * 测试从库数据源 事务
     */
    @Test
    public void testSlaveDataSourceTransactional(){
        testService.testSlaveDataSourceTransactional();
    }




}
