package servlets;

import com.google.gson.Gson;
import database.DB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Respuesta;
import services.TweetsService;

public class busqueda extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Gson json = new Gson();
            
            TweetsService tweetsService = new TweetsService();
            
            String termino = request.getParameter("termino");
            int count = Integer.parseInt(request.getParameter("cantidad"));
            
            Respuesta resp = tweetsService.getTweets(termino, count);
            
            if (!resp.isOk()) {
                
                resp.setMessage("Hubo un problema, lo sentimos ):");
                
                out.println(json.toJson(resp));
                
                return;
                
            }
            
            DB.postBusqueda(termino);
            
            out.println(json.toJson(resp));
            
        }
    }
    
//    {"createdAt":"Nov 26, 2019 4:26:03 PM","id":1199408998916726785,"text":"NG-BE 🖤💛❤️\n@ngbeconf\nBelgium\u0027s annual Angular Conference - Holiday Inn Ghent Expo | 5-6 December 2019 #ngbe\nGhent, https://t.co/s0GB357uwJ https://t.co/y7DwTx2hzh","displayTextRangeStart":0,"displayTextRangeEnd":138,"source":"\u003ca href\u003d\"https://mobile.twitter.com\" rel\u003d\"nofollow\"\u003eTwitter Web App\u003c/a\u003e","isTruncated":false,"inReplyToStatusId":-1,"inReplyToUserId":-1,"isFavorited":false,"isRetweeted":false,"favoriteCount":0,"retweetCount":0,"isPossiblySensitive":false,"lang":"en","contributorsIDs":[],"userMentionEntities":[{"name":"NG-BE 🖤💛❤️","screenName":"ngbeconf","id":4836178324,"start":13,"end":22}],"urlEntities":[{"url":"https://t.co/s0GB357uwJ","expandedURL":"http://Belgiumng-be.org","displayURL":"Belgiumng-be.org","start":117,"end":140}],"hashtagEntities":[{"text":"ngbe","start":104,"end":109}],"mediaEntities":[{"id":1199408992868540416,"url":"https://t.co/y7DwTx2hzh","mediaURL":"http://pbs.twimg.com/media/EKUoH9BWoAAjzO4.jpg","mediaURLHttps":"https://pbs.twimg.com/media/EKUoH9BWoAAjzO4.jpg","expandedURL":"https://twitter.com/EventosGlobais/status/1199408998916726785/photo/1","displayURL":"pic.twitter.com/y7DwTx2hzh","sizes":{"0":{"width":150,"height":150,"resize":101},"1":{"width":680,"height":395,"resize":100},"2":{"width":1200,"height":697,"resize":100},"3":{"width":1205,"height":700,"resize":100}},"type":"photo","videoAspectRatioWidth":0,"videoAspectRatioHeight":0,"videoDurationMillis":0,"videoVariants":[],"start":141,"end":164}],"symbolEntities":[],"currentUserRetweetId":-1,"user":{"id":314285778,"name":"GLOBAIS Eventos","screenName":"EventosGlobais","location":"Herriliberg, Schweiz","description":"Business Events, conferences, congresses and incentive events\ncastrojocelina@gmail.com","descriptionURLEntities":[],"isContributorsEnabled":false,"profileImageUrl":"http://pbs.twimg.com/profile_images/1060605082247618563/t1xc44i5_normal.jpg","profileImageUrlHttps":"https://pbs.twimg.com/profile_images/1060605082247618563/t1xc44i5_normal.jpg","isDefaultProfileImage":false,"isProtected":false,"followersCount":2918,"profileBackgroundColor":"131516","profileTextColor":"333333","profileLinkColor":"981CEB","profileSidebarFillColor":"EFEFEF","profileSidebarBorderColor":"000000","profileUseBackgroundImage":false,"isDefaultProfile":false,"showAllInlineMedia":false,"friendsCount":1188,"createdAt":"Jun 9, 2011 9:48:23 PM","favouritesCount":17676,"utcOffset":-1,"profileBackgroundImageUrl":"http://abs.twimg.com/images/themes/theme14/bg.gif","profileBackgroundImageUrlHttps":"https://abs.twimg.com/images/themes/theme14/bg.gif","profileBannerImageUrl":"https://pbs.twimg.com/profile_banners/314285778/1458732053","profileBackgroundTiled":true,"statusesCount":52186,"isGeoEnabled":true,"isVerified":false,"translator":false,"listedCount":457,"isFollowRequestSent":false},"quotedStatusId":-1}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
