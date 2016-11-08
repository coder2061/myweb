package com.web.tools.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 索引工具类 Lucene的使用主要体现在两个步骤： 
 * 1 创建索引，通过IndexWriter对不同的文件进行索引的创建，并将其保存在索引相关文件存储的位置中。 
 * 2 通过索引查寻关键字相关文档。
 * 
 * @author jiangyf
 */
public class LuceneIndex {
	private static final Logger log = LoggerFactory
			.getLogger(LuceneIndex.class);
	private static String indexPath = "D://lucene/";
	private static Directory dir = null;

	/**
	 * Directory 索引的存储位置 两种实现： 
	 * FSDirectory，表示存储在文件系统中的索引的位置；
	 * RAMDirectory，表示存储在内存当中的索引的位置
	 */
	static {
		try {
			dir = FSDirectory.open(Paths.get(indexPath));
		} catch (IOException e) {
			e.printStackTrace();
			log.info("Initialization error for index directory path");
		}
	}

	/**
	 * 获取IndexWriter实例
	 * 
	 * @return IndexWriter
	 * @throws Exception
	 */
	public static IndexWriter getWriter() throws Exception {
		// 建立索引之前必须对文档内容进行分词处理
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		return writer;
	}

	/**
	 * 封装文档对象
	 * 
	 * @param doc
	 * @param obj
	 * @return Document
	 * @throws Exception
	 */
	public static Document setDocument(Document doc, Object obj)
			throws Exception {
		java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
		for (java.lang.reflect.Field field : fields) {
			field.setAccessible(true);
			/**
			 * yes表示将数据存进索引，如果搜索结果中需要将记录显示出来就要存进去；
			 * 如果搜索结果只是显示标题之类的就可以不用存，而且内容过长不建议存进去
			 * 使用TextField类是可以用于搜索的。
			 */
			doc.add(new TextField(field.getName(), field.get(obj).toString(),
					Field.Store.YES));
		}
		return doc;
	}

	/**
	 * 添加索引
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public static void addIndex(Object obj) throws Exception {
		// 文档对象实例
		Document doc = new Document();
		// 封装文档对象
		doc = setDocument(doc, obj);
		// 创建索引并添加文档对象到索引中
		IndexWriter writer = getWriter();
		writer.addDocument(doc);
		writer.close();
	}

	/**
	 * 更新索引
	 * 
	 * @param obj
	 * @param key
	 *            字段名
	 * @param value
	 *            字段值
	 * @throws Exception
	 */
	public static void updateIndex(Object obj, String key, String value)
			throws Exception {
		Document doc = new Document();
		doc = setDocument(doc, obj);
		IndexWriter writer = getWriter();
		writer.updateDocument(new Term(key, value), doc);
		writer.close();
	}

	/**
	 * 删除指定的索引
	 * 
	 * @param key
	 *            字段名
	 * @param value
	 *            字段值
	 * @throws Exception
	 */
	public static void deleteIndex(String key, String value) throws Exception {
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term(key, value));
		// 强制删除
		writer.forceMergeDeletes();
		writer.commit();
		writer.close();
	}

	/**
	 * 根据需要来对搜索关键字自定义高亮样式
	 * 
	 * @param query
	 */
	public static Highlighter setHighlighter(Query query) {
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(
				"<b><font color='red'>", "</font></b>");
		Highlighter highlighter = new Highlighter(formatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		return highlighter;
	}

	public static void test() throws Exception {
		// 标准词法分析器
		Analyzer analyzer = new StandardAnalyzer();
		// 保存索引至内存
		Directory directory = new RAMDirectory();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(directory, config);
		Document doc = new Document();
		String text = "This is the text to be indexed.";
		doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
		writer.addDocument(doc);
		writer.close();

		// 读取索引
		DirectoryReader reader = DirectoryReader.open(directory);
		// 创建搜索器
		IndexSearcher searcher = new IndexSearcher(reader);
		// 调用查询方法
		QueryParser parser = new QueryParser("fieldname", analyzer);
		Query query = parser.parse("text");
		ScoreDoc[] hits = searcher.search(query, 1000, null).scoreDocs;
		Assert.assertEquals(1, hits.length);
		// 遍历查询结果
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = searcher.doc(hits[i].doc);
			Assert.assertEquals("This is the text to be indexed.",
					hitDoc.get("fieldname"));
		}
		// 关闭查询器
		reader.close();
		directory.close();
	}

}
