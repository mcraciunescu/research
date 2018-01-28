/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.util;

import edu.marius.graph.entity.UserType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JsfUtils {

    public static String getRootPath() {
        return getRequest().getContextPath();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public static void redirect(String path) {
        try {
            getExternalContext().redirect(path);
        } catch (IOException ex) {
            Logger.getLogger(JsfUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserType getUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (UserType) session.getAttribute("user");
        }
        return null;
    }

    public static HttpSession getSession() {
        return (HttpSession) getExternalContext().getSession(false);
    }

    public static ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

//    public static void addMessage(Message message) {
//        addMessage(
//                asFacesSeverity(message.getSeverity()),
//                message.getSeverity().toString(),
//                message.getMessage());
//    }
    public static void addMessage(FacesMessage.Severity severity, String summary, String message) {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(severity, summary, message));
    }

//    private static FacesMessage.Severity asFacesSeverity(Severity severity) {
//        switch (severity) {
//            case FATAL:
//                return FacesMessage.SEVERITY_FATAL;
//            case ERROR:
//                return FacesMessage.SEVERITY_ERROR;
//            case WARNING:
//                return FacesMessage.SEVERITY_WARN;
//            case INFO:
//                return FacesMessage.SEVERITY_INFO;
//            default:
//                return null;
//        }
//    }
}
