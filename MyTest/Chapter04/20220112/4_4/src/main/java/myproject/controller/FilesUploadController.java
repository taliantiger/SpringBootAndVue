package myproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

// 遍历数组的方法有问题，运行会卡着不动
@RestController
public class FilesUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");







//    @RequestMapping(value = "/uploadTest", method = RequestMethod.POST)
//    @PostMapping(value = "/upload")
    @PostMapping("/uploads")
    public String upload(MultipartFile[] uploadFiles, HttpServletRequest req){

         String res = "";

        int length = uploadFiles.length;
        for(int i =0; i < length; i++){
            MultipartFile uploadFile =  uploadFiles[i];



            String realPath =
                    req.getSession().getServletContext().getRealPath("/uploadFile/");
            String format = sdf.format(new Date());

            File folder = new File(realPath + format);

            if(!folder.isDirectory()) {
                folder.mkdirs();
            }

            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() +
                    oldName.substring(oldName.lastIndexOf("."), oldName.length());

            try{
                uploadFile.transferTo(new File(folder, newName));

                String filePath = req.getScheme() + "://" + req.getServerName() + ":" +
                        req.getServerPort() + "/uploadFile/" + format + newName;

                res = res + filePath + "------";
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return res;
    }
}
