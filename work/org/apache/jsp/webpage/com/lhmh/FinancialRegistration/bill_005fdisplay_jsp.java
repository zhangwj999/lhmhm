package org.apache.jsp.webpage.com.lhmh.FinancialRegistration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class bill_005fdisplay_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write(" <head>\r\n");
      out.write("  <title>asdadfa</title>\r\n");
      out.write("  ");
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write(" </head>\r\n");
      out.write(" <link\r\n");
      out.write("\thref=\"");
      out.print(path);
      out.write("/webpage/com/lhmh/FinancialRegistration/tablecloth/tablecloth.css\"\r\n");
      out.write("\trel=\"stylesheet\" type=\"text/css\" media=\"screen\" />\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.print(path);
      out.write("/webpage/com/lhmh/FinancialRegistration/tablecloth/tablecloth.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("body {\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\tpadding: 0;\r\n");
      out.write("\tbackground: #f1f1f1;\r\n");
      out.write("\tfont: 70% Arial, Helvetica, sans-serif;\r\n");
      out.write("\tcolor: #555;\r\n");
      out.write("\tline-height: 150%;\r\n");
      out.write("\ttext-align: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a {\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcolor: #057fac;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("a:hover {\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tcolor: #999;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("h1 {\r\n");
      out.write("\tfont-size: 140%;\r\n");
      out.write("\tmargin: 0 20px;\r\n");
      out.write("\tline-height: 80px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("h2 {\r\n");
      out.write("\tfont-size: 120%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#container {\r\n");
      out.write("\tmargin: 0 auto;\r\n");
      out.write("\twidth: 680px;\r\n");
      out.write("\tbackground: #fff;\r\n");
      out.write("\tpadding-bottom: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#content {\r\n");
      out.write("\tmargin: 0 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("p.sig {\r\n");
      out.write("\tmargin: 0 auto;\r\n");
      out.write("\twidth: 680px;\r\n");
      out.write("\tpadding: 1em 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("form {\r\n");
      out.write("\tmargin: 1em 0;\r\n");
      out.write("\tpadding: .2em 20px;\r\n");
      out.write("\tbackground: #eee;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write(" <body style=\"overflow-y: hidden\" scroll=\"no\">\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write("   \r\n");
      out.write("   <!-- 播放区域 -->\r\n");
      out.write(" <div id=\"playzone\" width=\"550px\" height=\"550\" >\r\n");
      out.write("<object classid=\"clsid:866220F2-4079-4D30-A9A8-E48741BD65B6\" id=\"prew\" width='100%' height=\"550\" VIEWASTEXT>\r\n");
      out.write("  <param name=\"BorderStyle\" value=\"1\" />\r\n");
      out.write("  <param name=\"MousePointer\" value=\"0\" />\r\n");
      out.write("  <param name=\"Enabled\" value=\"1\" />\r\n");
      out.write("  <param name=\"Min\" value=\"0\" />\r\n");
      out.write("  <param name=\"Max\" value=\"10\" />\r\n");
      out.write("</object>\r\n");
      out.write(" </div>\r\n");
      out.write("\r\n");
      out.write("   \r\n");
      out.write(" </body>\r\n");
      out.write(" \r\n");
      out.write(" <script type=\"text/javascript\">\r\n");
      out.write(" repaly();\r\n");
      out.write("function repaly(){\r\n");
      out.write("\t\t//alert(\"这是视频回放\");\r\n");
      out.write("\t\tprew.ConnectA(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ip}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\", 6002, 0, 0, \"system\", \"system\");\r\n");
      out.write("\t\t//ConnectA(“192.168.0.10”, 6002, 0, 0, “system”, “system”);\r\n");
      out.write("\t  //play.CreatePlay(\"192.168.199.102\",'1',\"2015-09-09 22:22:22\");\r\n");
      out.write("\t   //alert(\"aaa\");\r\n");
      out.write("}\t\r\n");
      out.write(" //视频初始化\r\n");
      out.write(" function init(){\r\n");
      out.write("\t prew.Pause();\r\n");
      out.write(" }\r\n");
      out.write(" //视频初始化\r\n");
      out.write(" function start(){\r\n");
      out.write("\t alert(\"sdsdsd\");\r\n");
      out.write("\t alert(play);\r\n");
      out.write("\t// CreatePrew（采集器地址，通道);//打开视频预览\r\n");
      out.write("\t prew.CreatePrew(\"192.168.199.101\",0);\r\n");
      out.write(" }\r\n");
      out.write(" \r\n");
      out.write(" //终止回放\r\n");
      out.write(" function stopPlay(){\r\n");
      out.write("\t ClosePlay()//终止回放\r\n");
      out.write(" }\r\n");
      out.write(" //关闭预览\r\n");
      out.write(" function closePrew(){\r\n");
      out.write("\t ClosePrew();//关闭视频预览 \r\n");
      out.write(" }\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write(" \r\n");
      out.write(" </script>");
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
    // /webpage/com/lhmh/FinancialRegistration/bill_display.jsp(7,2) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fbase_005f0.setType("jquery,easyui,tools,DatePicker");
    int _jspx_eval_t_005fbase_005f0 = _jspx_th_t_005fbase_005f0.doStartTag();
    if (_jspx_th_t_005fbase_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
    return false;
  }
}
