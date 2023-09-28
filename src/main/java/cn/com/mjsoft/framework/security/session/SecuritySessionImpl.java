package cn.com.mjsoft.framework.security.session;

import cn.com.mjsoft.framework.security.Auth;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限Session对象实现类。
 * 
 * @author mjsoft
 * 
 */
public class SecuritySessionImpl implements SecuritySession
{
    private static final long serialVersionUID = -6083198772431993550L;

    private Auth auth = null;

    private Object member;

    private Long siteId;

    private boolean managerFlag = false;

    private Map workContextMap = new HashMap();

    private String uuid;

    public Auth getAuth()
    {
        return auth;
    }

    public void setAuth( Auth auth )
    {
        this.auth = auth;
    }

    public Object getMember()
    {
        return this.member;
    }

    public void setMember( Object member )
    {
        this.member = member;

    }

    public Object getCurrentLoginSiteInfo()
    {
        /*
        Object siteObj = InterceptFilter.siteIdInfoCache.get( this.siteId );

        if( siteObj == null || this.siteId.longValue() < 0 )
        {
            return ObjectUtility
                .buildObject( SecuritySessionDisposeFiletr.getSiteNodeImlObjClass() );
        }

        return siteObj;*/
        return null;
    }

    public void setCurrentLoginSiteInfo( Long siteId )
    {
        this.siteId = siteId;
    }

    public Map getWorkContextMap()
    {
        return workContextMap;
    }

    public boolean isManager()
    {
        return managerFlag;
    }

    public void setManagerFlag()
    {
        managerFlag = true;
    }

    public String getUUID()
    {
        return this.uuid;
    }

    public void setUUID( String uuid )
    {
        this.uuid = uuid;
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ( ( auth == null ) ? 0 : auth.hashCode() );
        result = PRIME * result + ( managerFlag ? 1231 : 1237 );
        result = PRIME * result + ( ( member == null ) ? 0 : member.hashCode() );
        result = PRIME * result + ( ( siteId == null ) ? 0 : siteId.hashCode() );
        result = PRIME * result + ( ( uuid == null ) ? 0 : uuid.hashCode() );
        result = PRIME * result + ( ( workContextMap == null ) ? 0 : workContextMap.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if( this == obj )
            return true;
        if( obj == null )
            return false;
        if( getClass() != obj.getClass() )
            return false;
        final SecuritySessionImpl other = ( SecuritySessionImpl ) obj;
        if( auth == null )
        {
            if( other.auth != null )
                return false;
        }
        else if( !auth.equals( other.auth ) )
            return false;
        if( managerFlag != other.managerFlag )
            return false;
        if( member == null )
        {
            if( other.member != null )
                return false;
        }
        else if( !member.equals( other.member ) )
            return false;
        if( siteId == null )
        {
            if( other.siteId != null )
                return false;
        }
        else if( !siteId.equals( other.siteId ) )
            return false;
        if( uuid == null )
        {
            if( other.uuid != null )
                return false;
        }
        else if( !uuid.equals( other.uuid ) )
            return false;
        if( workContextMap == null )
        {
            if( other.workContextMap != null )
                return false;
        }
        else if( !workContextMap.equals( other.workContextMap ) )
            return false;
        return true;
    }

}
