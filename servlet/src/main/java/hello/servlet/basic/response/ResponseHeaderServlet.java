package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK); // HTTP 응답 코드를 넣을 수 있다.

        // [reponse-header]
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store,must-revalidate"); // cache 완전 무효화 한다는 의미의 setHeader
        response.setHeader("Pramga", "no-cache"); // cache 완전 무효화 한다는 의미의 setHeader
        response.setHeader("my-header", "Hello");

        // [Header의 편의 메소드]
        content(response);

        // [Cookie의 편의 메소드]
        cookie(response);

        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("response OK");
    }

    private void content(HttpServletResponse response) {
        //Content-Type : text/plain;charset=utf-8
        //Content-Length : 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");ㄱ
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); // (생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie : myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "MyCookie=good"; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Loaction : /basic/hello-form.html

        //response.setStatus(HTTPServletResponse.SC_FOUND); // 302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
