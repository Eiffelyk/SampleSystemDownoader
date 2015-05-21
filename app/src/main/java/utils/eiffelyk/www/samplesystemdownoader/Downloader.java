package utils.eiffelyk.www.samplesystemdownoader;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

/**
 * 下载帮助类
 * Created by 馋猫 on 2015/5/21.
 */
public class Downloader {
    public static final String KEY_NAME_DOWNLOAD_ID = "downloadId";  //正在下载的sharedpreference的存储ID
    private Context context;
    private String downUrl;
    private String title ="标题";
    private String path ="download";
    private String fileName ="myDownloadFile.apk";
    private String description="描述";
    public Downloader(Context context,String downUrl){
        this.context =context;
        this.downUrl =downUrl;
    }
    public Downloader(Context context, String downUrl, String title,String path,String fileName,String description){
        this.context =context;
        this.downUrl =downUrl;
        this.title =title;
        this.path =path;
        this.description =description;
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void start() {
        if (downUrl!=null&& !downUrl.equals("")){
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(downUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);//网络类型
            request.setAllowedOverRoaming(true);   //移动网络是否允许漫游下载
            request.setTitle(title);//下载标题
            request.setDescription(description);//下载描述
            request.setDestinationInExternalPublicDir(path, fileName);
            request.setVisibleInDownloadsUi(true);//是否显示当前下载 在系统的下载界面上
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);//通知栏提示模式 下载中和下载完成都提醒
            request.setMimeType("application/vnd.android.package-archive");//打开模式
            long id = downloadManager.enqueue(request);
            DownloadingSHaredPreference.getInstance(context).putExtra(KEY_NAME_DOWNLOAD_ID, id);//将下载ID进行存储
        }else{
            Toast.makeText(context,"下载链接不正确",Toast.LENGTH_SHORT).show();
        }
    }
}
