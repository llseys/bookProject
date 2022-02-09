package org.company.my.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.company.my.dao.BookDAO;
import org.company.my.dto.Book;
import org.company.my.dto.Page;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDAO bookDAO;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Book> serch(Page page) throws Exception { //책 검색하기
		
		List<Book> blist = null;

        StringBuilder urlBuilder = new StringBuilder("http://book.interpark.com/api/search.api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=95EF0470BA621360BB719C4B6F9704DD638C88ED868527DAF55EAD5B7D8B6BA3"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(page.getBname(), "UTF-8")); //검색어
        urlBuilder.append("&" + URLEncoder.encode("output","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("maxResults","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); 
        URL url = new URL(urlBuilder.toString());
        System.out.println("url : " + urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println("sb : " + sb.toString());
		
		//------------------------------------------------------------------------------------------
        
        JSONParser jsonParser = new JSONParser();
        JSONObject object = (JSONObject)jsonParser.parse(sb.toString());
        JSONArray array = (JSONArray)object.get("item");

        System.out.println("데이터갯수 : " + array.size() +"개");  
		
        Book book = new Book();

		for(int i=0;i<array.size();i++) {
			System.out.println(i + "--------------------------");
			object = (JSONObject)array.get(i);

			book.setIsbn((String)object.get("isbn"));
			book.setTitle((String)object.get("title"));
			book.setDescription((String)object.get("description"));
			book.setCategory((String)object.get("categoryName"));
			book.setAuthor((String)object.get("author"));
			book.setPublisher((String)object.get("publisher"));
			book.setPubdate((String)object.get("pubDate"));
			book.setSmallfileurl((String)object.get("coverSmallUrl"));
			book.setBigfileurl((String)object.get("coverLargeUrl"));
			book.setBookKind("s");
			
			Object obj = object.get("customerReviewRank");
			if (obj instanceof Double) {
				book.setReviewrank((Double)object.get("customerReviewRank"));				
			}else if (obj instanceof Long) {
				book.setReviewrank((Long)object.get("customerReviewRank"));
			}

			System.out.println("파싱북" + book);

			sqlSession.delete("org.company.my.BookMapper.delete", (String)object.get("isbn"));
			sqlSession.insert("org.company.my.BookMapper.insert", book);
			;
		}


		return bookDAO.selectList(page);
	}

	//--------------------------------------------------------------------------------------------------------------------------
	@Override
	public List<Book> selectList(Page page) {
		return bookDAO.selectList(page);
	}

	@Override
	public Book selectOne(String isbn) {
		return bookDAO.selectOne(isbn);
	}
	
	
	@Transactional// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public List<Book> bestbookList(Page page) throws Exception {
		
		List<Book> blist = null;
		
    	int cnt=0;

        StringBuilder urlBuilder = new StringBuilder("http://book.interpark.com/api/bestSeller.api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=95EF0470BA621360BB719C4B6F9704DD638C88ED868527DAF55EAD5B7D8B6BA3"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("categoryId","UTF-8") + "=" + URLEncoder.encode("102", "UTF-8")); //검색어
        urlBuilder.append("&" + URLEncoder.encode("output","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("maxResults","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); 
//	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//	        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode("20200310", "UTF-8")); /*검색할 생성일 범위의 시작*/
//	        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode("20200315", "UTF-8")); /*검색할 생성일 범위의 종료*/
        URL url = new URL(urlBuilder.toString());
        System.out.println("url : " + urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println("sb : " + sb.toString());
		
		//------------------------------------------------------------------------------------------
        
        JSONParser jsonParser = new JSONParser();
        JSONObject object = (JSONObject)jsonParser.parse(sb.toString());
        JSONArray array = (JSONArray)object.get("item");

        System.out.println("데이터갯수 : " + array.size() +"개");  
		
        Book book = new Book();

		for(int i=0;i<array.size();i++) {
			System.out.println(i + "--------------------------");
			object = (JSONObject)array.get(i);

			book.setIsbn((String)object.get("isbn"));
			book.setTitle((String)object.get("title"));
			book.setDescription((String)object.get("description"));
			book.setCategory((String)object.get("categoryName"));
			book.setAuthor((String)object.get("author"));
			book.setPublisher((String)object.get("publisher"));
			book.setPubdate((String)object.get("pubDate"));
			book.setSmallfileurl((String)object.get("coverSmallUrl"));
			book.setBigfileurl((String)object.get("coverLargeUrl"));
			book.setBookKind("b");
			
			Object obj = object.get("customerReviewRank");
			if (obj instanceof Double) {
				book.setReviewrank((Double)object.get("customerReviewRank"));				
			}else if (obj instanceof Long) {
				book.setReviewrank((Long)object.get("customerReviewRank"));
			}

			System.out.println("파싱북" + book);

			sqlSession.delete("org.company.my.BookMapper.delete", (String)object.get("isbn"));
			cnt += sqlSession.insert("org.company.my.BookMapper.insert", book);
			System.out.println(cnt+"건 추가");	
		}
	// page 처리 값 구하기
		
		int curPage = page.getCurPage(); //현재페이지
		int perPage = page.getPerPage(); //한페이지당 게시물수
		int perBlock = page.getPerBlock(); // 화면 페이지 수
		
		// 1) 전체게시물수
		int totcnt = bookDAO.selectTotcnt(page);
		
		// 2) 전체페이지수
		int totPage = totcnt / perPage; //전체게시물 / 한페이지당 게시물수
		
		if(totcnt % perPage !=0){ //나머지 있는경우
			totPage = totPage+1;
		}
		
		page.setTotPage(totPage);
		
		// 3) 시작번호
		int startNum = (curPage -1)*perPage+1; // 오라클을 1번부터 시작
//		int startNum = (curPage -1)*perPage; //mysql은 0번부터시작해야함
		page.setStartNum(startNum);
		// 4) 끝번호
		int endNum = startNum + perPage -1; //시작번호 + 한페이지당 게시물 수 -1
		page.setEndNum(endNum);
		// 5) 시작페이지
		int startPage = curPage -((curPage-1)%perBlock);  //현재페이지 - ((현재페이지-1)%한 화면당 페이지수)
		page.setStartPage(startPage);
		// 6) 끝페이지
		int endPage = startPage + perBlock -1; // 시작페이지 + 한 화면당 페이지 수 -1
		if(endPage>totPage) endPage = totPage; // 맨끝 페이지는 전체페이지수보다 클수없다
		page.setEndPage(endPage);

		return bookDAO.bestSelectList(page);
	}
	
	// 신간도서
	@Transactional// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public List<Book> newbookList(Page page) throws Exception {
		
		List<Book> blist = null;
		
    	int cnt=0;

        StringBuilder urlBuilder = new StringBuilder("http://book.interpark.com/api/newBook.api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=95EF0470BA621360BB719C4B6F9704DD638C88ED868527DAF55EAD5B7D8B6BA3"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("categoryId","UTF-8") + "=" + URLEncoder.encode("102", "UTF-8")); //검색어
        urlBuilder.append("&" + URLEncoder.encode("output","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("maxResults","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); 
//	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//	        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode("20200310", "UTF-8")); /*검색할 생성일 범위의 시작*/
//	        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode("20200315", "UTF-8")); /*검색할 생성일 범위의 종료*/
        URL url = new URL(urlBuilder.toString());
        System.out.println("url : " + urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println("sb : " + sb.toString());
		
		//------------------------------------------------------------------------------------------
        
        JSONParser jsonParser = new JSONParser();
        JSONObject object = (JSONObject)jsonParser.parse(sb.toString());
        JSONArray array = (JSONArray)object.get("item");

        System.out.println("데이터갯수 : " + array.size() +"개");  
		
        Book book = new Book();

		for(int i=0;i<array.size();i++) {
			System.out.println(i + "--------------------------");
			object = (JSONObject)array.get(i);

			book.setIsbn((String)object.get("isbn"));
			book.setTitle((String)object.get("title"));
			book.setDescription((String)object.get("description"));
			book.setCategory((String)object.get("categoryName"));
			book.setAuthor((String)object.get("author"));
			book.setPublisher((String)object.get("publisher"));
			book.setPubdate((String)object.get("pubDate"));
			book.setSmallfileurl((String)object.get("coverSmallUrl"));
			book.setBigfileurl((String)object.get("coverLargeUrl"));
			book.setBookKind("n");
			
			Object obj = object.get("customerReviewRank");
			if (obj instanceof Double) {
				book.setReviewrank((Double)object.get("customerReviewRank"));				
			}else if (obj instanceof Long) {
				book.setReviewrank((Long)object.get("customerReviewRank"));
			}

			System.out.println("파싱북" + book);

			sqlSession.delete("org.company.my.BookMapper.delete", (String)object.get("isbn"));
			cnt += sqlSession.insert("org.company.my.BookMapper.insert", book);
			System.out.println(cnt+"건 추가");	
		}
		
	// page 처리 값 구하기
		int curPage = page.getCurPage(); //현재페이지
		int perPage = page.getPerPage(); //한페이지당 게시물수
		int perBlock = page.getPerBlock(); // 화면 페이지 수
		
		// 1) 전체게시물수
		int totcnt = bookDAO.selectTotcnt(page);
		
		// 2) 전체페이지수
		int totPage = totcnt / perPage; //전체게시물 / 한페이지당 게시물수
		
		if(totcnt % perPage !=0){ //나머지 있는경우
			totPage = totPage+1;
		}
		
		page.setTotPage(totPage);
		
		// 3) 시작번호
		int startNum = (curPage -1)*perPage+1; // 오라클을 1번부터 시작
//		int startNum = (curPage -1)*perPage; //mysql은 0번부터시작해야함
		page.setStartNum(startNum);
		// 4) 끝번호
		int endNum = startNum + perPage -1; //시작번호 + 한페이지당 게시물 수 -1
		page.setEndNum(endNum);
		// 5) 시작페이지
		int startPage = curPage -((curPage-1)%perBlock);  //현재페이지 - ((현재페이지-1)%한 화면당 페이지수)
		page.setStartPage(startPage);
		// 6) 끝페이지
		int endPage = startPage + perBlock -1; // 시작페이지 + 한 화면당 페이지 수 -1
		if(endPage>totPage) endPage = totPage; // 맨끝 페이지는 전체페이지수보다 클수없다
		page.setEndPage(endPage);

		return bookDAO.newSelectList(page);
	}
	

}
