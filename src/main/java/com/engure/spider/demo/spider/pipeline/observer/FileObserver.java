package com.engure.spider.demo.spider.pipeline.observer;

import com.engure.spider.demo.util.JsonUtil;
import com.engure.spider.demo.util.UUIDUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileObserver implements Observer {

    private String defaultPath = "C:\\Users\\HiWin10\\Desktop\\data\\";

    public FileObserver() {

    }

    public FileObserver(String path) {
        defaultPath = path;
    }


    @Override
    public void update(Object data) {

        if (data == null) throw new NullPointerException("data is null!");

        System.out.println("begin to write to file. -------------------------");

        String dataStr = JsonUtil.object2JsonStr(data);

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-HH-mm-ss");
        String dateStr = sdf.format(new Date());
        String stamp = UUIDUtil.uuid().substring(24);
        String storePath = defaultPath + dateStr + stamp + ".json";

        File file = new File(storePath);
        if (file.exists()) file.delete();
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(dataStr.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("write to " + storePath + " successfully! ----------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
