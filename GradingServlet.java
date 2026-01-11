import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GradingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("studentName");
        String studentClass = request.getParameter("class");
        
        double subject1 = Double.parseDouble(request.getParameter("subject1"));
        double subject2 = Double.parseDouble(request.getParameter("subject2"));
        double subject3 = Double.parseDouble(request.getParameter("subject3"));
        double subject4 = Double.parseDouble(request.getParameter("subject4"));
        double subject5 = Double.parseDouble(request.getParameter("subject5"));

        double average = (subject1 + subject2 + subject3 + subject4 + subject5) / 5;
        String grade = calculateGrade(average);

        System.out.println("Student: " + studentName);
        System.out.println("Class: " + studentClass);
        System.out.println("Average Marks: " + average);
        System.out.println("Grade: " + grade);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Grading Results</h1>");
        out.println("<p>Student Name: " + studentName + "</p>");
        out.println("<p>Class: " + studentClass + "</p>");
        out.println("<p>Average Marks: " + average + "</p>");
        out.println("<p>Grade: " + grade + "</p>");
        out.println("</body></html>");
    }

    private String calculateGrade(double average) {
        if (average >= 90) return "A+";
        else if (average >= 80) return "A";
        else if (average >= 70) return "B";
        else if (average >= 60) return "C";
        else if (average >= 50) return "D";
        else return "F";
    }
}
