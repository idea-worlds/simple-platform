package dev.simpleframework.platform.commons;

import dev.simpleframework.crud.BaseModel;
import dev.simpleframework.crud.annotation.Column;
import dev.simpleframework.crud.annotation.DataOperateDate;
import dev.simpleframework.crud.annotation.DataOperateUser;
import dev.simpleframework.crud.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author loyayz (loyayz@foxmail.com)
 */
@Setter
@Getter
public abstract class BaseDataModel<T> implements BaseModel<T> {

    @Id(type = Id.Type.SNOWFLAKE)
    @Column(updatable = false)
    private Long id;
    /**
     * 创建时间
     */
    @DataOperateDate
    @Column(updatable = false)
    private Date createTime;
    /**
     * 创建人
     */
    @DataOperateUser
    @Column(updatable = false)
    private Long createUser;
    /**
     * 修改时间
     */
    @DataOperateDate
    private Date updateTime;
    /**
     * 修改人
     */
    @DataOperateUser
    private Long updateUser;

    public void save() {
        if (this.id == null) {
            this.insert();
        } else {
            this.updateById();
        }
    }

}
