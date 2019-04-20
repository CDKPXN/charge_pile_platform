package com.company.project.platform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.ChargingOrder;
import com.company.project.model.ChargingRecord;
import com.company.project.model.Pile;
import com.company.project.model.User;
import com.company.project.platform.common.PlatformRequestBody;
import com.company.project.platform.common.PlatformResult;
import com.company.project.platform.config.PlatformConfig;
import com.company.project.platform.util.AesCBC;
import com.company.project.platform.util.CommonBh;
import com.company.project.platform.util.DateUtils;
import com.company.project.platform.util.PlatformOrderUtil;
import com.company.project.platform.util.PlatformToken;
import com.company.project.platform.util.PlatformUtil;
import com.company.project.platform.util.QRCodeUtils;
import com.company.project.service.ChargingOrderService;
import com.company.project.service.ChargingRecordService;
import com.company.project.service.PileService;
import com.company.project.service.UserService;
import com.company.project.utils.HttpClientUtil;
import com.company.project.utils.JavaWebToken;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/evcs/20160701")
@Slf4j
public class ChargingController {
	
    @Resource
	private ChargingOrderService chargingOrderService;
    @Resource
	private PileService pileService;
    @Resource
	private ChargingRecordService chargingRecordService;
    @Resource
    private UserService userService;
	
	/**
	 * 扫码判断设备状态,查询设备状态,发送认证设备请求
	 * @param json
	 * @return
	 */
	@GetMapping("/charging/qrCode")
    public Result qrCode(@RequestParam String QRCode) {
		JSONObject data=new JSONObject();
		data.put("EquipAuthSeq", PlatformConfig.OperatorID+CommonBh.getOrderIdByUUId());
		String ConnectorID=QRCodeUtils.getConnectorID(QRCode);
		if(StringUtils.isBlank(ConnectorID)) {
			return ResultGenerator.genFailResult("电桩不可用");
		}
		data.put("ConnectorID", ConnectorID);
		Pile pile=pileService.findBy("connectorId", ConnectorID);
		if(pile==null) {
			return ResultGenerator.genFailResult("电桩不可用");
		}
		List<String> StationIDs=new ArrayList<String>();
		StationIDs.add(pile.getSid().toString());
		JSONObject data0=new JSONObject();
		data0.put("StationIDs", StationIDs);
		try {
			//请求设备状态
			//PlatformResult p0=PlatformUtil.sendPost(data0.toJSONString(), "query_station_status");
			//JSONObject result0= JSONObject.parseObject(p0.getData());//真是数据
			JSONObject result0= JSONObject.parseObject("{\"StationStatusInfos\": [{\"StationID\": \"3702120022101\",\"ConnectorStatusInfos\": [{\"ConnectorID\": \"1\",\"Status\": 2}]}],\"Total\": 2}");//模拟数据
			JSONArray array= result0.getJSONArray("StationStatusInfos");
			JSONArray Connector=JSONObject.parseObject(array.get(0).toString()).getJSONArray("ConnectorStatusInfos");
			for (Object object : Connector) {
				JSONObject js=JSONObject.parseObject(object.toString());
				if(js.getInteger("Status")==2&&ConnectorID.equals(js.getString("ConnectorID"))) {
					//认证设备
					//PlatformResult p=PlatformUtil.sendPost(data.toJSONString(), "query_equip_auth");
					//JSONObject result= JSONObject.parseObject(p.getData());//真实数据
					JSONObject result= JSONObject.parseObject("{\"EquipAuthSeq\": \"123456789201805071630123456\",\"ConnectorID\": \"3702120244102\",\"FailReason\": 0,\"SuccStat\": 0}");//模拟数据
					if(result.getInteger("SuccStat")==0) {
						return ResultGenerator.genSuccessResult();
					}else {
						return ResultGenerator.genFailResult("电桩不可用");
					}
				}
			}
		
			return  ResultGenerator.genFailResult("电桩不可用");
			
		} catch (Exception e) {
			log.info("request failed : "+e.getMessage());
			return ResultGenerator.genFailResult("请求超时");
		}
    }
	
	/**
	 * 设置并开始充电(新增订单,修改电桩状态预约,发送开始充电请求)
	 * @param json
	 * @return
	 */
	@PostMapping("/start_charging")
    public Result set_charging(@RequestBody JSONObject json,HttpServletRequest request) {
		System.err.println(request.getHeader("token"));
		String id = JavaWebToken.parserStaffIdByToken(request);
		System.err.println(id);
		User user=userService.findBy("id", Long.parseLong(id));
		Long value=json.getLong("value");
		Integer models=json.getInteger("model");
		String QRCode=json.getString("QRCode");
		String ConnectorID=QRCodeUtils.getConnectorID(QRCode);
		String PhoneNum=json.getString(user.getPhone());
		String  cq=PlatformConfig.OperatorID+CommonBh.getOrderIdByUUId();
		//电桩信息
		if(StringUtils.isBlank(ConnectorID)) {
			return ResultGenerator.genFailResult("参数错误");
		}
		Pile pile=pileService.findBy("connectorId", ConnectorID);
		if(pile==null) {
			return ResultGenerator.genFailResult("电桩不存在");
		}
		//新增订单
		ChargingOrder model=new ChargingOrder();
		model.setChargeSeq(cq);
		model.setModel(models);
		model.setPid(pile.getId());
		model.setSetValue(value);
		model.setStatus(1);
		model.setUid(user.getId());
		model.setBalance(user.getBalance());
		model.setOperator(1);
		chargingOrderService.save(model);
		System.err.println("充电订单号 : "+cq);
		//开始充电
		JSONObject data=new JSONObject();
		data.put("StartChargeSeq", cq);
		data.put("ConnectorID", ConnectorID);
		data.put("QRCode", QRCode);
		data.put("PhoneNum", PhoneNum);
		try {
			//PlatformResult p=PlatformUtil.sendPost(data.toJSONString(), "query_start_charge_with_phone_num");
			//JSONObject result= JSONObject.parseObject(p.getData());
			JSONObject result= JSONObject.parseObject(" {\"StartChargeSeqStat\": 1,\"StartChargeSeq\": \""+cq+"\",\"ConnectorID\": \"1\",\"SuccStat\": 0,\"FailReason\": 0}");//模拟数据
			if(result.getInteger("SuccStat")==0) {
				return ResultGenerator.genSuccessResult(model.getId());
			}
		} catch (Exception e) {
			log.info("query start charge failed : "+e.getMessage());
		}
		model.setStatus(3);
		chargingOrderService.update(model);
		return ResultGenerator.genFailResult("启动失败");
		
    }
	
	/**
	   *   接受特来电推送启动充电结果
	   *   1：启动中    
	   *   2：充电中
	   *   3：停止中
	   *   4：已结束
	   *   5：未知)
	 * @param json
	 * @return
	 */
	@PostMapping("/notification_start_charge_result")
    public PlatformRequestBody notification_start_charging(@RequestBody JSONObject json) {
		
		PlatformResult params=PlatformUtil.decryptResult(json.toString());
		JSONObject result= JSONObject.parseObject(params.getData());
		//JSONObject result= JSONObject.parseObject("{\"StartChargeSeq\":\"123456789643000001394449368\",\"StartChargeSeqStat\":2,\"StartTime\":\"2019-04-17 06:54:08\",\"ConnectorID\":\"1\"}");
		System.err.println("充电启动结果:"+result);
		String StartChargeSeq=result.getString("StartChargeSeq");
		Integer StartChargeSeqStat=result.getInteger("StartChargeSeqStat");
		//String ConnectorID=result.getString("ConnectorID");
		//String IdentCode=result.getString("IdentCode");
		String StartTime=result.getString("StartTime");
		String s="{\"StartChargeSeq\": \"123456789201805141125123456\",\"SuccStat\": 0,\"FailReason\": 0}";
		String f="{\"StartChargeSeq\": \"123456789201805141125123456\",\"SuccStat\": 1,\"FailReason\": 1}";
		PlatformRequestBody success = new PlatformRequestBody(s);
		PlatformRequestBody failed = new PlatformRequestBody(f);
		if(StartChargeSeqStat!=2) {
			return success;
		}
		//订单信息
		ChargingOrder order=chargingOrderService.findBy("chargeSeq", StartChargeSeq);
		if(order==null) {
		    log.info("order chargeSeq("+StartChargeSeq+") is not exist");
			return failed;
		}
		order.setBeginTime(DateUtils.getDateTimeFormat(StartTime));
		order.setStatus(1);
		chargingOrderService.update(order);
		return success;
		
    }
	
	
	/**
	 * 刷新实时充电状态(更新电桩信息,更新订单信息,新增充电记录,根据余额、充电设置判断是否停止)
	 * @param json
	 * @return
	 */
	@PostMapping("/charging")
    public Result charging(@RequestBody JSONObject json) {
		String oid=json.getString("oid");
		ChargingOrder order=chargingOrderService.findBy("id", Long.parseLong(oid));
		if(order==null||order.getStatus()==3) {
			return ResultGenerator.genFailResult("订单不存在或已结束");
		}
		//请求实时充电状态
		JSONObject data=new JSONObject();
		data.put("StartChargeSeq", order.getChargeSeq());
		try {
			//PlatformResult p=PlatformUtil.sendPost(data.toJSONString(), "query_equip_charge_status");
			//JSONObject result= JSONObject.parseObject(p.getData());
			String ss="{\r\n" + 
					"        \"StartChargeSeqStat\": 2,\r\n" + 
					"        \"StartChargeSeq\": \""+order.getChargeSeq()+"\",\r\n" + 
					"        \"ConnectorID\": \"1\",\r\n" + 
					"        \"ConnectorStatus\": 3,\r\n" + 
					"        \"CurrentA\": 78.1,\r\n" + 
					"        \"CurrentB\": 0.0,\r\n" + 
					"        \"CurrentC\": 0.0,\r\n" + 
					"        \"VoltageA\": 575.3,\r\n" + 
					"        \"VoltageB\": 0.0,\r\n" + 
					"        \"VoltageC\": 0.0,\r\n" + 
					"        \"Soc\": 5.0,\r\n" + 
					"        \"StartTime\": \"2019-04-17 06:54:08\",\r\n" + 
					"        \"EndTime\": \"2019-04-17 06:59:08\",\r\n" + 
					"        \"TotalPower\": 3.54,\r\n" + 
					"        \"ElecMoney\": 4.956,\r\n" + 
					"        \"SeviceMoney\": 0.0,\r\n" + 
					"        \"TotalMoney\": 4.956,\r\n" + 
					"        \"SumPeriod\": 1,\r\n" + 
					"        \"ChargeDetails\": [\r\n" + 
					"          {\r\n" + 
					"              \"DetailStartTime\": \"2019-04-17 06:54:08\",\r\n" + 
					"              \"DetailEndTime\": \"2019-04-17 06:59:08\",\r\n" + 
					"              \"ElecPrice\": 1.4,\r\n" + 
					"              \"SevicePrice\": 0.0,\r\n" + 
					"              \"DetailPower\": 3.54,\r\n" + 
					"              \"DetailElecMoney\": 4.956,\r\n" + 
					"              \"DetailSeviceMoney\": 0.0\r\n" + 
					"          }\r\n" + 
					"          ]\r\n" + 
					"  }";
			JSONObject result= JSONObject.parseObject(ss);
			//更新订单
			order.setElectricQuantity(result.getDouble("TotalPower"));
			order.setCprice(result.getDouble("ElecMoney")*100);
			order.setSprice(result.getDouble("SeviceMoney")*100);
			int duration=DateUtils.diffDateSecond(DateUtils.getDateTimeFormat(result.getString("StartTime")), 
					DateUtils.getDateTimeFormat(result.getString("EndTime")));
			System.err.println("充电时长:"+duration);
			order.setDuration(duration);
			chargingOrderService.update(order);
			//更新电桩
			Pile pile=pileService.findById(order.getPid());
			pile.setElectricQuantity(result.getDouble("TotalPower"));
			pile.setVoltage(result.getDouble("VoltageA"));
			pile.setElectricity(result.getDouble("CurrentA"));
			pile.setCprice((int)(result.getDouble("ElecMoney")*100));
			pile.setSprice((int)(result.getDouble("SeviceMoney")*100));
			pile.setDuration(duration);
			pileService.update(pile);
			//新增充电记录
			ChargingRecord cr=new ChargingRecord();
			cr.setOid(order.getId());
			cr.setAmount(result.getDouble("TotalPower"));
			cr.setVoltage(result.getDouble("VoltageA"));
			cr.setCurrent(result.getDouble("CurrentA"));
			cr.setSoc(result.getString("Soc"));
			chargingRecordService.save(cr);
			//结算判断
			ChargingController cc=new ChargingController();
			User user=userService.findBy("id", order.getUid());
			int res=cc.judge(order,user);
			if(res==1) {
				//发起停止充电请求
				JSONObject data0=new JSONObject();
				data0.put("StartChargeSeq", order.getChargeSeq());
				data0.put("ConnectorID",pile.getConnectorId());
				PlatformResult end=PlatformUtil.sendPost(data0.toJSONString(), "query_ stop _charge");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultGenerator.genSuccessResult();
    }
	
	/**
	 * 停止充电
	 * @param json
	 * @return
	 */
	@PostMapping("/end_charging")
    public Result end_charging(@RequestBody JSONObject json) {
		Long oid= json.getLong("oid");
		ChargingOrder order=chargingOrderService.findBy("id", oid);
		if(order==null) {
			ResultGenerator.genFailResult("订单不存在");
		}
		Pile pile=pileService.findById(order.getPid());
		JSONObject data0=new JSONObject();
		data0.put("StartChargeSeq", order.getChargeSeq());
		data0.put("ConnectorID",pile.getConnectorId());
		try {
			PlatformResult end=PlatformUtil.sendPost(data0.toJSONString(), "query_stop_charge");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResultGenerator.genSuccessResult();
    }
	
	/**
	 * 接受订单信息推送信息(更新订单,电桩初始化,用户余额结算)
	 * @param json
	 * @return
	 */
	@PostMapping("/notification_charge_order_info")
    public PlatformRequestBody notification_charge_order_info(@RequestBody JSONObject json) {
		
		//PlatformResult params=PlatformUtil.decryptResult(json.toString());
		String ss="{\r\n" + 
				"      \"StartChargeSeq\":\"123456789665000001176708522\",\r\n" + 
				"      \"ConnectorID\":\"1\",\r\n" + 
				"      \"StartTime\":\"2019-04-18 11:29:29\",\r\n" + 
				"      \"EndTime\":\"2019-04-18 11:39:30\",\r\n" + 
				"      \"TotalPower\":6.00,\r\n" + 
				"      \"TotalElecMoney\":6.00,\r\n" + 
				"      \"TotalSeviceMoney\":6.00,\r\n" + 
				"      \"TotalMoney\":12.00,\r\n" + 
				"      \"StopReason\":2,\r\n" + 
				"      \"SumPeriod\":0,\r\n" + 
				"      \"ChargeDetails\":[]\r\n" + 
				"  }";
		//JSONObject result= JSONObject.parseObject(params.getData());
		JSONObject result= JSONObject.parseObject(ss);
		String StartChargeSeq=result.getString("StartChargeSeq");
		String ConnectorID=result.getString("ConnectorID");
		Date StartTime=DateUtils.getDateTimeFormat(result.getString("StartTime"));
		Date EndTime=DateUtils.getDateTimeFormat(result.getString("EndTime"));
		int duration=DateUtils.diffDateSecond(StartTime, EndTime);
		JSONObject res=new JSONObject();
		res.put("StartChargeSeq",StartChargeSeq);
		res.put("ConnectorID",ConnectorID);
		res.put("ConfirmResult",0);
		PlatformRequestBody success = new PlatformRequestBody(res.toJSONString());
		//更新订单
		ChargingOrder order=chargingOrderService.findBy("chargeSeq", StartChargeSeq);
		if(order==null) {
			System.err.println("订单不存在");
			return success;
		}
		order.setBeginTime(StartTime);
		order.setEndTime(EndTime);
		order.setElectricQuantity(result.getDouble("TotalPower"));
		order.setCprice(result.getDouble("TotalElecMoney")*100);
		order.setSprice(result.getDouble("TotalSeviceMoney")*100);
		order.setStatus(3);
		order.setDuration(duration);
		chargingOrderService.update(order);
		//用户余额结算
		try {
			String res0=HttpClientUtil.put("http://106.75.166.143:8082/app/user/orders/closeOrder", "{\"oid\":\""+order.getId()+"\"}");
		} catch (Exception e) {
			log.info("订单结算失败 :"+e.getMessage());
		}
//		User user=userService.findBy("id", order.getUid());
//		user.setBalance(user.getBalance()-order.getCprice()-order.getSprice());
//		userService.update(user);
		//电桩状态初始化
		Pile pile=pileService.findById(order.getPid());
		pile.setStatus(0);
		pile.setElectricQuantity(0d);
		pile.setElectricity(0d);
		pile.setVoltage(0d);
		pile.setCprice(0);
		pile.setSprice(0);
		pile.setDuration(0);
		pileService.update(pile);
		return success;
    }
	

	/**
	 * 判断主动停止充电
	 * @return
	 * 0停止充电1正常
	 */
	public int judge(ChargingOrder order,User user) {
		//余额小于1块主动停止
		if(user.getBalance()<(order.getCprice()+order.getSprice()+100)) {
			log.info("余额不足主动停止充电");
			return 1;
		}
		//定时提前60秒
		if(order.getModel()==1&&(order.getDuration()+60)>=order.getSetValue()) {
			log.info("定时主动停止充电");
			return 1;
		}
		//定电量提前一度电
		if(order.getModel()==2&&(order.getElectricQuantity()+1)>=order.getSetValue()) {
			log.info("定电量主动停止充电");
			return 1;
		}
		//定金额提前1块
		if(order.getModel()==3&&(order.getSetValue()+100)>=(order.getCprice()+order.getSprice())) {
			log.info("定金额主动停止充电");
			return 1;
		}
		return 0;
	}
	
}
