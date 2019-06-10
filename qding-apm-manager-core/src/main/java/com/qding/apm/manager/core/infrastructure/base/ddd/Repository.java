package com.qding.apm.manager.core.infrastructure.base.ddd;

import java.util.List;

/**
 * 仓库定义
 *
 * @author weijia
 */
public interface Repository<T> {
    /**
     * 查找全部领域对象
     *
     * @return 返回全部领域对象
     */
    List<T> findAll();

    /**
     * 保存领域对象
     *
     * @param domainObject 领域对象
     */
    void save(final T domainObject);

    /**
     * 通过领域对象id删除领域对象
     *
     * @param id 领域对象id
     */
    void delete(final long id);

    /**
     * 通过领域对象id查找领域对象
     *
     * @param id 领域对象id
     * @return 返回通过领域对象id查找到的领域对象
     */
    T findById(final long id);
}
