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
     * 查询数据字典信息
     *
     * @param code 编码
     */
    SysDictResponse findDict(String code);

    /**
     * 分页查询数据字典信息
     */
    PageData<SysDictResponse> findDicts(SysDictPageQueryArgs args, int pageNum, int pageSize);

    /**
     * 新增数据字典
     */
    void addDict(SysDictModifyArgs args);

    /**
     * 修改数据字典
     */
    void updateDict(SysDictModifyArgs args);

    /**
     * 删除数据字典
     *
     * @param codes 编码
     */
    void removeDicts(List<String> codes);

}
