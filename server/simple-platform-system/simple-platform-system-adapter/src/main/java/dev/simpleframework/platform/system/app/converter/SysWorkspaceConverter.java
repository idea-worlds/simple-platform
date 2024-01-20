package dev.simpleframework.platform.system.app.converter;

import dev.simpleframework.platform.commons.CommonUtils;
import dev.simpleframework.platform.system.infra.data.SysWorkspace;
import dev.simpleframework.platform.system.model.SysWorkspaceResponse;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public final class SysWorkspaceConverter {

    public static SysWorkspaceResponse toResponse(SysWorkspace data) {
        if (data == null) {
            return null;
        }
        SysWorkspaceResponse response = new SysWorkspaceResponse();
        response.setId(data.getId());
        response.setCode(data.getCode());
        response.setType(data.getType());
        response.setName(data.getName());
        response.setIcon(data.getIcon());
        response.setExt(CommonUtils.parseBool(data.getExtFlag()));
        response.setExtUrl(data.getExtUrl());
        response.setExtInfo(data.getExtInfo());
        response.setSortNo(data.getSortNo());
        response.setCreateTime(data.getCreateTime().getTime());
        return response;
    }


}
