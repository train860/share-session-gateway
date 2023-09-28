package cn.com.mjsoft.framework.security;


import java.util.Arrays;

/**
 * 抽象用户权限对象，此类拥有用户的角色信息和认证信息。
 * @author mjsoft
 * 
 */
public abstract class AbstractAuth implements Auth
{
    private static final long serialVersionUID =2061732011429184202L;
    protected Role[] authorities;

    protected Object authInfo;
    
    

    public AbstractAuth(Role[] authorities )
    {
        // 必须使用拷贝，保证修改安全
        Role[] tmp = null;
        if( authorities != null )
        {
            // check,不允许出现非法的角色信息
            for ( int i = 0; i < authorities.length; i++ )
            {
                if( isStringNull( authorities[i].getRoleName() ) )
                {
                    throw new IllegalArgumentException( "角色信息包含空字符" );
                }
            }
            tmp = new Role[authorities.length];
            System.arraycopy( authorities, 0, tmp, 0, authorities.length );
        }
        this.authorities = tmp;
    }

    public Object getDetail()
    {
        return authInfo;
    }

    public void setDetail( Object detail )
    {
        this.authInfo = detail;
    }

    public Role[] getUserRole()
    {
        return authorities;
    }

    public Role[] getUserRoleCopy()
    {
        if( authorities == null )
        {
            return null;
        }

        Role[] copy = new Role[authorities.length];
        System.arraycopy( authorities, 0, copy, 0, authorities.length );

        return copy;
    }

    private static int hashCode( Object[] array )
    {
        final int PRIME = 31;
        if( array == null )
            return 0;
        int result = 1;
        for ( int index = 0; index < array.length; index++ )
        {
            result = PRIME * result
                + ( array[index] == null ? 0 : array[index].hashCode() );
        }
        return result;
    }

    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
            + ( ( authInfo == null ) ? 0 : authInfo.hashCode() );
        result = PRIME * result + AbstractAuth.hashCode( authorities );
        return result;
    }

    public boolean equals( Object obj )
    {
        if( this == obj )
            return true;
        if( obj == null )
            return false;
        if( getClass() != obj.getClass() )
            return false;
        final AbstractAuth other = ( AbstractAuth ) obj;
        if( authInfo == null )
        {
            if( other.authInfo != null )
                return false;
        }
        else if( !authInfo.equals( other.authInfo ) )
            return false;
        if( !Arrays.equals( authorities, other.authorities ) )
            return false;
        return true;
    }
    public boolean isStringNull( String input )
    {
        if( null == input || input.trim().equals( "" ) )
        {
            return true;
        }
        return false;
    }
}
