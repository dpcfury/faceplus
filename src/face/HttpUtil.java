package face;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by alienware on 2017/3/9.
 */
public class HttpUtil {
    /*创建默认的httpClient*/
    private static  CloseableHttpClient httpclient= HttpClients.createDefault();

    public  static String post(String url,Map<String,String> params, List<File> imageFiles){
        String result =null;
        //创建post请求
        HttpPost httpPost= new HttpPost(url);

        //
        HttpEntity entity = createEntity(params, imageFiles);
        httpPost.setEntity(entity);
        try {
            CloseableHttpResponse response= httpclient.execute(httpPost);
            result=EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static HttpEntity createEntity(Map<String,String> maps, List<File> imageFiles){
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();

        //设置一般参数
        for(String key:maps.keySet()){
            meBuilder.addPart(key,new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }
        //插入文件流

        int index =1;
        for (File file : imageFiles) {
            FileBody fileBody = new FileBody(file);
            String keyName = imageFiles.size()>1?"image_file"+index:"image_file";
            meBuilder.addPart("image_file" ,fileBody);
            index++;
        }

        return meBuilder.build();
    }
}
