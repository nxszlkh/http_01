package com.lkh.test;

import java.io.UnsupportedEncodingException;

import com.lkh.post.HttpSendPost;

public class TestMain {
	// 提供主方法，测试发送GET请求和POST请求
	public static void main(String args[]) {

		String req = "{\"head_chkpwd\":\"1\",\"cust_no_displaylable\":\"\",\"head_inputuser\":\"admin\",\"mobile_displaylable\":\"\",\"AGENT_id_type\":\"\",\"head_rtn_code_success\":\"34242\",\"id_type_displaylable\":\"\",\"head_channel_time\":\"095406\",\"cust_no\":\"\",\"acct_no_displaylable\":\"\",\"app_amt\":\"1\",\"AGENT_name\":\"\",\"AGENT_name_displaylable\":\"\",\"head_rtn_code\":\"\",\"head_sub_branch_code\":\"\",\"SYSTEM_MENUNAME\":\"........\",\"cust_manager_displaylable\":\"\",\"fnc_trans_acct_no_displaylable\":\"\",\"head_checkuser\":\"\",\"acct_no\":\"\",\"AGENT_id_code\":\"\",\"head_channel_flag\":\"0\",\"AGENT_id_code_displaylable\":\"\",\"head_grantuser\":\"\",\"head_rtn_desc\":\"\",\"app_amt_displaylable\":\"1\",\"SYSTEM_BUTTON_DESCRIPT\":\"................\",\"prod_code\":\"001\",\"AGENT_id_type_displaylable\":\"\",\"undefined\":\"\",\"cust_manager\":\"\",\"head_bank_code\":\"01001\",\"grid_save_page\":\"true\",\"prod_code_displaylable\":\"........\",\"undefined_displaylable\":\"\",\"cust_name\":\"\",\"id_type\":\"\",\"head_branch_code\":\"\",\"head_channel_serno\":\"\",\"cust_name_displaylable\":\"\",\"head_trans_serno\":\"\",\"fnc_trans_acct_no\":\"\",\"head_cust_type\":\"\",\"mobile\":\"\",\"id_code\":\"\",\"button_id\":\"M5007B03\",\"id_code_displaylable\":\"\",\"head_channel_date\":\"20170525\"} ";

		byte[] body = null;
		try {
			body = req.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = HttpSendPost.sendPost("https://project.kkws.cn/doc/", body, 3000);

		System.out.println(s);
	}
}
