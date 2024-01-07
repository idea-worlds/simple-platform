package dev.simpleframework.platform.system.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Data
public class SysUserRemoveWorkspaceArgs {

    /**
     * 工作空间编码
     */
    @NotBlank(message = "{blank.workspace}")
    private List<String> workspaces;

}
