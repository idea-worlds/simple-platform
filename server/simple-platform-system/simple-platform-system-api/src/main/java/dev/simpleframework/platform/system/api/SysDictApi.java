package dev.simpleframework.platform.system.api;

import dev.simpleframework.core.PageData;
import dev.simpleframework.platform.system.model.SysDictModifyArgs;
import dev.simpleframework.platform.system.model.SysDictPageQueryArgs;
import dev.simpleframework.platform.system.model.SysDictResponse;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public interface SysDictApi {

    /**
     * 查询工作空间信息
     *
     * @param code 编码
     */
    SysDictResponse findDict(String code);

    /**
     * 分页查询工作空间信息
     */
    PageData<SysDictResponse> findDicts(SysDictPageQueryArgs args, int pageNum, int pageSize);

    /**
     * 新增工作空间
     */
    void addDict(SysDictModifyArgs args);

    /**
     * 修改工作空间
     */
    void updateDict(SysDictModifyArgs args);

    /**
     * 删除工作空间
     *
     * @param codes 编码
     */
    void removeDicts(List<String> codes);

}
