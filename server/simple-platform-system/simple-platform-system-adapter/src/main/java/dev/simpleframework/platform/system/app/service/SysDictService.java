package dev.simpleframework.platform.system.app.service;

import dev.simpleframework.core.PageData;
import dev.simpleframework.crud.core.ConditionType;
import dev.simpleframework.crud.core.Page;
import dev.simpleframework.crud.core.QueryConfig;
import dev.simpleframework.crud.core.QuerySorters;
import dev.simpleframework.platform.system.api.SysDictApi;
import dev.simpleframework.platform.system.app.converter.SysConverter;
import dev.simpleframework.platform.system.app.executor.SysDictAddExecutor;
import dev.simpleframework.platform.system.app.executor.SysDictRemoveExecutor;
import dev.simpleframework.platform.system.app.executor.SysDictUpdateExecutor;
import dev.simpleframework.platform.system.infra.data.SysDict;
import dev.simpleframework.platform.system.model.SysDictModifyArgs;
import dev.simpleframework.platform.system.model.SysDictPageQueryArgs;
import dev.simpleframework.platform.system.model.SysDictResponse;
import dev.simpleframework.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Service
public class SysDictService implements SysDictApi {

    @Override
    public SysDictResponse findDict(String code) {
        if (Strings.isBlank(code)) {
            return null;
        }
        QueryConfig conditions = QueryConfig.of()
                .addCondition(SysDict::getCode, ConditionType.equal, code);
        SysDict dict = new SysDict().findOneByConditions(conditions);
        return SysConverter.toResponse(dict);
    }

    @Override
    public PageData<SysDictResponse> findDicts(SysDictPageQueryArgs args, int pageNum, int pageSize) {
        QueryConfig config = QueryConfig.of()
                .addCondition(SysDict::getCode, ConditionType.like_all, args.getCode())
                .addCondition(SysDict::getName, ConditionType.like_all, args.getName())
                .addSorter(QuerySorters.desc(SysDict::getSortNo));
        Page<SysDictResponse> page = new SysDict()
                .pageByConditions(pageNum, pageSize, config)
                .convert(SysConverter::toResponse);
        return PageData.of(pageNum, pageSize, page.getTotal(), page.getItems());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDict(SysDictModifyArgs args) {
        new SysDictAddExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(SysDictModifyArgs args) {
        new SysDictUpdateExecutor(args).exec();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDicts(List<String> codes) {
        new SysDictRemoveExecutor(codes).exec();
    }

}
