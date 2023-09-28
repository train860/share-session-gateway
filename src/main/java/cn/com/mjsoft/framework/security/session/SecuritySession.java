package cn.com.mjsoft.framework.security.session;

import cn.com.mjsoft.framework.security.Auth;

import java.io.Serializable;
import java.util.Map;

/**
 * 权限Session对象，可获取Auth授权信息，用户当前登录站点信息，授权类型(登录者是管理员还是会员)
 * 
 * @author mjsoft
 * 
 */
public interface SecuritySession extends Serializable
{
    public static final long serialVersionUID = 1L;
    public static final String SEC_APP_FLAG = "___JTOPCMS__SYSTEM__SECURITY__INFO___";

    /**
     * 返回session持有的认证信息对象
     * 
     * @return
     */
    public Auth getAuth();

    /**
     * 设定session的认证信息对象
     * 
     * @param auth
     */
    public void setAuth( Auth auth );

    /**
     * 设定会员类型对象
     * 
     * @param auth
     */
    public void setMember( Object member );

    /**
     * 返回session持有的会员类型对象
     * 
     * @return
     */
    public Object getMember();

    /**
     * 返回session持有的当前登陆站点信息
     * 
     * @return
     */
    public Object getCurrentLoginSiteInfo();

    /**
     * 设置session持有的当登陆站点信息
     * 
     * @param auth
     */
    public void setCurrentLoginSiteInfo( Long siteId );

    /**
     * 是否为管理者session
     * 
     * @return
     */
    public boolean isManager();

    /**
     * 管理者session标志
     * 
     * @return
     */
    public void setManagerFlag();

    /**
     * 获取sessionContext对象工作map
     * 
     * @return
     */
    public Map getWorkContextMap();

    /**
     * 获取唯一ID
     * 
     * @return
     */
    public String getUUID();

    /**
     * 设置唯一ID
     * 
     * @return
     */
    public void setUUID( String uuid );

}
