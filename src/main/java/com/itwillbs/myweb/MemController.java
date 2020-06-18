package com.itwillbs.myweb;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberBean;
import com.itwillbs.service.MemService;
import com.itwillbs.service.MemServiceImpl;

@Controller
public class MemController {
	// MemService memService = new MemServiceImpl();
	private MemService memService;
	@Inject
	public void setMemService(MemService memService) {
		this.memService = memService;
	}
	
//	MemController, HomeController ���� �ּ� /test ������ ���� �߻�
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public String test() {
//		
//		return "test";
//	}
	

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		System.out.println("ȸ������");
		return "insertForm";
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPost(MemberBean mb) {
		System.out.println("ID: "+mb.getId()+",PASS: "+mb.getPass()+",NAME: "+mb.getName());
		System.out.println("ȸ������ ó��");
		// 1. ��ü ���� => 3���� ���� �ʿ�
//		MemServiceImpl memServiceImpl = new MemServiceImpl();
//		memServiceImpl.insert();
		// 2. �θ�=�ڽ� ��ü ���� => 1�� ���� �ʿ� 
//		MemService memService = new MemServiceImpl();
//		memService.insert();
		// 3. �θ�=�ڽ��� ��� �Ǵ� �� <= ���������� xml �ڽ� ��ü���� �ʿ�� �ϴ� ���� ��
		//    ���������� ��ü �����ϴ� ��� : �������� ���� (DI)
		memService.insert(mb);
		

		return "redirect:login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("�α���");
		return "loginForm";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(MemberBean mb, HttpSession session) {
		System.out.println(mb.getId());
		System.out.println(mb.getPass());
		System.out.println("�α��� ó��");
		// ���ϰ� MemberBean mb2 = userCheck(mb)
		MemberBean mb2 = memService.userCheck(mb);
		// ���̵� ��й�ȣ ��ġ�ϸ� mb2 ȸ������ ��� ��������
		if(mb2!=null) {
			// ���ǰ� ����
			session.setAttribute("id", mb2.getId());
		}
		return "redirect:main";
	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		System.out.println("����");
		return "main";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// �α׾ƿ� 
		session.invalidate();
		System.out.println("�α׾ƿ� ó��");
		return "redirect:main";
	}
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(HttpSession session, Model model) {
		System.out.println("����");
		MemberBean mb = memService.info((String)session.getAttribute("id"));
		model.addAttribute("mb",mb);
		
		return "info";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		System.out.println("������Ʈ");
		MemberBean mb = memService.info((String)session.getAttribute("id"));
		model.addAttribute("mb",mb);
		return "updateForm";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePost(MemberBean mb) {
		
		MemberBean mb2 = memService.userCheck(mb);
		
		if(mb2!=null) {
			memService.update(mb);
		}
		System.out.println("������Ʈ �Ϸ�");
		return "redirect:main";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete() {

		return "deleteForm";
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePost(MemberBean mb, HttpSession session) {
		System.out.println("���� �Ϸ�");
		System.out.println("����");
		MemberBean mb2 = memService.userCheck(mb);
		if(mb2!=null) {
			memService.delete(mb.getId());
			session.invalidate();
		}
		return "redirect:main";
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		System.out.println("����Ʈ");
		return "list";
	}

}
