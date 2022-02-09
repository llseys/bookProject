package org.company.my.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.company.my.dao.MemberDAO;
import org.company.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverLoginServiceImpl implements NaverLoginService{

	@Autowired
	private MemberDAO memberDAO;
	
	
	// 네이버 간편로그인버튼 url생성---------------------------------------------------------------------------------
	@Override
	public Map<String, String> getApiUrl() throws Exception {
		 
	    String clientId = "Zon0Qk0IaX48NCCMX_yg";//애플리케이션 클라이언트 아이디값";
//	    로컬에서 테스트
//	    String redirectURI = URLEncoder.encode("http://localhost:8081/my/naver_callback", "UTF-8"); //콜백주소
//	    //실서버에서 테스트 
	    String redirectURI = URLEncoder.encode("http://115.85.182.210:8080/myapp/naver_callback", "UTF-8"); //콜백주소
	    
	    SecureRandom random = new SecureRandom(); 
	    String state = new BigInteger(130, random).toString(); //인증값을 생성(보안)
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
		
	    // 네이버 로그인 
	    // 넘거야할것 : apiURL , state(인증값)를 반환,  
	    Map<String, String> result = new HashMap<>();
	    result.put("apiURL", apiURL); //링크주소
	    result.put("state", state);
	    
		return result;
	}
	
	// 1)토큰을 얻고 + 2)개인정보얻기------------------------------------------------------------------------------------
	@Override
	public Map<String, String> getTokenUserInfo(String code, String state) throws Exception {
		
		String accessToken = getToken(code, state); //1)토큰을얻기
		Map<String, String> result =  getUserInfo(accessToken); //2)네이버의 개인정보얻기
		
		return result;
	}

	// 토큰을얻기-----------------------------------------------------------------------------------------------------
	// 토큰 : 네이버에서 정보를 주기위한 인증
	
	public String getToken(String code, String state) throws Exception {
		  String clientId = "Zon0Qk0IaX48NCCMX_yg";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "1_YMVW5xe7";//애플리케이션 클라이언트 시크릿값";
//		    String code = request.getParameter("code");
//		    String state = request.getParameter("state");
//		    String redirectURI = URLEncoder.encode("http://localhost:8081/my/naver_callback", "UTF-8");
		    String redirectURI = URLEncoder.encode("http://115.85.182.210:8080/myapp/naver_callback", "UTF-8");
		    String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String accessToken = "";
		    String refreshToken = "";
		    System.out.println("apiURL="+apiURL);
		    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
//		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) { //성공일때 파싱 
		    	System.out.println(res.toString());
		    	
			    // json파싱 
			    JSONObject object = (JSONObject) new JSONParser().parse(res.toString());
			    accessToken = (String)object.get("access_token");
		    	
		      }        
		    } catch (Exception e) {
		    	System.out.println(e);
		    }

		return accessToken;
	}
	
	// 개인정보얻기-----------------------------------------------------------------------------------------------------
	// 매개변수 : 네이버 로그인 접근토큰
	
	public Map<String, String> getUserInfo(String token) throws Exception {
		
        String header = "Bearer " + token; // Bearer 다음에 공백 추가


        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);      


        System.out.println(responseBody); 
        
        // responseBody : json형식
        // json 파싱
        
        JSONObject object = (JSONObject) new JSONParser().parse(responseBody);
        object = (JSONObject) object.get("response"); // resopnse {} 추출
        String email = (String)object.get("email"); // email 추출
        
        // 리턴 맵
        Map<String, String> result = new HashMap<>();        
        result.put("email", email);
        
		return result;
	}
	
	//*******************************************************************************************************

	 private String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }

	//----------------------------------------------------------------------------------------------------------
    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

	 //----------------------------------------------------------------------------------------------------------
    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
    
    //db에 회원저장-------------------------------------------------------------------------------------------------------------------------
	@Override
	public Map<String, Object> insert(String userid) { 
		Map<String, Object> result = new HashMap<>();
		// code,msg
		// 0: 네이버 간편가입 완료
		// 1: 이미 네이버 간편가입된 회원
		// 2: 일반가입된 회원

		//1)회원조회
		Member dbMember = memberDAO.selectOne(userid);
		
		//2)회원가입이 된 회원 체크 (유니크에러 방지)
		if(dbMember!=null) { //회원가입이 된 사람
			if(dbMember.getSimplejoin().equals("1")) { //이미 네이버 간편가입된 회원
				result.put("code", 1);
				result.put("msg", "이미 네이버 간편가입된 회원입니다");
			}else if(dbMember.getSimplejoin().equals("0")) {
				result.put("code", 2);
				result.put("msg", "일반가입된 회원입니다");	
			}
			return result;
		}
			
		//3) 회원가입	
		
		Member member = new Member();
		member.setUserid(userid);
		member.setNicname(userid);
		member.setUsername(userid);
		member.setEmail(userid);
		member.setTel(" ");
		
		member.setUserpw(userid+"123"); //not null이기에 
		member.setSimplejoin("1"); //네이버 간편가입
		int cnt = memberDAO.insert(member);
		
		result.put("code", 0);
		result.put("msg", "네이버 로그인 가입완료");
		
		return result;
	}

}