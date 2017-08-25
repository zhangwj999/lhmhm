package org.apache.jsp.webpage.main;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class bootstrap_005fmain_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/context/mytags.jsp");
    _jspx_dependants.add("/WEB-INF/tld/easyui.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

      out.write('\r');
      out.write('\n');
      //  c:set
      org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
      _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fset_005f0.setParent(null);
      // /context/mytags.jsp(9,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setVar("webRoot");
      // /context/mytags.jsp(9,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setValue(basePath);
      int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
      if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      out.write("\r\n");
      out.write("<!DOCTYPE html >\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>联合美华手术示教系统v2015</title>\r\n");
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"plug-in/bootstrap/css/bootstrap.css\" type=\"text/css\"></link>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".bootstrap-menu i {\r\n");
      out.write("\tdisplay: inline-block;\r\n");
      out.write("\twidth: 14px;\r\n");
      out.write("\theight: 14px;\r\n");
      out.write("\tmargin-top: 1px;\r\n");
      out.write("\t*margin-right: .3em;\r\n");
      out.write("\tline-height: 14px;\r\n");
      out.write("\tvertical-align: text-top;\r\n");
      out.write("\tbackground-image: url(\"plug-in/bootstrap/img/glyphicons-halflings.png\");\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".bootstrap-center {\r\n");
      out.write("\theight: 530px;\r\n");
      out.write("\toverflow-y: auto;\r\n");
      out.write("\tmargin-top: -20px;\r\n");
      out.write("\tmargin-left: 2px;\r\n");
      out.write("\tmargin-right: 2px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".bootstrap-icon {\r\n");
      out.write("\tdisplay: inline-block;\r\n");
      out.write("\twidth: 16px;\r\n");
      out.write("\theight: 16px;\r\n");
      out.write("\tline-height: 16px;\r\n");
      out.write("\tvertical-align: text-top;\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\tbackground-image: url(\"plug-in/accordion/images/pictures.png\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".footer {\r\n");
      out.write("\tmargin-top: 10px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- 头部菜单导航-->\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("<div class=\"navbar\">\r\n");
      out.write("<div class=\"navbar-inner\">\r\n");
      out.write("<div class=\"container-fluid\"><a class=\"brand\" href=\"\" target=\"_blank\">联合美华手术示教系统v2015 &nbsp;&nbsp;<span class=\"slogan\"></span></a>\r\n");
      out.write("<div class=\"nav-no-collapse bootstrap-menu\">\r\n");
      out.write("\r\n");
      out.write("<ul class=\"nav pull-right usernav\">\r\n");
      out.write("\t<li style=\"line-height: 43px;\"><span style=\"color: #CC33FF\">当前用户:</span><span style=\"color: #666633\">(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(")</span> <span style=\"color: #CC33FF\">职务</span>:<span style=\"color: #666633\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roleName\r\n	}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span></li>\r\n");
      out.write("\t<li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle avatar\" data-toggle=\"dropdown\"> <i class=\"icon-wrench\"></i> <span class=\"txt\">控制面板</span> <b class=\"caret\"></b> </a>\r\n");
      out.write("\t<ul class=\"dropdown-menu\">\r\n");
      out.write("\t\t<li onclick=\"openwindow('用户信息','userController.do?userinfo')\"><a href=\"javascript:;\"><i class=\"icon-user\"></i> 个人信息</a></li>\r\n");
      out.write("\t\t<li onclick=\"add('修改密码','userController.do?changepassword')\"><a href=\"javascript:;\"><i class=\"icon-pencil\"></i> 修改密码</a></li>\r\n");
      out.write("\t\t<li onclick=\"add('修改首页风格','userController.do?changestyle')\"><a href=\"javascript:;\"><i class=\"icon-retweet\"></i> 首页风格</a></li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t</li>\r\n");
      out.write("\t<li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle avatar\" data-toggle=\"dropdown\"> <i class=\"icon-arrow-left\"></i> <span class=\"txt\">注销</span> <b class=\"caret\"></b> </a>\r\n");
      out.write("\t<ul class=\"dropdown-menu\">\r\n");
      out.write("\t\t<li onclick=\"exit('loginController.do?logout','确定退出该系统吗 ?',1);\"><a href=\"javascript:;\"><i class=\"icon-off\"></i>退出系统</a></li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t</li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- /.nav-collapse --></div>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- /navbar-inner --></div>\r\n");
      out.write("<!-- /navbar --></div>\r\n");
      out.write("<!-- End #header -->\r\n");
      out.write("\r\n");
      out.write("<!-- 中间 -->\r\n");
      out.write("<div id=\"wrapper\" class=\"bootstrap-center\"></div>\r\n");
      out.write("\r\n");
      out.write("<!-- 底部 -->\r\n");
      out.write("<footer class=\"footer\">\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("<ul class=\"footer-links\">\r\n");
      out.write("\t<li style=\"display: inline;\">&copy; 版权所有<a href=\"\" title=\"\">联合美华</a></li>\r\n");
      out.write("\t<li style=\"display: inline;\">&nbsp; 技术支持:<a title=\"联合美华\" href=\"\">LHMH</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("</footer>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plug-in/bootstrap/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"plug-in/accordion/js/bootstrap_main.js\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_t_005fbase_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  t:base
    org.jeecgframework.tag.core.easyui.BaseTag _jspx_th_t_005fbase_005f0 = (org.jeecgframework.tag.core.easyui.BaseTag) _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.get(org.jeecgframework.tag.core.easyui.BaseTag.class);
    _jspx_th_t_005fbase_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fbase_005f0.setParent(null);
    // /webpage/main/bootstrap_main.jsp(7,0) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fbase_005f0.setType("jquery,easyui,tools,DatePicker,autocomplete");
    int _jspx_eval_t_005fbase_005f0 = _jspx_th_t_005fbase_005f0.doStartTag();
    if (_jspx_th_t_005fbase_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
    return false;
  }
}
