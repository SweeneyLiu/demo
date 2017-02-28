package test.com.demo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.com.demo.R;
import test.com.demo.utils.Tools;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
//        testObserver();
        testConsume();
    }

    private void testConsume() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                Log.d(TAG, "onNext(1)");
                e.onNext(2);
                Log.d(TAG, "onNext(2)");
                e.onComplete();
                Log.d(TAG, "onComplete(1)");
                e.onNext(3);
                Log.d(TAG, "onNext(3)");
            }
        }).subscribeOn(Schedulers.newThread()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext---main---Consumer thread is : " + Thread.currentThread().getName());
                Log.d(TAG, String.valueOf(integer));
            }
        }).observeOn(Schedulers.io()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext---io1---Consumer thread is : " + Thread.currentThread().getName());
                Log.d(TAG, String.valueOf(integer));
            }
        }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext---io2---Consumer thread is : " + Thread.currentThread().getName());
                Log.d(TAG, String.valueOf(integer));
            }
        });
    }


    private void testObserver() {
        //创建一个Observable
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Log.d(TAG, "onNext(1)");
                e.onNext(2);
                Log.d(TAG, "onNext(2)");
//                e.onError(null);
//                Log.d(TAG, "onError(1)");
//                e.onError(null);
//                Log.d(TAG, "onError(2)");
                e.onComplete();
                Log.d(TAG, "onComplete(1)");
//                e.onComplete();
//                Log.d(TAG, "onComplete(2)");
                e.onNext(3);
                Log.d(TAG, "onNext(3)");

            }
        }).subscribe(new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext");
                if (value == 1) {
//                    disposable.dispose();
                    Log.d(TAG, "dispose");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });

        //创建一个Observer
        /*Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext");
                if (value == 1) {
                    Log.d(TAG, "dispose");
                    mDisposable.dispose();
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        //建立关联
        observable.subscribe(observer);*/

    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("loading......");
                progressDialog.setCancelable(true);//可以通过back键取消掉
                progressDialog.show();
                break;
            case R.id.button2:
                getSystemDefaultEnglishFont();
//                getSystemAllFallBackFonts();
                break;
            case R.id.button3:
//                getSystemEnglishFonts();
                getSystemDefaultFont();
                break;
            case R.id.button4:
//                test();
//                test1();
//                testList();
                getSystemEnglishFont();
                break;
        }
    }

    /*private void testList() {
        List<String> list = new ArrayList<>();
        String systemFontsPath = Environment.getRootDirectory().getPath() + File.separator + "fonts";
        File file=new File(systemFontsPath);
        if(file!=null){
            if(file.isDirectory()){
                File[] fileNames=file.listFiles();
                if(fileNames!=null){
                    for (int i = 0; i < fileNames.length; i++) {
                        //递归调用
                        long blockSize = getFileSize(fileNames[i]);
                        if(blockSize > 1048576){
                            list.add(fileNames[i].getPath());
                        }
                    }
                }
            }
        }

        for(int j = 0;j<list.size();j++){
            Log.i(TAG, "testList: 可以字体列表--"+list.get(j));
        }

    }*/


    /*private void test1() {
        List<String> list = new ArrayList<>();
        *//*String path = Environment.getRootDirectory().getPath() + File.separator + "etc" + File.separator + "fonts.xml";
        String path2 = "mnt" + File.separator + "sdcard" + File.separator + "Download" + File.separator + "mfonts.xml";
        String path3 = Environment.getExternalStorageDirectory() + File.separator + "sdcard" + File.separator + "Download" + File.separator + "nocnfonts.xml";*//*

        String path1 = Environment.getExternalStorageDirectory().getPath() + File.separator + "fonts7.0.xml";
        String path2 = Environment.getExternalStorageDirectory().getPath() + File.separator + "mfonts7.0.xml";
        String path3 = Environment.getExternalStorageDirectory().getPath() + File.separator + "nocnfonts7.0.xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = db.parse(new FileInputStream(new File(path3)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XPathFactory factory = XPathFactory.newInstance();

        XPath xpath = factory.newXPath();

        String expression;
        NodeList nodeList = null;

        expression = "familyset/family[@lang=\"zh-Hans\"]/font";

        try {
            nodeList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        if (nodeList.getLength()==0) {
            String expression2 = "familyset/family[not(@lang)]/font[@weight=\"400\"][@style=\"normal\"]";
            NodeList nodeList2 = null;
            try {
                nodeList2 = (NodeList) xpath.evaluate(expression2, doc, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
                for (int j = 0; j < nodeList2.getLength(); j++) {
                    list.add(nodeList2.item(j).getTextContent());
                }
        } else {
            int length = nodeList.getLength();
            if(length == 1){
                list.add(nodeList.item(0).getChildNodes().item(0).getTextContent());
            }else{
                for (int i = 0; i < nodeList.getLength(); i++) {
                    NamedNodeMap namedNodeMap = nodeList.item(i).getAttributes();
                    if((namedNodeMap.getNamedItem("weight").getNodeValue().equals("400"))&&(namedNodeMap.getNamedItem("style").getNodeValue().equals("normal"))){
                        list.add(nodeList.item(i).getChildNodes().item(0).getTextContent());
                    }
                    }
                }
        }
    }
    private void test() {
        String path = Environment.getRootDirectory().getPath() + File.separator + "etc" + File.separator + "fonts.xml";
        File file = new File(path);
        readXML(file);
    }

    private void readXML(File file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            Element element = doc.getDocumentElement();
            listAllChildNodes(element, 0);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    *//*
     * 递归遍历并打印所有的ElementNode(包括节点的属性和文本节点的有效内容),按一般的xml样式展示出来(空格来表示层次)
     *//*
    public void listAllChildNodes(Node node, int level) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {

            if("family".equals(node.getNodeName())){
                if(node.hasAttributes()){
                    NamedNodeMap nnmap = node.getAttributes();
                    for (int i = 0; i < nnmap.getLength(); i++) {
                        if("lang".equals(nnmap.item(i).getNodeName())){
                            if("zh-Hans".equals(nnmap.item(i).getNodeValue())){
                                if(node.hasChildNodes()){
                                    level++;
                                    NodeList nodelist = node.getChildNodes();
                                    for (int j = 0; j < nodelist.getLength(); j++) {
                                        if (nodelist.item(j).getNodeType() == Node.TEXT_NODE
                                                && (!nodelist.item(i).getTextContent()
                                                .matches("\\s+"))) {// 用正则选取内容包含非空格的有效字符的文本节点
//                                            hasTextChild = true;// 该ElementNode的一级子节点是存在有效字符的文本节点
                                            System.out.print(nodelist.item(i).getTextContent());// 在开始标签后面添加文本内容
                                        } else if (nodelist.item(i).getNodeType() == Node.ELEMENT_NODE) {
                                            System.out.println();
                                            listAllChildNodes(nodelist.item(i), level);// level表示该节点处于第几个层次(相应空格)
                                        }
                                    }
                                    level--;// 遍历完所有的子节点,层次变量随子节点的层数,依次递减,回归到该节点本身的层次
                                }
                            }
                        }
                    }
                }
            }

            boolean hasTextChild = false;
            String levelSpace = "";
            for (int i = 0; i < level; i++) {
                levelSpace += "    ";
            }
            Log.i(TAG, "listAllChildNodes: "+(levelSpace + "<" + node.getNodeName() + (node.hasAttributes() ? " " : ">")));// 有属性的话节点的开始标签后面的尖括号">"就留待属性打印完再打印
            if (node.hasAttributes()) {
                NamedNodeMap nnmap = node.getAttributes();
                for (int i = 0; i < nnmap.getLength(); i++) {
                    System.out.print(nnmap.item(i).getNodeName()
                            + "=\""// 字符串里含双引号要用到转义字符\
                            + nnmap.item(i).getNodeValue() + "\""
                            + (i == (nnmap.getLength() - 1) ? "" : " "));// 不是最后一个属性的话属性之间要留空隙
                }
                System.out.print(">");// 开始标签里的属性全部打印完加上尖括号">"
            }
            if (node.hasChildNodes()) {
                level++;// 有下一级子节点,层次加1,新的层次表示的是这个子节点的层次(递归调用时传给了它)
                NodeList nodelist = node.getChildNodes();
                for (int i = 0; i < nodelist.getLength(); i++) {
                    if (nodelist.item(i).getNodeType() == Node.TEXT_NODE
                            && (!nodelist.item(i).getTextContent()
                            .matches("\\s+"))) {// 用正则选取内容包含非空格的有效字符的文本节点
                        hasTextChild = true;// 该ElementNode的一级子节点是存在有效字符的文本节点
                        System.out.print(nodelist.item(i).getTextContent());// 在开始标签后面添加文本内容
                    } else if (nodelist.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println();
                        listAllChildNodes(nodelist.item(i), level);// level表示该节点处于第几个层次(相应空格)
                    }
                }
                level--;// 遍历完所有的子节点,层次变量随子节点的层数,依次递减,回归到该节点本身的层次
            }
            System.out.print(((hasTextChild) ? "" : "\n" + levelSpace) + "</"
                    + node.getNodeName() + ">");
        }
    }


    *//**
     * 遍历获取系统所有字体
     * @return
     *//*
    public static void getSystemEnglishFonts() {

        String path = Environment.getRootDirectory().getPath() + File.separator + "etc" + File.separator + "fonts.xml";
        List<String> fontsList= parseFontsXMLWithPull(getFontXMLStr(path));
        for (int i = 0; i < fontsList.size(); i++) {
            Log.d(TAG, "getSystemEnglishFonts: "+fontsList.get(i));
        }
    }


    *//**
     * 遍历获取系统所有字体
     * @return
     *//*
    public static void getSystemAllFallBackFonts() {

        String fallBackXMLPath = Environment.getRootDirectory().getPath() + File.separator + "etc" + File.separator + "fallback_fonts.xml";
        String fontsPath = Environment.getRootDirectory().getPath() + File.separator + "fonts";
        List<String> allFallBackFontsList = parseXMLWithPull(getFontXMLStr(fallBackXMLPath));
        List<String> chineseFontsPathList = new ArrayList<>();
        for (int i = 0; i < allFallBackFontsList.size(); i++) {
            String path = fontsPath + File.separator + allFallBackFontsList.get(i);
            long blockSize = getFileSize(new File(path));
            if(blockSize > 1048576){
                chineseFontsPathList.add(path);
            }
        }
        for(int i = 0;i<chineseFontsPathList.size();i++){
            Log.d(TAG, "getSystemAllFonts: "+chineseFontsPathList.get(i));
        }
    }

    private static List<String> parseXMLWithPull(String xmlData) {
        List<String> list = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //文档开始
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<>();
                        break;
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if("file".equals(nodeName)){
                            list.add(xmlPullParser.nextText());
                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {

                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<String> parseFontsXMLWithPull(String xmlData) {
        List<String> list = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //文档开始
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<>();
                        break;
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {

                        if("family".equals(nodeName)){
                            boolean isRightChineseFont = false;
                            int count = xmlPullParser.getAttributeCount();
                            for (int i = 0; i < count; i++) {
                                String name = xmlPullParser.getAttributeName(i);
                                String value = xmlPullParser.getAttributeValue(i);
                                if (name.equals("lang")) {
                                    if("zh-Hans".equals(value)){
                                        isRightChineseFont = true;
                                        list.clear();
                                        list.add(xmlPullParser.nextText());
                                        break;
                                    }
                                }
                            }

                            if(isRightChineseFont){
                                break;
                            }

                        }else if("font".equals(nodeName)){
                            boolean isRightWeight = false;
                            boolean isRightStyle = false;
                            int count = xmlPullParser.getAttributeCount();
                            for (int i = 0; i < count; i++) {
                                String name = xmlPullParser.getAttributeName(i);
                                String value = xmlPullParser.getAttributeValue(i);
                                if("weight".equals(name)){
                                    if("400".equals(value)){
                                        isRightStyle = true;
                                    }
                                }else if("style".equals(name)){
                                    if("normal".equals(value)){
                                        isRightStyle = true;
                                    }
                                }
                            }
                            if((isRightWeight)&&(isRightStyle)){
                                list.add(xmlPullParser.nextText());
                            }
                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {

                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String parseEnglishFontsXMLWithPull(String xmlData) {
        String englishFont = "";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //文档开始
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if("font".equals(nodeName)){
                            if("400".equals(xmlPullParser.getAttributeValue(0))){
                                if("normal".equals(xmlPullParser.getAttributeValue(1))){
                                    englishFont = xmlPullParser.nextText();
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {

                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return englishFont;
    }

    *//**
     * 获得系统字体XML的路径
     *
     * @return
     *//*
    public static String getFontXMLStr(String path) {

        String templateContent = null;
        try {
            File file = new File(path);
            String encoding = "utf-8";
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                templateContent = response.toString();
                read.close();
                bufferedReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return templateContent;
    }

    *//**
     * 获取指定文件大小
     * @param file
     * @return
     * @throws Exception
     *//*
    private static long getFileSize(File file)
    {
        long size = 0;
        if (file.exists()){
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                size = fis.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }*/


    /**
     * 获得系统默认英文字体
     *
     * @return
     */
    public static String getSystemDefaultEnglishFont() {
        String path = "";
        String systemFontFile = Environment.getRootDirectory().getPath() + File.separator + "fonts";
        String englishFontPath = systemFontFile + File.separator + "DroidSans.ttf";//英文字体路径
       /* if (Tools.isFileExists(englishFontPath)) {
            path = englishFontPath;
            return path;
        }
        englishFontPath = systemFontFile + File.separator + "Roboto-Regular.ttf";
        if (Tools.isFileExists(englishFontPath)) {
            path = englishFontPath;
            return path;
        }*/
        //获取系统默认的字体
        path = getSystemEnglishFont();
        return path;
    }


    /**
     * 获取系统默认字体
     * @return
     */
    public static String[] getSystemDefaultFont() {
        String[] chineseFontsPath = null;
        String fallBackXMLPath = Environment.getRootDirectory().getPath() + File.separator + "etc1" + File.separator + "fallback_fonts.xml";
//        String fontsXMLath = Environment.getRootDirectory().getPath() + File.separator + "etc" + File.separator + "fonts.xml";
//        String fontsXMLath = File.separator + "mnt" + File.separator + "sdcard"  + File.separator + "Download"  + File.separator + "mfonts7.xml";
        String fontsXMLath = Environment.getExternalStorageDirectory().getPath() + File.separator + "fonts7.0.xml";
//        String fontsXMLath = Environment.getExternalStorageDirectory().getPath() + File.separator + "mfonts7.0.xml";
//        String fontsXMLath = Environment.getExternalStorageDirectory().getPath() + File.separator + "nocnfonts7.0.xml";
        String fontsPath = Environment.getRootDirectory().getPath() + File.separator + "fonts";

        if(Tools.isFileExists(fallBackXMLPath)){
            List<String> allFallBackFontsList = parseXMLWithPull(getFontXMLStr(fallBackXMLPath));
            List<String> chineseFontsPathList = new ArrayList<>();
            for (int i = 0; i < allFallBackFontsList.size(); i++) {
                String path = fontsPath + File.separator + allFallBackFontsList.get(i);
                long blockSize = getFileSize(new File(path));
                if(blockSize > 1048576){
                    chineseFontsPathList.add(path);
                }
            }
            chineseFontsPath = chineseFontsPathList.toArray(new String[chineseFontsPathList.size()]);
        }else if(Tools.isFileExists(fontsXMLath)){
            Log.i(TAG, "getSystemDefaultFont: begin--"+System.currentTimeMillis());
//            List<String> allFontsList = parseXMLWithDom(fontsXMLath);
            List<String> allFontsList = parseFontsXMLWithPull(getFontXMLStr(fontsXMLath));
            Log.i(TAG, "getSystemDefaultFont: end--"+System.currentTimeMillis());
            List<String> chineseFontsPathList = new ArrayList<>();
            for (int i = 0; i < allFontsList.size(); i++) {
                String path = fontsPath + File.separator + allFontsList.get(i);
                long blockSize = getFileSize(new File(path));
                if(blockSize > 1048576){
                    chineseFontsPathList.add(path);
                }
            }
            chineseFontsPath = chineseFontsPathList.toArray(new String[chineseFontsPathList.size()]);
        }

        return chineseFontsPath;
    }


    /**
     * 获取系统使用的英文字体
     * @return
     */
    public static String getSystemEnglishFont() {
        String fontFile = Environment.getRootDirectory().getPath() + File.separator + "fonts";
        File file = new File(fontFile);
        String systemFontPath = Environment.getRootDirectory().getPath() + File.separator + "etc" + File.separator + "system_fonts.xml";
        String fontsPath = Environment.getRootDirectory().getPath() + File.separator + "etc" + File.separator + "fonts.xml";
        String path = "";
        if(Tools.isFileExists(systemFontPath)){
            path = new File(file,parseSystemFontWithPull(getFontXMLStr(systemFontPath))).getPath();
        }else if(Tools.isFileExists(fontsPath)){
            path = new File(file,parseEnglishFontsXMLWithPull(getFontXMLStr(fontsPath))).getPath();
        }

        return path;
    }


    /**
     * 获取字体XML文件的字符串
     * @param path
     * @return
     */
    public static String getFontXMLStr(String path) {

        String content = null;
        try {
            File file = new File(path);
            String encoding = "utf-8";
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                content = response.toString();
                read.close();
                bufferedReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * 用PULL方式解析XML文件
     * @param xmlData
     * @return
     */
    private static String parseSystemFontWithPull(String xmlData) {
        String systemEnglishFont = "";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //文档开始
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if("file".equals(nodeName)){
                            systemEnglishFont = xmlPullParser.nextText();
                            return systemEnglishFont;
                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {

                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemEnglishFont;
    }

    /**
     * 用PULL方式解析出XML文件的英文字体
     * @param xmlData
     * @return
     */
    private static String parseEnglishFontsXMLWithPull(String xmlData) {
        String englishFont = "";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //文档开始
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if("font".equals(nodeName)){
                            if("400".equals(xmlPullParser.getAttributeValue(0))){
                                if("normal".equals(xmlPullParser.getAttributeValue(1))){
                                    englishFont = xmlPullParser.nextText();
                                    return englishFont;
                                }
                            }
                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {

                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return englishFont;
    }


    /**
     * 通过PULL方式解析XML
     * @param xmlData
     * @return
     */
    private static List<String> parseXMLWithPull(String xmlData) {
        List<String> list = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //文档开始
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<>();
                        break;
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if("file".equals(nodeName)){
                            list.add(xmlPullParser.nextText());
                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {

                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取指定文件大小
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file)
    {
        long size = 0;
        if (file.exists()){
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                size = fis.available();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    /**
     * 通过DOM的方式解析XML
     * @param path
     * @return
     */
    private static List<String> parseXMLWithDom(String path) {
        List<String> list = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = db.parse(new FileInputStream(new File(path)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XPathFactory factory = XPathFactory.newInstance();

        XPath xpath = factory.newXPath();

        String expression;
        NodeList nodeList = null;

        expression = "familyset/family[@lang=\"zh-Hans\"]/font";

        try {
            nodeList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        if (nodeList.getLength()==0) {
            String expression2 = "familyset/family[not(@lang)]/font[@weight=\"400\"][@style=\"normal\"]";
            NodeList nodeList2 = null;
            try {
                nodeList2 = (NodeList) xpath.evaluate(expression2, doc, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < nodeList2.getLength(); j++) {
                list.add(nodeList2.item(j).getTextContent());
            }
        } else {
            int length = nodeList.getLength();
            if(length == 1){
                list.add(nodeList.item(0).getChildNodes().item(0).getTextContent());
            }else{
                for (int i = 0; i < nodeList.getLength(); i++) {
                    NamedNodeMap namedNodeMap = nodeList.item(i).getAttributes();
                    if((namedNodeMap.getNamedItem("weight").getNodeValue().equals("400"))&&(namedNodeMap.getNamedItem("style").getNodeValue().equals("normal"))){
                        list.add(nodeList.item(i).getChildNodes().item(0).getTextContent());
                    }
                }
            }
        }
        return list;
    }

    private static List<String> parseFontsXMLWithPull(String xmlData) {
        List<String> list = null;
        String chineseFont = "";
        boolean isRightFont = false;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //文档开始
                    case XmlPullParser.START_DOCUMENT:
                        list = new ArrayList<>();
                        break;
                    // 开始解析某个结点
                    case XmlPullParser.START_TAG: {

                        if("family".equals(nodeName)){
                            int count = xmlPullParser.getAttributeCount();
                            for (int i = 0; i < count; i++) {
                                String name = xmlPullParser.getAttributeName(i);
                                String value = xmlPullParser.getAttributeValue(i);
                                if ((name.equals("lang"))&&("zh-Hans".equals(value))) {
                                    isRightFont = true;
                                    break;
                                }
                            }

                        }else if("font".equals(nodeName)){

                            if (isRightFont) {
                                boolean isRightWeight = false;
                                boolean isRightStyle = false;
                                int count = xmlPullParser.getAttributeCount();
                                for (int i = 0; i < count; i++) {
                                    String name = xmlPullParser.getAttributeName(i);
                                    String value = xmlPullParser.getAttributeValue(i);
                                    if("weight".equals(name)){
                                        if("400".equals(value)){
                                            isRightWeight = true;
                                        }
                                    }else if("style".equals(name)){
                                        if("normal".equals(value)){
                                            isRightStyle = true;
                                        }
                                    }
                                }
                                if((isRightWeight)&&(isRightStyle)){
                                    chineseFont = xmlPullParser.nextText();
                                }
                            }else{

                            }

                        }
                        break;
                    }
                    // 完成解析某个结点
                    case XmlPullParser.END_TAG: {
                        isRightFont = false;
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
