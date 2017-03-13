package face;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alienware on 2017/3/9.
 * 测试face++的证件识别API
 * 分别可以识别 1.二代省份证 2.机动车驾驶证 3. 机动车行驶证
 */
public class Ocridcard {
    public static void main(String []args){
        //预备参数
        HashMap<String, String> params = new HashMap<>();
        params.put("api_key","对应api_key");
        params.put("api_secret","对应api_secret");
        params.put("legality","1");
        File file = new File("E://card.jpg");
        List<File> imageFiles = new ArrayList<File>();
        imageFiles.add(file);

        String url ="https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";
        String result = HttpUtil.post(url,params,imageFiles);

        if(result!=null){
            System.out.println("请求成功:\n"+result);
        }

    }

}
