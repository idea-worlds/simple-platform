package dev.simpleframework.platform.system.app.converter;

import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.system.infra.data.SysDict;
import dev.simpleframework.platform.system.infra.data.SysParam;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.platform.system.model.SysDictResponse;
import dev.simpleframework.platform.system.model.SysParamResponse;
import dev.simpleframework.platform.system.model.SysWorkspaceResponse;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public final class SysConverter {

    public static SysWorkspaceResponse toResponse(SysWorkspace data) {
        if (data == null) {
            return null;
        }
        SysWorkspaceResponse response = new SysWorkspaceResponse();
        response.setCode(data.getCode());
        response.setType(data.getType());
        response.setName(data.getName());
        response.setIcon(data.getIcon());
        response.setDescription(data.getDescription());
        response.setExt(CommonUtils.parseBool(data.getExtFlag()));
        response.setExtUrl(data.getExtUrl());
        response.setExtInfo(data.getExtInfo());
        response.setSortNo(data.getSortNo());
        response.setCreateTime(data.getCreateTime().getTime());
        return response;
    }

    public static SysDictResponse toResponse(SysDict data) {
        if (data == null) {
            return null;
        }
        SysDictResponse response = new SysDictResponse();
        response.setCode(data.getCode());
        response.setName(data.getName());
        response.setDescription(data.getDescription());
        data.getItems().forEach((code, i) -> response.addItem(code, i.getName(), i.getVal()));
        return response;
    }

    public static SysParamResponse toResponse(SysParam data) {
        if (data == null) {
            return null;
        }
        SysParamResponse response = new SysParamResponse();
        response.setCode(data.getCode());
        response.setName(data.getName());
        response.setVal(data.getVal());
        response.setDescription(data.getDescription());
        return response;
    }

}
