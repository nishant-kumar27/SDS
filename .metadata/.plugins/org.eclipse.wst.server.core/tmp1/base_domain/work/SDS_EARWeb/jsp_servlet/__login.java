package jsp_servlet;

import java.lang.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class __login extends  weblogic.servlet.jsp.JspBase  implements weblogic.servlet.jsp.StaleIndicator {

    private static void _releaseTags(javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag t) {
        while (t != null) {
            weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, t);
            if(t instanceof javax.servlet.jsp.tagext.Tag) {
                javax.servlet.jsp.tagext.Tag tmp = (javax.servlet.jsp.tagext.Tag)t;
                t = ((javax.servlet.jsp.tagext.Tag) t).getParent();
                try {
                    tmp.release();
                } catch(java.lang.Exception ignore) {}
            }
            else {
                t = ((javax.servlet.jsp.tagext.SimpleTag)t).getParent();
            }
        }
    }

    public boolean _isStale(){
        boolean _stale = _staticIsStale((weblogic.servlet.jsp.StaleChecker) getServletConfig().getServletContext());
        return _stale;
    }

    public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
        if (sci.isResourceStale("/login.jsp", 1515532012000L ,"12.2.1.0.0","Asia/Calcutta")) return true;
        return false;
    }
    private weblogic.servlet.jsp.ExpressionInterceptor _jsp_expressionInterceptor = weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.getNonOperExpressionInterceptor();

    private static boolean _WL_ENCODED_BYTES_OK = true;
    private static final java.lang.String _WL_ORIGINAL_ENCODING = "UTF-8".intern();

    private static byte[] _getBytes(java.lang.String block){
        try {
            return block.getBytes(_WL_ORIGINAL_ENCODING);
        } catch (java.io.UnsupportedEncodingException u){
            _WL_ENCODED_BYTES_OK = false;
        }
        return null;
    }

    private static java.lang.String  _wl_block0 ="\n";
    private final static byte[]  _wl_block0Bytes = _getBytes( _wl_block0 );

    private static java.lang.String  _wl_block1 ="\n\n<!DOCTYPE html>\n<html>\n<head>\n<meta charset=\"utf-8\">\n<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n<title>SDS | Log in</title>\n\n<meta content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\"\n\tname=\"viewport\">\n<!-- Bootstrap 3.3.6 -->\n<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n<link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap-theme.min.css\">\n<!-- Font Awesome -->\n<link rel=\"stylesheet\" href=\"assets/font-awesome-4.6.3/css/font-awesome.min.css\"\n\ttype=\"text/css\" />\n<!-- Ionicons -->\n<!-- Theme style -->\n<link rel=\"stylesheet\" href=\"assets/dist/css/AdminLTE.min.css\">\n<link rel=\"stylesheet\" href=\"assets/css/Customizations.css\">\n\n";
    private final static byte[]  _wl_block1Bytes = _getBytes( _wl_block1 );

    private static java.lang.String  _wl_block2 ="\n<script type=\"text/javascript\" src=\"jquery/jquery-1.12.4.min.js\"></script>\n<script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\n\n<!--[if lt IE 9]>\n    <script type=\"text/javascript\" src=\"assets/ie8support/html5shiv.min.js\"></script>\n\t<script type=\"text/javascript\" src=\"assets/ie8support/html5shiv-printshiv.min.js\"></script>\n\t<script type=\"text/javascript\" src=\"assets/ie8support/respond.min.js\"></script>\n<![endif]-->\n\n<style>\n.login-page {\n\tbackground: #e6e6ea;\n}\n\n</style>\n</head>\n<body class=\"hold-transition login-page\">\n\t<div class=\"login-box well well-sm\">\n\t\t<div class=\"login-logo text-center\">\n\t\t\t<img src=\"assets/SDSlogo.png\" class=\"image\"\n\t\t\t\tstyle=\"image-resolution: snap;\"><br>\n\t\t\t<h2 style=\"margin-bottom:0px;\">Salam Distribution System</h2>\n\t\t\t<h4 style=\"color:gray\">";
    private final static byte[]  _wl_block2Bytes = _getBytes( _wl_block2 );

    private static java.lang.String  _wl_block3 ="</h4>\n\t\t</div>\n\t\t<!-- /.login-logo -->\n\t\t<div class=\"login-box-body\">\n\t\t\t<p class=\"login-box-msg text-center\">";
    private final static byte[]  _wl_block3Bytes = _getBytes( _wl_block3 );

    private static java.lang.String  _wl_block4 ="</p>\n\n\t\t\t<form action=\"login\" method=\"post\">\n\t\t\t\t<div class=\"form-group has-feedback\" style=\"margin-bottom: 15px;\">\n\t\t\t\t\t<input type=\"text\" name=\"loginid\" id=\"loginid\" class=\"form-control\"\n\t\t\t\t\t\tplaceholder=\"Login Id\" autocomplete=\"off\" style=\"height:34px;\"\n\t\t\t\t\t\trequired> <span\n\t\t\t\t\t\tclass=\"glyphicon glyphicon-user form-control-feedback\"></span>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"form-group has-feedback\" style=\"margin-bottom: 15px;\">\n\t\t\t\t\t<input type=\"password\" name=\"password\" id=\"password\"\n\t\t\t\t\t\tclass=\"form-control\" placeholder=\"Password\" style=\"height:34px;\"\n\t\t\t\t\t\trequired> <span\n\t\t\t\t\t\tclass=\"glyphicon glyphicon-lock form-control-feedback\"></span>\n\t\t\t\t</div>\n\t\t\t\t<div class=\"form-group\" style=\"margin-bottom: 15px; \">\n\t\t\t\t\t<a href=\"forgotPasswordPage\" class=\"pull-left\" style=\"padding-top: 6px;\">Forgot password</a>\n\t\t\t\t\t<button type=\"submit\" id=\"submit\"\n\t\t\t\t\t\tclass=\"btn btn-primary pull-right\" >Login</button>\n\t\t\t\t</div>\n\t\t\t</form>\n\t\t\t<div class=\"row\">\n\t\t\t\t<br/><br/>\n\t\t\t</div>\n\t\t\t<div class=\"row\">\n\t\t\t\t";
    private final static byte[]  _wl_block4Bytes = _getBytes( _wl_block4 );

    private static java.lang.String  _wl_block5 ="\n\t\t\t\t";
    private final static byte[]  _wl_block5Bytes = _getBytes( _wl_block5 );

    private static java.lang.String  _wl_block6 ="\n\t\t\t</div>\n\t\t\t\n\t\t</div>\n\t\t<!-- /.login-box-body -->\n\t</div>\n\t<div class=\"text-center\" style=\"color: gray;\">\n\t\t<small>Build:&nbsp; ";
    private final static byte[]  _wl_block6Bytes = _getBytes( _wl_block6 );

    private static java.lang.String  _wl_block7 ="</small>\n\t</div>\n\t<!-- /.login-box -->\n\t<script type=\"text/javascript\">\n\t\t$(\'#loginid\').focus();\n\t\t\n\t\t/* $(\'#submit\').click(function(e)\n\t\t{\n\t\t\t$(\'#actionerror\').empty();\n\t\t\tvar loginid = $(\'#loginid\');\n\t\t\tvar password = $(\'#password\');\n\t\t\tif (loginid.val().length <= 0)\n\t\t\t{\n\t\t\t\tloginid.parent().addClass(\'has-error\');\n\t\t\t} else\n\t\t\t{\n\t\t\t\tloginid.parent().removeClass(\'has-error\');\n\t\t\t}\n\t\t\tif (password.val().length <= 0)\n\t\t\t{\n\t\t\t\tpassword.parent().addClass(\'has-error\');\n\t\t\t} else\n\t\t\t{\n\t\t\t\tpassword.parent().removeClass(\'has-error\');\n\t\t\t}\n\t\t\tif (loginid.val().length <= 0)\n\t\t\t{\n\t\t\t\tloginid.focus();\n\t\t\t\te.preventDefault();\n\t\t\t\treturn;\n\t\t\t}\n\t\t\tif (password.val().length <= 0)\n\t\t\t{\n\t\t\t\tpassword.focus();\n\t\t\t\te.preventDefault();\n\t\t\t\treturn;\n\t\t\t}\n\t\t}); */\n\t</script>\n\n\n</body>\n</html>\n";
    private final static byte[]  _wl_block7Bytes = _getBytes( _wl_block7 );

    static private weblogic.jsp.internal.jsp.JspFunctionMapper _jspx_fnmap = weblogic.jsp.internal.jsp.JspFunctionMapper.getInstance();

    protected void _jspInit() {
        _jsp_expressionInterceptor = weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.getExpressionInterceptor(getServletConfig());
    }

    public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) 
    throws javax.servlet.ServletException, java.io.IOException {

        javax.servlet.ServletConfig config = getServletConfig();
        javax.servlet.ServletContext application = config.getServletContext();
        javax.servlet.jsp.tagext.JspTag _activeTag = null;
        java.lang.Object page = this;
        javax.servlet.jsp.PageContext pageContext = javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true , 8192 , true );
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter _bw = (weblogic.servlet.jsp.ByteWriter)out;
        _bw.setInitCharacterEncoding(_WL_ORIGINAL_ENCODING, _WL_ENCODED_BYTES_OK);
        javax.servlet.jsp.JspWriter _originalOut = out;
        javax.servlet.http.HttpSession session = request.getSession( true );
        try {;
            response.setContentType("text/html; charset=UTF-8");
            _bw.write(_wl_block0Bytes, _wl_block0);
            _bw.write(_wl_block1Bytes, _wl_block1);
            _bw.write(_wl_block2Bytes, _wl_block2);

            if (_jsp__tag0(request, response, pageContext, _activeTag, null))
             return;
            _bw.write(_wl_block3Bytes, _wl_block3);

            if (_jsp__tag1(request, response, pageContext, _activeTag, null))
             return;
            _bw.write(_wl_block4Bytes, _wl_block4);

            if (_jsp__tag2(request, response, pageContext, _activeTag, null))
             return;
            _bw.write(_wl_block5Bytes, _wl_block5);

            if (_jsp__tag3(request, response, pageContext, _activeTag, null))
             return;
            _bw.write(_wl_block6Bytes, _wl_block6);

            if (_jsp__tag4(request, response, pageContext, _activeTag, null))
             return;
            _bw.write(_wl_block7Bytes, _wl_block7);
        } catch (java.lang.Throwable __ee){
            __ee.setStackTrace(weblogic.jsp.internal.jsp.utils.SMAPUtils.loadSMAP(this.getClass()).processStackTrace(__ee.getStackTrace()));
            if(!(__ee instanceof javax.servlet.jsp.SkipPageException)) {
                while ((out != null) && (out != _originalOut)) out = pageContext.popBody(); 
                _releaseTags(pageContext, _activeTag);
                pageContext.handlePageException(__ee);
            }
        } finally {
            javax.servlet.jsp.JspFactory.getDefaultFactory().releasePageContext(pageContext);
        }
    }

    private boolean _jsp__tag0(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, javax.servlet.jsp.tagext.JspTag parent) throws java.lang.Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter _bw = (weblogic.servlet.jsp.ByteWriter) out;
         org.apache.struts2.views.jsp.PropertyTag __tag0 = null ;
        int __result__tag0 = 0 ;

        if (__tag0 == null ){
            __tag0 = new  org.apache.struts2.views.jsp.PropertyTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag0);
        }
        __tag0.setPageContext(pageContext);
        weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.setParentForClassicTag(__tag0, parent);
        __tag0.setValue(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("getText(\'country.name\')", java.lang.String.class,"value"));
        _activeTag=__tag0;
        __result__tag0 = __tag0.doStartTag();

        if (__result__tag0!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag0== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag0.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag0);
            return true;
        }
        _activeTag=__tag0.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag0);
        __tag0.release();
        return false;
    }

    private boolean _jsp__tag1(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, javax.servlet.jsp.tagext.JspTag parent) throws java.lang.Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter _bw = (weblogic.servlet.jsp.ByteWriter) out;
         org.apache.struts2.views.jsp.PropertyTag __tag1 = null ;
        int __result__tag1 = 0 ;

        if (__tag1 == null ){
            __tag1 = new  org.apache.struts2.views.jsp.PropertyTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag1);
        }
        __tag1.setPageContext(pageContext);
        weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.setParentForClassicTag(__tag1, parent);
        __tag1.setValue(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("getText(\'loginpage.text\')", java.lang.String.class,"value"));
        _activeTag=__tag1;
        __result__tag1 = __tag1.doStartTag();

        if (__result__tag1!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag1== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag1.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag1);
            return true;
        }
        _activeTag=__tag1.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag1);
        __tag1.release();
        return false;
    }

    private boolean _jsp__tag2(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, javax.servlet.jsp.tagext.JspTag parent) throws java.lang.Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter _bw = (weblogic.servlet.jsp.ByteWriter) out;
         org.apache.struts2.views.jsp.ui.ActionErrorTag __tag2 = null ;
        int __result__tag2 = 0 ;

        if (__tag2 == null ){
            __tag2 = new  org.apache.struts2.views.jsp.ui.ActionErrorTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag2);
        }
        __tag2.setPageContext(pageContext);
        weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.setParentForClassicTag(__tag2, parent);
        __tag2.setId(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("actionerror", java.lang.String.class,"id"));
        __tag2.setTheme(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("bootstrap", java.lang.String.class,"theme"));
        __tag2.setCssStyle(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("background-image:none;", java.lang.String.class,"cssStyle"));
        __tag2.setCssClass(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("alert-dismissible", java.lang.String.class,"cssClass"));
        __tag2.setOnclick(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("dismiss", java.lang.String.class,"onclick"));
        _activeTag=__tag2;
        __result__tag2 = __tag2.doStartTag();

        if (__result__tag2!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag2== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag2.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag2);
            return true;
        }
        _activeTag=__tag2.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag2);
        __tag2.release();
        return false;
    }

    private boolean _jsp__tag3(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, javax.servlet.jsp.tagext.JspTag parent) throws java.lang.Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter _bw = (weblogic.servlet.jsp.ByteWriter) out;
         org.apache.struts2.views.jsp.ui.ActionMessageTag __tag3 = null ;
        int __result__tag3 = 0 ;

        if (__tag3 == null ){
            __tag3 = new  org.apache.struts2.views.jsp.ui.ActionMessageTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag3);
        }
        __tag3.setPageContext(pageContext);
        weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.setParentForClassicTag(__tag3, parent);
        __tag3.setId(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("actionmessage", java.lang.String.class,"id"));
        __tag3.setTheme(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("bootstrap", java.lang.String.class,"theme"));
        __tag3.setCssStyle(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("background-image:none;", java.lang.String.class,"cssStyle"));
        __tag3.setCssClass(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("alert-dismissible", java.lang.String.class,"cssClass"));
        __tag3.setOnclick(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("dismiss", java.lang.String.class,"onclick"));
        _activeTag=__tag3;
        __result__tag3 = __tag3.doStartTag();

        if (__result__tag3!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag3== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag3.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag3);
            return true;
        }
        _activeTag=__tag3.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag3);
        __tag3.release();
        return false;
    }

    private boolean _jsp__tag4(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, javax.servlet.jsp.PageContext pageContext, javax.servlet.jsp.tagext.JspTag activeTag, javax.servlet.jsp.tagext.JspTag parent) throws java.lang.Throwable
    {
        javax.servlet.jsp.tagext.JspTag _activeTag = activeTag;
        javax.servlet.jsp.JspWriter out = pageContext.getOut();
        weblogic.servlet.jsp.ByteWriter _bw = (weblogic.servlet.jsp.ByteWriter) out;
         org.apache.struts2.views.jsp.PropertyTag __tag4 = null ;
        int __result__tag4 = 0 ;

        if (__tag4 == null ){
            __tag4 = new  org.apache.struts2.views.jsp.PropertyTag ();
            weblogic.servlet.jsp.DependencyInjectionHelper.inject(pageContext, __tag4);
        }
        __tag4.setPageContext(pageContext);
        weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.setParentForClassicTag(__tag4, parent);
        __tag4.setValue(( java.lang.String) weblogic.jsp.internal.jsp.utils.JspRuntimeUtils.convertType("getText(\'build.no\')", java.lang.String.class,"value"));
        _activeTag=__tag4;
        __result__tag4 = __tag4.doStartTag();

        if (__result__tag4!= javax.servlet.jsp.tagext.Tag.SKIP_BODY){
            if (__result__tag4== javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED) {
            }
        }
        if (__tag4.doEndTag()== javax.servlet.jsp.tagext.Tag.SKIP_PAGE){
            _activeTag = null;
            _releaseTags(pageContext, __tag4);
            return true;
        }
        _activeTag=__tag4.getParent();
        weblogic.servlet.jsp.DependencyInjectionHelper.preDestroy(pageContext, __tag4);
        __tag4.release();
        return false;
    }
}
