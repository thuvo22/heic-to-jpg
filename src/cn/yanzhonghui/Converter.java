package cn.yanzhonghui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
public class Converter {

    private static final String IMAGE_MAGICK_PATH="C:\\Program Files\\ImageMagick-7.1.0-Q16-HDRI";
    private static final String PHOTO_SRC="C:\\Users\\Thu\\Downloads\\heic";
    private static final String PHOTO_DES="C:\\Users\\Thu\\Downloads\\converted";
    public static void main(String[] args) {
        File folder = new File(PHOTO_SRC);
        File[] listOfFiles = folder.listFiles();
        ConvertCmd cmd = new ConvertCmd();
        cmd.setSearchPath(IMAGE_MAGICK_PATH);

        IMOperation op = new IMOperation();


        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                op.addImage(PHOTO_SRC+"\\"+listOfFiles[i].getName());
                String fileNameWithOutExt = FilenameUtils.removeExtension(listOfFiles[i].getName());
                op.addImage(PHOTO_DES+"\\"+fileNameWithOutExt+".JPG");
            }
        }


        try {
            cmd.run(op);
        } catch (IOException | InterruptedException | IM4JavaException e) {
            e.printStackTrace();
        }

    }
}