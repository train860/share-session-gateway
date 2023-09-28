package cn.com.mjsoft.framework.web.html.common;

import cn.com.mjsoft.framework.util.StringUtil;
import cn.com.mjsoft.framework.web.html.TagConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.Serializable;
import java.util.*;

public class TokenStrTag extends TagSupport
{
    private static final long serialVersionUID = 2781772874119897199L;

    private static final int TOKEN_LIMIT = 500;

    private String mode = "param";// param;html;val

    @SuppressWarnings("unchecked")
    public int doStartTag() throws JspException
    {

        HttpSession session = ( ( HttpServletRequest ) this.pageContext
            .getRequest() ).getSession();

        Set tokenSet = ( Set ) session.getAttribute( TagConstants.TOKEN );

        Integer pos = ( Integer ) session.getAttribute( TagConstants.TOKEN_POS );

        if( tokenSet == null )
        {
            tokenSet = new TreeSet( new TokenCompar() );

        }

        if( pos == null )
        {
            pos = Integer.valueOf( 1 );

        }
        else
        {
            pos = pos.intValue() + 1;
        }

       
        String uuid = pos
            + "*"
            + StringUtil.replaceString( session.getId().hashCode() + "", "-",
                "", false, false ) + StringUtil.getUUIDString();

        if( tokenSet.size() >= TOKEN_LIMIT )
        {
            List remove = new ArrayList();

            Iterator iter = tokenSet.iterator();

            int i = 0;
            while ( iter.hasNext() )
            {
                if( i > TOKEN_LIMIT / 2 )
                {
                    break;
                }

                remove.add( iter.next() );

                i++;
            }

            for ( int r = 0; r < remove.size(); r++ )
            {
                tokenSet.remove( remove.get( r ) );
            }

        }

        tokenSet.add( uuid );
        
        session.setAttribute( TagConstants.TOKEN, tokenSet );
        
        session.setAttribute( TagConstants.TOKEN_POS, pos );


        try
        {
            if( "html".equals( mode ) )
            {
                String html = "<input type=\"hidden\" id=\"_sys_jtop_token_key_\" name=\"_sys_jtop_token_key_\" value=\""
                    + uuid + "\"/>";

                this.pageContext.getOut().write( html );
            }
            else if( "param".equals( mode ) )
            {
                this.pageContext.getOut()
                    .write( "_sys_jtop_token_key_=" + uuid );
            }
            else if( "val".equals( mode ) )
            {
                this.pageContext.getOut().write( uuid );
            }
        }
        catch ( Exception e )
        {

        }

        return EVAL_BODY_INCLUDE;
    }

    public int doEndTag() throws JspException
    {
        return EVAL_PAGE;
    }

    public void setMode( String mode )
    {
        this.mode = mode;
    }

    private class TokenCompar implements Comparator, Serializable
    {

        private static final long serialVersionUID = -8012345275918303354L;

        public int compare( Object o1, Object o2 )
        {
            String id1 = ( String ) o1;

            String id2 = ( String ) o2;

            if( id1 == null || id2 == null )
            {
                return -1;
            }

            if( id1.indexOf( "*" ) == -1 || id2.indexOf( "*" ) == -1 )
            {
                return -1;
            }

            if( id1.hashCode() == id2.hashCode() )
            {
                return 0;
            }

            long sc1 = Long.valueOf( StringUtil.subString( id1, 0, id1
                .indexOf( "*" ) ) );

            long sc2 = Long.valueOf( StringUtil.subString( id2, 0, id2
                .indexOf( "*" ) ) );

            if( sc1 > sc2 )
            {
                return 1;
            }

            if( sc1 < sc2 )
            {
                return -1;
            }

            return -1;

        }

    }

}
