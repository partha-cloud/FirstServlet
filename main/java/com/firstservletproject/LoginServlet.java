package com.firstservletproject;

public class LoginServlet {
    @WebServlet(
            description = "Login Servlet Testing",
            urlPatterns = { "/LoginServlet" },
            initParams = {
                    @WebInitParam(name = "user", value = "Partha"),
                    @WebInitParam(name = "password", value = "Partha11@gmail.com")
            }
    )

    public class LoginServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected <HttpServletResponse, HttpServletRequest> void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String user = request.getParameter("user");
            String pwd = request.getParameter("password");
            String userRegex = "^[A-Z]{1}+[a-z]{2,}";
            String pwdRegex = "^.*(?=.*[A-Z])(?=.*[0-9])([a-z])(?=.*[@#$%^&+=])(?=.{8,}).*$";
            String userTest = getServletConfig().getInitParameter("user");
            String pwdTest = getServletConfig().getInitParameter("password");
            if(Pattern.matches(pwdRegex, pwd) && Pattern.matches(userRegex, user)) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
            }else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>Either username or password is incorrect.</font>");
                rd.include(request, response);
            }
        }

    }
}
