package com.atxiaojie.mybatis.factory;

import com.atxiaojie.mybatis.configuration.Configuration;
import com.atxiaojie.mybatis.configuration.MappedStatement;
import com.atxiaojie.mybatis.sqlsession.DefaultSqlSession;
import com.atxiaojie.mybatis.sqlsession.SqlSession;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName: DefaultSqlSessionFactory
 * @Description: SqlSessionFactory
 * @author: zhouxiaojie
 * @date: 2021/11/3 22:19
 * @Version: V1.0.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    //希望configuration是单例的并且唯一的
    private final Configuration configuration = new Configuration();

    //XML文件存放的位置
    private static final String MAPPER_CONFIG_LOCATION = "mappers";

    //数据库信息存放的位置
    private static final String DB_CONFIG_FILE = "mybatis.properties";

    public DefaultSqlSessionFactory(){
        loadDBInfo();
        loadMapperInfo();
    }

    private void loadDBInfo() {
        InputStream db = this.getClass().getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
        Properties p = new Properties();
        try {
            p.load(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将配置信息写入Configuration对象
        configuration.setJdbcDriver(p.getProperty("jdbc_driver"));
        configuration.setJdbcUrl(p.getProperty("jdbc_url"));
        configuration.setJdbcUsername(p.getProperty("jdbc_username"));
        configuration.setJdbcPassword(p.getProperty("jdbc_password"));
    }

    private void loadMapperInfo() {
        URL resource = this.getClass().getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
        File mappers = new File(resource.getFile());
        //读取文件夹下面的文件信息
        if(mappers.isDirectory()){
            File[] files = mappers.listFiles();
            for(File file : files){
                loadMapperInfo(file);
            }
        }
    }

    private void loadMapperInfo(File file) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取根节点元素对象<mapper>
        Element element = document.getRootElement();
        //获取命名空间namespace
        String namespace = element.attribute("namespace").getValue();
        //获取select,insert,update,delete子节点列表
        List<Element> selects = element.elements("select");
        List<Element> inserts = element.elements("insert");
        List<Element> updates = element.elements("update");
        List<Element> deletes = element.elements("delete");
        List<Element> allElementList = new ArrayList<Element>();
        allElementList.addAll(selects);
        allElementList.addAll(inserts);
        allElementList.addAll(updates);
        allElementList.addAll(deletes);
        for(Element ele : allElementList){
            MappedStatement mappedStatement = new MappedStatement();
            String id = ele.attribute("id").getData().toString();
            String resultType = ele.attribute("resultType").getData().toString();
            String sql = ele.getData().toString();
            mappedStatement.setId(namespace + "." + id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sql);
            configuration.getMappedStatement().put(namespace + "." + id, mappedStatement);
        }
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
