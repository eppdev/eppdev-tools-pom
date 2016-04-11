/**
 * # LineTemplate.java -- (2014年12月16日)
 * 作者：郝金隆
 * 联系方式：haojinlong@189.cn
 */
package cn.eppdev.tools.commons.template;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.eppdev.tools.commons.file.TextFileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 郝金隆
 * @since 2014年12月16日
 */
public class LineTemplate {
	static Logger logger = LoggerFactory.getLogger(LineTemplate.class);
	static final String EOL = "\n";

	// 每一行的内容
	private List<String> lineList = null;

	/**
	 * @param fileName
	 * @throws IOException
	 */
	public LineTemplate(String fileName) throws IOException {
		this(new File(fileName), "UTF-8");
	}

	/**
	 * @param fileName
	 *            要读取的文件绝对地址
	 * @param encoding
	 *            文件字字符编码
	 * @throws IOException
	 *             读写异常
	 */
	public LineTemplate(String fileName, String encoding) throws IOException {
		this(new File(fileName), encoding);
	}

	/**
	 * @param file
	 *            已经存在要分章节进行替换的文件
	 * @throws IOException
	 *             读取异常
	 */
	public LineTemplate(File file) throws IOException {
		this(new FileInputStream(file), "UTF-8");
	}

	/**
	 * @param file
	 *            要读取的文件
	 * @param encoding
	 *            文件字符编码
	 * @throws IOException
	 *             读取异常
	 */
	public LineTemplate(File file, String encoding) throws IOException {
		this(new FileInputStream(file), encoding);
	}

	/**
	 * 按照UTF-8作为默认编码进行模板的初始化
	 * 
	 * @param is
	 *            模板的输入六
	 * @throws IOException
	 *             读取异常
	 */
	public LineTemplate(InputStream is) throws IOException {
		this(is, "UTF-8");
	}

	/**
	 * @param is
	 *            输入流
	 * @param encoding
	 *            编码格式
	 * @throws IOException
	 */
	public LineTemplate(InputStream is, String encoding) throws IOException {
		this(new InputStreamReader(is, encoding));
	}

	/**
	 * @param reader
	 *            reader属性
	 * @throws IOException
	 */
	public LineTemplate(Reader reader) throws IOException {
		this.lineList = TextFileUtils.readToList(reader);
		logger.debug("the value of init: {}", this.toString());
	}

	/**
	 * 在某行前插入内容
	 * 
	 * @param str
	 * @param content
	 * @return
	 */
	public LineTemplate addBefore(String str, String content) {
		List<String> newList = new ArrayList<String>();
		List<String> insertList = init(content);
		for (String line : lineList) {
			if (line.contains(str)) {
				for (String insertLine : insertList) {
					newList.add(insertLine);
				}
			}
			newList.add(line);
		}
		this.lineList = newList;
		return this;
	}

	/**
	 * 在某行后插入内容
	 * 
	 * @param str
	 * @param content
	 * @return
	 */
	public LineTemplate addAfter(String str, String content) {
		List<String> newList = new ArrayList<String>();
		List<String> insertList = init(content);
		for (String line : lineList) {
			newList.add(line);
			if (line.contains(str)) {
				for (String insertLine : insertList) {
					newList.add(insertLine);
				}
			}
		}
		this.lineList = newList;
		return this;
	}

	/**
	 * 将strStart和strEnd中间的内容替换表
	 * 
	 * @param strStart
	 * @param strEnd
	 * @param content
	 * @return
	 */
	public LineTemplate replace(String strStart, String strEnd, String content) {
		List<String> newList = new ArrayList<String>();
		List<String> replaceList = init(content);
		boolean replace = false; // 循环内容是否为要替换内容
		for (String line : lineList) {
			if (!replace) {
				newList.add(line);
				if (line.contains(strStart)) {
					replace = true;
					for (String replaceLine : replaceList) {
						newList.add(replaceLine);
					}
				}
			} else {
				if (line.contains(strEnd)) {
					newList.add(line);
					replace = false;
				}
			}
		}
		this.lineList = newList;
		return this;
	}

	/**
	 * 判断是否包含此部分内容
	 * 
	 * @param content
	 * @return
	 */
	public boolean contains(String content) {
		for (String str : lineList) {
			if (str.contains(content.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 转换为List
	 * 
	 * @param content
	 * @return
	 */
	private List<String> init(String content) {
		try {
			return TextFileUtils.readToList(new ByteArrayInputStream(content
					.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (lineList != null) {
			for (String str : lineList) {
				sb.append(str + EOL);
			}
		}
		return sb.toString();
	}

}
