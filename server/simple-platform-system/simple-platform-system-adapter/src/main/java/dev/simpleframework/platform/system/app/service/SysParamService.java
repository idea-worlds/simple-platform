package dev.simpleframework.platform.system.app.service;

import dev.simpleframework.core.PageData;
import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.Page;
import dev.simpleframework.crud.core.QueryConfig;
import dev.simpleframework.crud.core.QuerySorters;
import dev.simpleframework.platform.system.api.SysParamApi;
import dev.simpleframework.platform.system.app.converter.SysConverter;
import dev.simpleframework.platform.system.app.executor.SysParamAddExecutor;
import dev.simpleframework.platform.system.app.executor.SysParamRemoveExecutor;
import dev.simpleframework.platform.system.app.executor.SysParamUpdateExecutor;
import dev.simpleframework.platform.system.infra.data.SysParam;
import dev.simpleframework.platform.system.model.SysParamModifyArgs;
import dev.simpleframework.platform.system.model.SysParamPageQueryArgs;
import dev.simpleframework.platform.system.model.SysParamResponse;
import dev.simpleframework.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Service
public class SysParamService implements SysParamApi {

    @Override
    public SysParamResponse findParam(String code) {
        if (Strings.isBlank(code)) {
            return null;
        }
        QueryConfig conditions = QueryConfig.of()
                .addCondition(SysParam::getCode, ConditionType.equal, code);
        SysParam param = new SysParam().findOneByConditions(conditions);
        return SysConverter.toResponse(param);
    }

    @Override
    public PageData<SysParamResponse> findParams(SysParamPageQueryArgs args, int pageNum, int pageSize) {

        QueryConfig config = QueryConfig.of()
                .addCondition(SysParam::getCode, ConditionType.like_all, args.getCode())
                .addCondition(SysParam::getName, ConditionType.like_all, args.getName())
                .addSorter(QuerySorters.asc(SysParam::getCode));
        Page<SysParamResponse> page = new SysParam()
                .pageByConditions(pageNum, pageSize, config)
                .convert(SysConverter::toResponse);
        return PageData.of(pageNum, pageSize, page.getTotal(), page.getItems());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addParam(SysParamModifyArgs args) {
        new SysParamAddExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateParam(SysParamModifyArgs args) {
        new SysParamUpdateExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeParams(List<String> codes) {
        new SysParamRemoveExecutor(codes).exec();
    }

}
