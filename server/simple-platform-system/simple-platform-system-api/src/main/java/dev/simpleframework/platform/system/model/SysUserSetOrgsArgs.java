package dev.simpleframework.platform.system.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserSetOrgsArgs {

    /**
     * 组织
     */
    private List<String> orgs;

    public List<String> getOrgs() {
        return orgs == null ? Collections.emptyList() : orgs;
    }

}
