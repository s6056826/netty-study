package cn.dbw.netty.test;

import net.sf.json.JSONArray;

public class Demo2 {
	/**
	 * [{"CityId":18,"CityName":"杭州","ProvinceId":27,"CityOrder":1},{"CityId":53,"CityName":"广州","ProvinceId":27,"CityOrder":1}] 
	 * 这是一个json格式的字符串，请通过java代码获得CityOrder的值；
	 */
	public static void main(String[] args) {
		String str="[{\"CityId\":18,\"CityName\":\"杭州\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":2}]";
		JSONArray array = JSONArray.fromObject(str);
		for(int i=0;i<array.size();i++){
			 System.out.println(array.getJSONObject(i).getString("CityOrder"));
		}
	}

}
