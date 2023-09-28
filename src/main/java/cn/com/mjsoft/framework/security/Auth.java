package cn.com.mjsoft.framework.security;

import java.io.Serializable;

/**
 * 用户的认证相关信息，包含其从发起认证到认证结束后所具有的身份信息等一切所需要资源<br>
 * 此接口重点描叙权限管理过程中的状态变化和细节。
 * 
 * @author mjsoft
 * 
 */
public interface Auth extends Serializable
{
    //serialVersionUID
    public static final long serialVersionUID = 1L;
    /**
     * 取得用户的所有身份信息拷贝
     * 
     * @return 一组证明用户身份的信息
     */
    public Role[] getUserRoleCopy();

    /**
     * 取得用户的所有身份信息
     * 
     * @return 一组证明用户身份的信息
     */
    public Role[] getUserRole();

    // /**
    // * 取得身份信息本身的唯一标识
    // *
    // * @return
    // */
    // public Object getCredenceId();

    /**
     * 取得认证者名称
     * 
     * @return
     */
    public Object getApellation();

    /**
     * 取得认证者凭证
     * 
     * @return
     */
    public Object getCredence();

    /**
     * 取得认证者细节
     * 
     * @return
     */
    public Object getDetail();

    /**
     * 是否通过认证
     * 
     * @return
     */
    public boolean isAuthenticated();

    /**
     * 设定是否通过认证
     * 
     * @param isAuthed
     * @throws IllegalArgumentException
     */
    public void setAuthenticated( boolean isAuthed );

    /**
     * 取得Auth的对应的sqlhelper,注意,当用户没有通过系统认证时,返回Null.
     * 
     * @return
     */
    //public RoleSqlHelper getRoleSqlHelper();

    /**
     * 取得此用户的唯一identity
     * 
     * @return
     */
    public Object getIdentity();

    /**
     * 设定新的角色组
     * 
     * @param authorities
     */
    public void setAuthorities( Role[] authorities );

    /**
     * 认证对象所属组织唯一identity
     * 
     * @return
     */
    public Object getOrgIdentity();

    /**
     * 认证对象所属组织唯一code
     * 
     * @return
     */
    public Object getOrgCode();
}
