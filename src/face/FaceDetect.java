package face;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alienware on 2017/3/9.
 * 测试face++的人脸识别detect API
 */
public class FaceDetect {
    public static void main(String []args){
        //预备参数
        HashMap<String, String> params = new HashMap<>();
        params.put("api_key","对应api_key");
        params.put("api_secret","对应api_secret");
        params.put("return_attributes","gender,age,smiling,glass,headpose,facequality,blur");
        File file = new File("E://112.jpg");
        List<File> imageFiles = new ArrayList<File>();
        imageFiles.add(file);

        String url ="https://api-cn.faceplusplus.com/facepp/v3/detect";
        String result = HttpUtil.post(url,params,imageFiles);

        if(result!=null){
            System.out.println("请求成功:\n"+result);
        }

    }

}
