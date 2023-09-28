package cn.com.mjsoft.framework.security;

/**
 * 一般意义的角色实现
 * 
 * @author mjsoft
 */
public class GenericRole implements Role
{
    private static final long serialVersionUID = -4871941624028988554L;

    // 角色名
    private String roleName;

    // 角色ID
    private Long roleId;

    public GenericRole(String roleName, Long roleId )
    {
        super();
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public GenericRole(String roleName )
    {
        this.roleName = roleName;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public Long getRoleID()
    {
        return this.roleId;
    }

    public boolean equals( Object obj )
    {
        if( obj instanceof String )
        {
            return roleName.equals( obj );
        }

        if( obj instanceof Role )
        {
            return roleName.equals( ( ( Role ) obj ).getRoleName() );
        }

        return false;
    }

    public int hashCode()
    {
        return roleName.hashCode();
    }

    public String toString()
    {
        return roleName+":"+roleId;
    }

}
