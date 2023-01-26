package by.belstu.it.lyskov.taglib;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.List;

public class LKEPrintTableTag extends TagSupport {
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            if (list != null) {
                JspWriter out = pageContext.getOut();
                if (list.size() > 0) {
                    out.println("<table style=\"width: 20%; border: 1px solid gray\">");
                    out.println("<tr style=\"background-color:  #59c4ff; color: white\">");
                    out.println("<th>№</td>");
                    out.println("<th>Фамилия</td>");
                    out.println("</tr>");
                    for (int i = 0; i < list.size(); i++) {
                        out.println("<tr>");
                        out.println("<td style=\"border: 1px solid gray\">" + (i + 1) + "</td>");
                        out.println("<td style=\"border: 1px solid gray\">" + list.get(i) + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                }
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
