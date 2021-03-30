package pl.example.recipeBook.client.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class FilterCss implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // encoding format setting request
        request.setCharacterEncoding("utf-8");  // post change (requesting entity)
        // set the response code format
        response.setContentType("text/css;charset=utf-8");// modify the response code
        chain.doFilter(request, response);
    }
}
