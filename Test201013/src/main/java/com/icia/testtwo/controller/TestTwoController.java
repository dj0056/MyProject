package com.icia.testtwo.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.icia.testtwo.api.KakaoJoinApi;
import com.icia.testtwo.api.KakaoLoginApi;
import com.icia.testtwo.api.NaverJoinApi;
import com.icia.testtwo.api.NaverLoginApi;
import com.icia.testtwo.dto.TestTwoDTO;
import com.icia.testtwo.service.TestTwoService;

@Controller
public class TestTwoController {
	
	@Autowired
	private TestTwoService testtwoService;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession	session;
	
	@Autowired
	private KakaoJoinApi kakaoJoinApi;
	
	@Autowired
	private KakaoLoginApi kakaoLoginApi;
	
	@Autowired
	private NaverJoinApi naverJoinApi;
	
	@Autowired
	private NaverLoginApi naverLoginApi;
	
	@RequestMapping(value="/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/memberjoinform")
	public String memberJoinForm() {
		return "membert/MemberJoin";
	}
	
	@RequestMapping(value="/memberloginform")
	public String memberLoginForm() {
		return "membert/MemberLogin";
	}
	
	@RequestMapping(value="/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute TestTwoDTO testtwo) throws IllegalStateException, IOException {
		mav = testtwoService.memberJoin(testtwo);
		return mav;
	}
	
	@RequestMapping(value="/memberlogin")
	public ModelAndView memberLogin(@ModelAttribute TestTwoDTO testtwo) {
		mav = testtwoService.memberLogin(testtwo);
		return mav;
	}
	
	@RequestMapping(value="/memberupdate")
	public String memberUpdate() {
		return "membert/MemberUpdate";
	}
	
	@RequestMapping(value="/memberupdateprocess")
	public ModelAndView memberUpdateProcess(@ModelAttribute TestTwoDTO testtwo) throws IllegalStateException, IOException {
		mav = testtwoService.memberUpdateProcess(testtwo);
		return mav;
	}
	
	@RequestMapping(value="/memberlist")
	public ModelAndView memberList() {
		mav = testtwoService.memberList();
		return mav;
	}
	
	@RequestMapping(value="/memberview")
	public ModelAndView memberView(@RequestParam("tid") String tid) {
		mav = testtwoService.memberView(tid);
		return mav;
	}
	
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody TestTwoDTO memberViewAjax(@RequestParam("tid") String tid) {
		System.out.println("전달받은 값"+tid);
		TestTwoDTO memberView = testtwoService.memberViewAjax(tid);
		return memberView;
	}
	
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberDelete(@RequestParam("tid") String tid) {
		mav = testtwoService.memberDelete(tid);
		return mav;
	}
	
	@RequestMapping(value="/memberlogout")
	public String memberLogout() {
		session.removeAttribute("loginId");
		return "membert/MemberLogin";
	}
	
	@RequestMapping(value="/kakaojoin")
	public ModelAndView kakaoJoin(HttpSession session) {
		String kakaoUrl = kakaoJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("membert/KakaoLogin");
		return mav;
	}
	
	// 카카오 서버 인증 통과 후 처리
	@RequestMapping(value="/kakaojoinok")
	public ModelAndView kakaoJoinOK(@RequestParam("code") String code,
			HttpSession session) {
		JsonNode token = kakaoJoinApi.getAccessToken(code);
		JsonNode profile = kakaoJoinApi.getKakaoUserInfo(token.path("access_token"));
		System.out.println("profile"+profile);
		// profile에 담긴 id 값을 가지고 MemberJoin.jsp로 이동
		String kakaoId = profile.get("id").asText();
		mav = new ModelAndView();
		mav.addObject("kakaoId", kakaoId);
		mav.setViewName("membert/MemberJoin");
		return mav;
	}
	
	// 카카오 로그인
	@RequestMapping(value="/kakaologin")
	public ModelAndView kakaoLogin(HttpSession session) {
		String kakaoUrl = kakaoLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("kakaoUrl", kakaoUrl);
		mav.setViewName("membert/KakaoLogin");
		return mav;
	}
	
	@RequestMapping(value="/kakaologinok")
	public ModelAndView kakaoLoginOK(@RequestParam("code") String code) {
		JsonNode token = kakaoLoginApi.getAccessToken(code);
		JsonNode profile = kakaoLoginApi.getKakaoUserInfo(token.path("access_token"));
		
		mav = testtwoService.kakaoLogin(profile);
		return mav;
	}
	
	@RequestMapping(value="/naverjoin")
	public ModelAndView naverJoin(HttpSession session) {
		String naverUrl = naverJoinApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("membert/NaverLogin");
		return mav;
	}
	
	@RequestMapping(value="/naverjoinok")
	public ModelAndView naverJoinOK(@RequestParam("state") String state, @RequestParam("code") String code, HttpSession session) throws IOException, ParseException {
		mav = new ModelAndView();
		OAuth2AccessToken oauthToken = naverJoinApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(profile);
		
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String) userInfo.get("id");
		
		mav.addObject("naverId", naverId);
		mav.setViewName("membert/MemberJoin");
		
		return mav;
	}
	
	@RequestMapping(value="/naverlogin")
	public ModelAndView naverLogin(HttpSession session) {
		String naverUrl = naverLoginApi.getAuthorizationUrl(session);
		mav = new ModelAndView();
		mav.addObject("naverUrl", naverUrl);
		mav.setViewName("membert/NaverLogin");
		return mav;
	}
	
	@RequestMapping(value="/naverloginok")
	public ModelAndView naverLoginOK(@RequestParam("state") String state, @RequestParam("code") String code, HttpSession session) throws IOException, ParseException {
		OAuth2AccessToken oauthToken = naverLoginApi.getAccessToken(session, code, state);
		String profile = naverJoinApi.getUserProfile(oauthToken);
		mav = testtwoService.naverLogin(profile);
		return mav;
	}
	
		@RequestMapping(value="/idoverlap")
		// @ResponsBody는 text로 리턴값 주기위한 어노텐션
		// 안하면 주소값으로 보내게 됨.
		// ajax의 result로 보냄.
		public @ResponseBody String idOverlap(@RequestParam("tid") String tid) {
			String resultMsg = testtwoService.idOverlap(tid);
			
			return resultMsg;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
