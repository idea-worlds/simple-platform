package dev.simpleframework.platform.system.app.converter;

import dev.simpleframework.platform.system.infra.data.SysUser;
import dev.simpleframework.platform.system.infra.data.SysUserAccount;
import dev.simpleframework.platform.system.model.SysUserAccountResponse;
import dev.simpleframework.platform.system.model.SysUserResponse;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
public final class SysUserConverter {

    public static SysUserResponse toResponse(SysUser data) {
        if (data == null) {
            return null;
        }
        SysUserResponse response = new SysUserResponse();
        response.setId(data.getId());
        response.setName(data.getName());
        response.setMobile(data.getMobile());
        response.setEmail(data.getEmail());
        response.setSex(data.getSex());
        response.setAvatar(data.getAvatar());
        response.setOrgs(data.getOrgs());
        response.setSortNo(data.getSortNo());
        response.setStatus(data.getStatus());
        response.setCreateTime(data.getCreateTime().getTime());
        data.getWorkspaces().forEach((code, w) -> response.addWorkspace(code, w.getTypes(), w.getLock()));
        return response;
    }

    public static SysUserAccountResponse toResponse(SysUserAccount data) {
        if (data == null) {
            return null;
        }
        SysUserAccountResponse response = new SysUserAccountResponse();
        response.setType(data.getType());
        response.setName(data.getName());
        response.setLastLoginIp(data.getLastLoginIp());
        response.setLastLoginAgent(data.getLastLoginAgent());
        response.setLastLoginTime(data.getLastLoginTime());
        return response;
    }

}
