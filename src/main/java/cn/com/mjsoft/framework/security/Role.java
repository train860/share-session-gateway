package cn.com.mjsoft.framework.security;

import java.io.Serializable;

/**
 * 提供角色身份的能力
 * 
 * @author mjsoft
 * 
 */
public interface Role extends Serializable
{
    public static final long serialVersionUID = 1L;
    /**
     * 取得角色字符信息
     * 
     * @return
     */
    public String getRoleName();

    /**
     * 取得角色ID
     * 
     * @return
     */
    public Long getRoleID();

}
