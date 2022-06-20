package top.coolbreeze4j.dynamicdatasource.test.service.impl;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.coolbreeze4j.dynamicdatasource.annotation.DataSource;
import top.coolbreeze4j.dynamicdatasource.enums.DataSourceType;
import top.coolbreeze4j.dynamicdatasource.test.domain.Test;
import top.coolbreeze4j.dynamicdatasource.test.mapper.TestMapper;
import top.coolbreeze4j.dynamicdatasource.test.service.TestService;
import top.coolbreeze4j.dynamicdatasource.utils.SpringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CoolBreeze
 * @date 2022/6/20 14:32.
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;

    /**
     * 主库 从库查询测试
     * tip: 在主库test和 从库 test2中都建了一张test表
     */
    @Override
    public void testDynamicDataSourceSelect() {
        log.info("开始查询主库数据");
        List<Test> list = testMapper.selectByMasterDataSource();
        log.info("查询到主库数据共【{}】条, 内容如下:\n{}", list.size(), list);

        log.info("开始查询从库数据");
        List<Test> list2 = testMapper.selectBySlaveDataSource();
        log.info("查询到从库数据共【{}】条, 内容如下:\n{}", list2.size(), list2);
    }

    /**
     * service 方法调用 《多个》 本类声明了@DataSource注解的方法
     */
    @Override
    public void testDynamicDataSourceSelect2() {
        SpringUtils.getAopProxy(this).testMasterDataSourceSelect();
        SpringUtils.getAopProxy(this).testSlaveDataSourceSelect();
    }

    @DataSource(DataSourceType.MASTER)
    public void testMasterDataSourceSelect() {
        log.info("开始查询主库数据");
        List<Test> list = testMapper.selectByMasterDataSource2();
        log.info("查询到主库数据共【{}】条, 内容如下:\n{}", list.size(), list);
    }

    @DataSource(DataSourceType.SLAVE)
    public void testSlaveDataSourceSelect() {
        log.info("开始查询从库数据");
        List<Test> list2 = testMapper.selectBySlaveDataSource2();
        log.info("查询到从库数据共【{}】条, 内容如下:\n{}", list2.size(), list2);
    }


    /**
     * 主库 数据插入测试
     */
    @Override
    public void testMasterDataSourceInsert() {
        Test test = new Test();
        test.setId(IdUtil.fastSimpleUUID());
        test.setName("测试插入");

        log.info("开始插入主库数据");
        testMapper.insert2MasterDataSource(test);
        log.info("主库成功插入数据");
    }

    /**
     * 从库 数据插入测试
     */
    @Override
    public void testSlaveDataSourceInsert() {
        Test test = new Test();
        test.setId(IdUtil.fastSimpleUUID());
        test.setName("测试插入");

        log.info("开始插入从库数据");
        testMapper.insert2SlaveDataSource(test);
        log.info("从库成功插入数据");
    }

    /**
     * 主库 数据插入 事务测试
     */
    @Transactional
    @Override
    public void testMasterDataSourceTransactional() {
        Test test = new Test();
        test.setId(IdUtil.fastSimpleUUID());
        test.setName("测试插入");

        log.info("开始插入主库数据");
        testMapper.insert2MasterDataSource(test);
        int num = 2/0;
        log.info("主库成功插入数据");
    }

    /**
     * 从库 数据插入 事务测试
     */
    @Transactional
    @Override
    public void testSlaveDataSourceTransactional() {
        Test test = new Test();
        test.setId(IdUtil.fastSimpleUUID());
        test.setName("测试插入");

        log.info("开始插入从库数据");
        testMapper.insert2SlaveDataSource(test);
        int num = 2/0;
        log.info("从库成功插入数据");
    }
}
