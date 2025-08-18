package org.zerock.ex00.util;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.ibatis.javassist.bytecode.ExceptionsAttribute;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.ex00.domain.AttachVO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.UUID;

@Component
@Log4j2
public class UpDownUtil {

    private final String UPLOAD = "/Users/kronos0697/Trajectory/JAVA/SpringLegacyStudy";

    public List<AttachVO> upload(MultipartFile[] files) {//파일데이터 의미 -> 컨트롤러가 수령

            if(files == null || files.length == 0){
                return null;
            }

            List<AttachVO> list = new ArrayList<>();

            for (MultipartFile file: files) {

                if(file.getSize() == 0){
                    continue;
                }

                String fileName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();

                String saveFileName = uuid +"_" + fileName;

                //jpg,gif,png,bmp
                String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

                String regExp = "^(jpg|jpeg|JPG|JPEG|png|PNG|gif|GIF|bmp|BMP)";
                if(!suffix.matches(regExp)){
                    continue;
                }


                try(
                        InputStream in = file.getInputStream();
                        OutputStream out = new FileOutputStream(UPLOAD+ File.separator+saveFileName)
                ){

                    FileCopyUtils.copy(in,out);

                    Thumbnails.of( new File(UPLOAD+ File.separator+saveFileName))
                            .size(200,200)
                            .toFile(UPLOAD+File.separator+"s_"+saveFileName);

                    AttachVO attachVO = new AttachVO();
                    attachVO.setUuid(uuid);
                    attachVO.setFileName(fileName);

                    list.add(attachVO);

                }catch(Exception e){
                    log.error(e.getMessage());
                }//end catch

            }//파일 업로드 처리 for
            return list;
    }

    public void deleteFiles(String[] fileNames) {

        if (fileNames == null || fileNames.length == 0) {
            return;
        }

        for (String fileName : fileNames) {
            File origianlFile = new File(UPLOAD + File.separator + fileName);
            File thumbFile = new File(UPLOAD + File.separator+"s_" + fileName);

            origianlFile.delete();
            thumbFile.delete();
        }
    }


}
