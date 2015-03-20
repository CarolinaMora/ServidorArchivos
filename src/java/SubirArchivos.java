/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Carolina
 */
public class SubirArchivos extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static String error = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //BUSCAR USUARIO EN LA BASE
            
            /*String nombreUsuario = request.getParameter("usuario");  
            if(nombreUsuario == null){
                response.setStatus(500);
            }
            
            else{*/
                String root = getServletContext().getRealPath("\\");
            
                if(procesaFicheros(request,out, "nombreUsuario", root)){
                    response.setStatus(200);
                }
                else{
                    response.setStatus(500);
                }
            //}
            
            
          
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    public static boolean procesaFicheros(HttpServletRequest request, PrintWriter out, String usuario, String root ) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
   if (isMultipart) {
       FileItemFactory factory = new DiskFileItemFactory();
       ServletFileUpload upload = new ServletFileUpload(factory);
       try {
            List items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                 FileItem item = (FileItem) iterator.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName();
                        File path = new File(root + "\\Files\\"+usuario);
                        if (!path.exists()) {
                            boolean status = path.mkdirs();
                        }
                        File uploadedFile = new File(path + "\\" + fileName);
                        item.write(uploadedFile);
                        
                        System.out.println("Se creo el archivo");
                        
                        }
                    else{
                        System.out.println("No hay archivo");
                        error = "Error al subir archivo";
                        return false;
                    }
                    }
            
       } catch (FileUploadException e) {
          e.printStackTrace();
          error = "Error al subir archivo";
          return false;
          } catch (Exception e) {
          e.printStackTrace();
          error = "Error en el servidor";
          return false;
          }
      }
   return true;
    }
    
    
}
