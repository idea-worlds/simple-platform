package dev.simpleframework.platform.system.api;

import dev.simpleframework.core.PageData;
import dev.simpleframework.platform.system.model.SysParamModifyArgs;
import dev.simpleframework.platform.system.model.SysParamPageQueryArgs;
import dev.simpleframework.platform.system.model.SysParamResponse;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public interface SysParamApi {

    /**
     * 查询系统参数信息
     *
     * @param code 编码
     */
    SysParamResponse findParam(String code);

    /**
     * 分页查询系统参数信息
     */
    PageData<SysParamResponse> findParams(SysParamPageQueryArgs args, int pageNum, int pageSize);

    /**
     * 新增系统参数
     */
    void addParam(SysParamModifyArgs args);

    /**
     * 修改系统参数
     */
    void updateParam(SysParamModifyArgs args);

    /**
     * 删除系统参数
     *
     * @param codes 编码
     */
    void removeParams(List<String> codes);

}
