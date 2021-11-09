package com.lance.modules.system.service;

import com.lance.modules.system.domain.Dept;
import com.lance.modules.system.service.dto.DeptDto;
import com.lance.modules.system.service.dto.DeptQueryCriteria;

import java.util.List;
import java.util.Set;

public interface DeptService {

    /**
     * 根据PID查询
     * @param pid /
     * @return /
     */
    List<Dept> findByPid(long pid);

    /**
     * 获取
     * @param deptList
     * @return
     */
    List<Long> getDeptChildren(List<Dept> deptList);
    /**
     * 根据角色ID查询
     * @param id /
     * @return /
     */
    Set<Dept> findByRoleId(Long id);

    /**
     * 查询所有数据
     * @param criteria 条件
     * @param isQuery /
     * @throws Exception /
     * @return /
     */
    List<DeptDto> queryAll(DeptQueryCriteria criteria, Boolean isQuery) throws Exception;

}
