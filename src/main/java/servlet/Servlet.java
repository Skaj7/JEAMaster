//package servlet;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.HttpConstraint;
//import javax.servlet.annotation.ServletSecurity;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by Kaj Suiker on 12-3-2017.
// */
//@WebServlet(name = "/Servlet")
//@ServletSecurity(
//        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL,
//                rolesAllowed = {"Admin", "User"}))
//public class Servlet extends HttpServlet {
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public Servlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter printWriter = response.getWriter();
//        response.setContentType("text/html");
//        printWriter.println("success...");
//    }
//}
