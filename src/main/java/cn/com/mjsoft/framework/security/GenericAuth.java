package cn.com.mjsoft.framework.security;


/**
 * 一般的使用用户名和密码进行的认证的认证信息对象
 * 
 * @author mjsoft
 * 
 */
public class GenericAuth extends AbstractAuth
{
    private static final long serialVersionUID = 340390056471938808L;

    private boolean isAuthed = false;

    private Object orgIdentity;
    private Object orgCode;
    private Object identity;
    private Object name;
    private Object password;

    //private RoleSqlHelper sqlHelper;

    public GenericAuth()
    {
        super( null );
    }

    public GenericAuth(Object name, Object password )
    {
        super( null );
        this.name = name;
        this.password = password;
        /**
         * 这样的构造对象表示这只是一次认证请求，所以要设定为没有通过
         */
        this.isAuthed = false;
    }

    public GenericAuth(Object name, Object password, Object detail,
                       Object identity, Object orgIdentity, Object orgCode )
    {
        super( null );
        this.name = name;
        this.password = password;
        this.identity = identity;
        this.orgIdentity = orgIdentity;
        this.orgCode = orgCode;
        /**
         * 这样的构造对象表示这只是一次认证请求，所以要设定为没有通过
         */
        this.isAuthed = false;
        this.authInfo = detail;
    }

    public GenericAuth(Role[] authorities, Object name, Object password,
                       Object identity, Object orgIdentity, Object orgCode )
    {
        super( authorities );
        this.name = name;
        this.password = password;

        this.identity = identity;
        this.orgIdentity = orgIdentity;
        this.orgCode = orgCode;

        /**
         * 有了用户的身份表示已经通过认证信息了
         */
        this.isAuthed = true;

        //sqlHelper = new RoleSqlHelper( password+this.identity.toString()+orgCode, this.authorities );

        //sqlHelper.registerAuthAllRole( "", "roleId" );
    }

    public Object getApellation()
    {
        return name;
    }

    public Object getCredence()
    {
        return password;
    }

    public boolean isAuthenticated()
    {
        return isAuthed;
    }

    public void setAuthenticated( boolean isAuthed )
    {
        this.isAuthed = isAuthed;
    }

    public void setAuthorities( Role[] authorities )
    {
        // 必须使用拷贝，保证修改安全
        Role[] tmp = null;
        if( authorities != null )
        {
            // check,不允许出现非法的角色信息
            for ( int i = 0; i < authorities.length; i++ )
            {
                if(isStringNull( authorities[i].getRoleName() ) )
                {
                    throw new IllegalArgumentException( "角色信息包含空字符" );
                }
            }
            tmp = new Role[authorities.length];
            System.arraycopy( authorities, 0, tmp, 0, authorities.length );
        }

        this.authorities = tmp;
        //sqlHelper.setIdentity( this.identity );

        //sqlHelper.setRoleArray( this.authorities );
        //sqlHelper.romoveAuthAllRole();

        //sqlHelper.registerAuthAllRole( "", "roleId" );
    }

    /*
    public RoleSqlHelper getRoleSqlHelper()
    {
        return sqlHelper;
    }*/

    public Object getIdentity()
    {
        return identity;
    }

    public void setIdentity( Object identity )
    {
        this.identity = identity;
    }

    public Object getOrgIdentity()
    {
        return orgIdentity;
    }

    public Object getOrgCode()
    {
        return orgCode;
    }

    public void setOrgIdentity( Object orgIdentity )
    {
        this.orgIdentity = orgIdentity;
    }

    public int hashCode()
    {
        final int PRIME = 31;
        int result = super.hashCode();
        result = PRIME * result
            + ( ( identity == null ) ? 0 : identity.hashCode() );
        result = PRIME * result + ( isAuthed ? 1231 : 1237 );
        result = PRIME * result + ( ( name == null ) ? 0 : name.hashCode() );
        result = PRIME * result
            + ( ( orgIdentity == null ) ? 0 : orgIdentity.hashCode() );
        result = PRIME * result
            + ( ( password == null ) ? 0 : password.hashCode() );
        return result;
    }

    public boolean equals( Object obj )
    {
        if( this == obj )
            return true;
        if( !super.equals( obj ) )
            return false;
        if( getClass() != obj.getClass() )
            return false;
        final GenericAuth other = ( GenericAuth ) obj;
        if( identity == null )
        {
            if( other.identity != null )
                return false;
        }
        else if( !identity.equals( other.identity ) )
            return false;
        if( isAuthed != other.isAuthed )
            return false;
        if( name == null )
        {
            if( other.name != null )
                return false;
        }
        else if( !name.equals( other.name ) )
            return false;
        if( orgIdentity == null )
        {
            if( other.orgIdentity != null )
                return false;
        }
        else if( !orgIdentity.equals( other.orgIdentity ) )
            return false;
        if( password == null )
        {
            if( other.password != null )
                return false;
        }
        else if( !password.equals( other.password ) )
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
