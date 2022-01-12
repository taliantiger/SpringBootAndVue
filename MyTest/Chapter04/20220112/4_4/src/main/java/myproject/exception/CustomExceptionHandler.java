package myproject.exception;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


// Version1:
//@ControllerAdvice
//public class CustomExceptionHandler {
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public void uploadException(MaxUploadSizeExceededException e,
//                                HttpServletResponse resp) throws IOException {
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.write("The upload file is over limit");
//        out.flush();
//        out.close();
//    }
//}


//Version2:By using the page template of Thymeleaf
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView uploadException(MaxUploadSizeExceededException e) throws IOException {

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Upload File Is Over Limit!");
        mv.setViewName("error");
        return mv;
    }
}
