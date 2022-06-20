package top.coolbreeze4j.dynamicdatasource.test.service;

/**
 * @author CoolBreeze
 * @date 2022/6/20 14:32.
 */
public interface TestService {
    void testDynamicDataSourceSelect();

    void testDynamicDataSourceSelect2();

    void testMasterDataSourceInsert();

    void testSlaveDataSourceInsert();

    void testMasterDataSourceTransactional();

    void testSlaveDataSourceTransactional();

}
